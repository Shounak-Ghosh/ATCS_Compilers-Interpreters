package ast;
import emitter.Emitter;
import environment.Environment;

/**
 * The Assignment class stores a variable and its corresponding Expression
 * @author Shounak Ghosh
 * @version 10.08.2019
 */
public class Assignment extends Statement
{
    private String variable;
    private Expression expression;

    /**
     * Constructor; creates Assignment objects
     * @param var the name of the variable being assigned to
     * @param expr the expression being assigned to the variable
     */
    public Assignment(String var, Expression expr)
    {
        variable = var;
        expression = expr;
    }

    /**
     * Evaluates the expression and sets it equal to the variable
     * @param env stores the state of the variables in use
     */
    public void exec(Environment env)
    {
        env.setVariable(variable,expression.eval(env));
    }

    /**
     * Translates a PASCAL assignment statement into MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        e.emit("# assign a new value to the variable "  + variable);
        expression.compile(e);
        if(e.isLocalVariable(variable))
        {
            e.emit("sw $v0 " + e.getOffset(variable) + "($sp)");
            return;
        }
        e.emit("la $t0 var" + variable);
        e.emit("sw $v0 ($t0)");
    }
}
