package ast;
import emitter.Emitter;
import environment.Environment;
import java.util.ArrayList;
import java.util.Collections;

/**
 * ProcedureDeclaration objects store the name of the procedure,
 * the list of formal parameters of the object,
 * and the statement to be executed if the procedure is called
 * @author Shounak Ghosh
 * @version 10.19.2019
 */
public class ProcedureDeclaration extends Statement
{
    private String name;
    private Statement statement;
    private ArrayList<String> parameters;
    private ArrayList<String> localVariables;

    /**
     * Constructor: Creates Procedure declaration objects
     * @param n the name of the procedure
     * @param s the statement to be executed when the procedure is called
     * @param params a list of formal parameters
     * @param locVars a list of variables local to the procedure
     */
    public ProcedureDeclaration(String n, Statement s,
                                ArrayList<String> params, ArrayList<String> locVars)
    {
        name = n;
        statement = s;
        parameters = params;
        localVariables = locVars;
    }

    /**
     * Retrieves the local variables of the procedure
     * @return an ArrayList of the local variables in the procedure
     */
    public ArrayList<String> getLocalVariables()
    {
        return localVariables;
    }

    /**
     * Retrieves the procedure's statement
     * @return the procedure's statement
     */
    public Statement getStatement()
    {
        return statement;
    }

    /**
     * Retrieves the name of the procedure
     * @return the name of the procedure
     */
    public String getName()
    {
        return  name;
    }

    /**
     * Retrieves the list of formal parameters of the procedure
     * @return the list of formal paramters of the procedure
     */
    public ArrayList<String> getParameters()
    {
        return parameters;
    }

    /**
     * Executes the given Procedure declaration
     * @param env stores the state of the variables in use
     */
    public void exec(Environment env)
    {
        env.setProcedure(name,this);
    }

    /**
     * Translates a PASCAL procedure declaration into MIPS assembly code,
     * using a jump return statement
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        e.setProcedureContext(this);
        e.emit("proc" + name +":");
        for(String local: localVariables)
        {
            e.emit("li $v0 0");
            e.emit("# push local variable " + local + " to memory stack");
            e.emit("subu $sp $sp 4");
            e.emit("sw $v0 ($sp)");
        }
        statement.compile(e);
        for (int i = 0; i < localVariables.size(); i++)
        {
            e.emit("# pop local variable off the stack");
            e.emit("lw $v0  ($sp) ");
            e.emit("addu $sp $sp 4");
        }
        e.emit("jr $ra");
        e.clearProcedureContext();
    }
}
