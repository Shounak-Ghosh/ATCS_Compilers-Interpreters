package ast;
import emitter.Emitter;
import environment.Environment;

/**
 * While objects execute a Statement while a given condition is true
 * @author Shounak Ghosh
 * @version 10.08.2019
 */
public class While extends Statement
{
    private Condition condition;
    private Statement statement;

    /**
     * Constructor; Creates While objects
     * @param c the loop condition
     * @param s the Statement to be executed while the condition above is true
     */
    public While (Condition c, Statement s)
    {
        condition = c;
        statement = s;
    }

    /**
     * Executes the given Statement while the Condition is true
     * @param env stores the state of the variables in use
     */
    public void exec(Environment env)
    {
        while (condition.eval(env) == 0)
        {
            statement.exec(env);
        }
    }

    /**
     * Translates a PASCAL While statement into MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        int l = e.nextLabelID();
        e.emit("while" + l + ": ");
        condition.compile(e,"endwhile" +l);
        statement.compile(e);
        e.emit("j while" + l);
        e.emit("endwhile" + l + ":");
    }
}
