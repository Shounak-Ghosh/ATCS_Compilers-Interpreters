package ast;

import environment.*;

/**
 * A wrapper class for boolean primitives
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Boolean extends Value
{
    private boolean value;

    /**
     * Constructor: Creates Boolean objects
     * @param val the value of the this
     */
    public Boolean(boolean val)
    {
        value = val;
    }

    /**
     * Retrieves the boolean value of this
     * @return a boolean representation of this
     */
    public boolean getBooleanValue()
    {
        return value;
    }

    /**
     * Prints "true" if true, "false" if false
     * @return a String representation of this
     */
    public String toString()
    {
        if(value)
        {
            return "true";
        }
        return "false";
    }


    /**
     * Retrieves the integer value of this
     * @return 1 if true, 0 if false
     */
    public int getNumericalValue()
    {
        if(value)
        {
            return 1;
        }
        return 0;
    }

    /**
     * Evaluates the boolean
     * @param env the environment in which variables are stored
     * @return a new Boolean object with the value of this
     */
    public Value eval(Environment env)
    {
        return new Boolean(value);
    }
}
