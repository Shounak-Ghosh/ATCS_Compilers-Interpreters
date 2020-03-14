package ast;

import environment.*;

/**
 * Control flow execution: different Programs are executed depending on the output of
 * the evaluation of an Expression
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class If extends Statement
{
    private Expression condition;
    private Program mainProgram;
    private Program childProgram;

    /**
     * Constructor: creates If objects
     * @param c the Expression to be evaluated
     * @param main the Program executed if the Expression evaluates to true
     * @param child the Program executed if the Expression evaluates to false
     */
    public If(Expression c, Program main, Program child)
    {
        condition = c;
        mainProgram = main;
        childProgram = child;
    }


    /**
     * Executes either the main or child Program
     * based on the evaluation of the condition
     * @param env the environment in which variables are stored
     */
    public void exec(Environment env)
    {
        if(condition.eval(env).getBooleanValue())
        {
            mainProgram.exec(env);
        }
        else if (childProgram != null)
        {
            childProgram.exec(env);
        }
    }
}
