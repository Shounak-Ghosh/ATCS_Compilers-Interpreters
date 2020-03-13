package ast;

import emitter.Emitter;
import environment.Environment;

/**
 * The IF evaluates a Condition and executes a given Statement
 * if the conditional is true
 * @author  Shounak Ghosh
 * @version 10.08.2019
 */
public class If extends Statement
{
    private Condition condition;
    private Statement statement;

    /**
     * Constructor; creates If objects
     * @param cond the condition required in order to execute the statement
     * @param s the statement executed if the condition is satisfied
     */
    public If (Condition cond, Statement s)
    {
        condition = cond;
        statement = s;
    }

    /**
     * If the condition is true, the statement is executed
     * @param env  stores the state of the variables in use
     */
    public void exec(Environment env)
    {
        if(condition.eval(env) == 0)
        {
            statement.exec(env);
        }
    }

    /**
     * Translates a PASCAL If statement into MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        int l = e.nextLabelID();
        condition.compile(e,"endif" +l);
        statement.compile(e);
        e.emit("endif" + l + ":");
    }
}
