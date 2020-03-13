package ast;
import emitter.Emitter;
import environment.Environment;
import java.util.ArrayList;

/**
 * ProcedureCall objects evaluate a ProcedureDeclaration
 * by substituting the actual parameters passed in
 * @author Shounak Ghosh
 * @version 10.19.2019
 */
public class ProcedureCall extends Expression
{
    private String name;
    private ArrayList<Expression> actualParameters;

    /**
     * Constructor: Creates ProcedureCall objects
     * @param n the name of the procedure
     * @param actualParams the actual parameters passed into the procedure
     */
    public ProcedureCall(String n, ArrayList<Expression> actualParams)
    {
        name = n;
        actualParameters = actualParams;
    }

    /**
     * Evaluates a procedure call
     * @param env stores the state of the variables in use
     * @return the return value of the procedure; 0 is the default
     */
    public int eval(Environment env)
    {
        Environment local = new Environment(env);
        local.declareVariable(name,0);
        ProcedureDeclaration current = env.getProcedure(name);
        local.setProcedure(name,current); // allows for recursion
        for(String localVar: current.getLocalVariables())
        {
            local.declareVariable(localVar,0);
        }
        for (int i = 0; i < current.getParameters().size(); i++)
        {
            local.declareVariable(current.getParameters().get(i),actualParameters.get(i).eval(env));
        }
        current.getStatement().exec(local);
        int val = local.getVariable(name);
        return val;
    }

    /**
     * Translates a PASCAl procedure call into MIPS assembly code,
     * using a jump and link statement
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        e.emitPush("$ra");
        for (Expression expr : actualParameters)
        {
            expr.compile(e);
            e.emitPush("$v0");
        }
        // push the return value onto the memory stack
        e.emit("li $v0 0");
        e.emitPush("$v0");
        // jump and link
        e.emit("jal proc" + name);
        e.emit("# pop return value off the stack");
        e.emit("lw $v0  ($sp) ");
        e.emit("addu $sp $sp 4");
        for (int i = 0; i < actualParameters.size(); i++)
        {
            e.emit("# pop parameter off the stack");
            e.emit("lw $v0  ($sp) ");
            e.emit("addu $sp $sp 4");
        }
        e.emitPop("$ra");
    }


}
