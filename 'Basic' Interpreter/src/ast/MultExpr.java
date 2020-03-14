package ast;

import environment.*;

/**
 * Evaluates a multiplication or division operation,
 * and outputs a Numeral object.
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class MultExpr
{
    private NegExpr left;
    private String op;
    private MultExpr right;

    /**
     * Constructor: creates MultExpr objects
     * @param o the operator; either multiplication or division
     * @param l the left hand side operand
     * @param r the right hand side operand
     */
    public MultExpr(String o, NegExpr l, MultExpr r)
    {
        op = o;
        left = l;
        right = r;
    }


    /**
     * Evaluates a multiplication or division operation to a Numeral object
     * @param env the environment in which variables are stored
     * @return the output of the operation performed, wrapped in a Numeral object
     */
    public Value eval(Environment env)
    {
        int leftEval = left.eval(env).getNumericalValue();
        if (right != null)
        {
            int rightEval = right.eval(env).getNumericalValue();
            if(op.equals("*"))
                return new Numeral(leftEval * rightEval);
            else
                return new Numeral(leftEval / rightEval);
        }
        return new Numeral(leftEval);
    }
    
}
