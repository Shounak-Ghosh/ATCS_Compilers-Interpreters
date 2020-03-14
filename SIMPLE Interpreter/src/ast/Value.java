package ast;
import environment.*;

/**
 * The root object to which all expressions are simplified
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public abstract class Value
{
    /**
     * Evaluates an expression
     * @param env the environment in which variables are stored
     * @return the Value of the expression
     */
    public abstract Value eval(Environment env);

    /**
     * Retrieves the numerical value of this
     * @return an integer value representing this
     */
    public int getNumericalValue()
    {
        return -1;
    }


    /**
     * Retrieves the boolean value of this
     * @return a boolean value representing this
     */
    public boolean getBooleanValue()
    {
        return false;
    }

}
