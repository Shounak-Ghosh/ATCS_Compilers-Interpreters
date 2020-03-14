package ast;

import environment.*;

/**
 * Negates a given Value if it is preceded by a “-”,
 * returns the Value as is otherwise.
 * @author  Shounak Ghosh
 * @version 12.21.2019
 */
public class NegExpr
{
    private Value value;
    private String binOp;

    /**
     * Constructor: creates NegExpr objects
     * @param val The value to be negated of the operand is negative
     * @param op the operand: either positive or negative,
     *           an empty operator is positive
     */
    public NegExpr(Value val, String op)
    {
        value = val;
        binOp = op;
    }

    /**
     * Negates the Value object if the operator is negative
     * @param env the environment in which variables are stored
     * @return If the operator is negative, the negated Value is returned
     *         otherwise, no chage is made to the value object
     */
    public Value eval(Environment env)
    {
        if(binOp.equals("-"))
        {
            if(value instanceof Numeral)
            {
                return new Numeral(-1 * value.getNumericalValue());

            }
            else if (value instanceof Boolean)
            {
                return new Boolean(!value.getBooleanValue());
            }
        }
        return value.eval(env);
    }
}
