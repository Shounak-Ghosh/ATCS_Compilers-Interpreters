package ast;

import environment.*;

/**
 * Evaluates an Expression and prints the output onto the console.
 * Reads input if the Display statement is followed by a Read statement.
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Display extends Statement
{
    private Expression expr;
    private Read read;

    /**
     * Constructor: Creates Display objects
     * @param e The expression to be displayed
     * @param r a Read object, can be null
     */
    public Display(Expression e, Read r)
    {
        expr = e;
        read = r;
    }

    /**
     * Evaluates the expression, prints the result, and reads input if applicable
     * @param env the environment in which variables are stored
     */
    public void exec(Environment env)
    {
        Value val = expr.eval(env);
        System.out.println(val.toString());
        if(read != null)
        {
            read.exec(env);
        }
    }
}
