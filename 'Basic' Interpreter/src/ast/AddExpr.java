package ast;

import environment.*;

/**
 * Evaluates an addition or subtraction  operation given an operand,
 * a MultExpr, AddExpr and outputs a Numeral object.
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class AddExpr
{
    private MultExpr left;
    private String operand;
    private AddExpr right;

    /**
     * Constructor: Creates AddExpr objects
     * @param o the operand
     * @param l the left hand side operand
     * @param r the right hand side operand
     */
    public AddExpr(String o, MultExpr l, AddExpr r)
    {
        left = l;
        right = r;
        operand = o;
    }


    /**
     * Evaluates the operation between the operands using the operator
     * @param env the environment in which variables are stored
     * @return the Value object created upon evaluation
     */
    public Value eval(Environment env)
    {
        int a = left.eval(env).getNumericalValue();
        if(right == null)
            return new Numeral(a);
        int b = right.eval(env).getNumericalValue();
        if(operand.equals("+"))
            return new Numeral(a + b);
        else
            return new Numeral(a - b);
    }
}
