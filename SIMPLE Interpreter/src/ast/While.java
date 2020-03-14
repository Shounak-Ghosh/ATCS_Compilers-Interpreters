package ast;

import environment.*;

/**
 * Executes a Program while an Expression evaluates to true
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class While extends Statement
{
    private Expression condition;
    private Program child;

    /**
     * Constructor: creates While objects
     * @param e The expression evaluated to a Boolean value
     * @param c the Program to be executed
     *          if the expression evaluates to true
     */
    public While(Expression e, Program c)
    {
        condition = e;
        child = c;
    }

    /**
     * While a given Expression evaluates to true, the given Program is executed
     * @param env the environment in which variables are stored
     */
    public void exec(Environment env)
    {
        while(condition.eval(env).getBooleanValue())
        {
            child.exec(env);
        }
    }
}
