package ast;

import environment.*;

/**
 * Declares or reassigns a variable to a Value within the Environment.
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Assign extends Statement
{
    private String name;
    private Expression expression;

    /**
     * Constructor: Creates Assign objects
     * @param n the name of the variable
     * @param e the expression to be assigned to the variable
     */
    public Assign(String n, Expression e)
    {
        name = n;
        expression = e;
    }

    /**
     * Evaluates the expression and adds the variable-Value pair to the Environment
     * @param env the environment in which variables are stored
     */
    public void exec(Environment env)
    {
        Value val = expression.eval(env);
        if (val instanceof Boolean)
        {
            env.declareVariable(name, new Boolean(val.getBooleanValue()));
        }
        env.declareVariable(name, expression.eval(env));
    }
}
