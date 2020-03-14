package ast;

import environment.*;

/**
 * a wrapper class for int primitives
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Numeral extends Value
{
    private int num;

    /**
     * Constructor: Creates Numeral objects
     * @param num the integer value of this
     */
    public Numeral(int num)
    {
        this.num = num;
    }

    /**
     * Retrieves the boolean value of this
     * @return true if nonzero, false otherwise
     */
    public boolean getBooleanValue()
    {
        return num != 0;
    }

    /**
     * Retrieves the integer value of this
     * @return the integer value of this
     */
    public int getNumericalValue()
    {
        return num;
    }

    /**
     * Prints the integer associated with this
     * @return a String representation of this
     */
    public String toString()
    {
        return num + "";
    }

    /**
     * Evaluates the Numeral
     * @param env the environment in which variables are stored
     * @return a Numeral object with the value of this
     */
    public Value eval(Environment env)
    {
        return new Numeral(num);
    }
}
