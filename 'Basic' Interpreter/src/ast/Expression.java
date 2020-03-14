package ast;

import environment.*;

/**
 * Extends Value, uses comparison operators on two given Expressions,
 * outputs a Boolean object.
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Expression extends Value
{
    private AddExpr left;
    private String relop;
    private Expression right;

    /**
     * Constructor: Creates Expression objects
     * @param op the relation operator to be used for comparison
     * @param l the left hand operand
     * @param r the right hand operand
     */
    public Expression(String op, AddExpr l, Expression r)
    {
        left = l;
        right = r;
        relop = op;
    }

    /**
     * Evaluates the Expression using the operator and the two operands
     * @param env the environment in which variables are stored
     * @return the Value of the
     */
    public Value eval(Environment env)
    {
        Value leftValue = left.eval(env);
        if(right == null)
            return leftValue;
            
        Value rightValue = right.eval(env);
        
        int leftEval = leftValue.getNumericalValue();
        int rightEval = rightValue.getNumericalValue();
        switch (relop)
        {
            case "=":
                return new Boolean(leftEval == rightEval);
            case "<>":
                return new Boolean(leftEval != rightEval);
            case "<":
                return new Boolean(leftEval < rightEval);
            case ">":
                return new Boolean(leftEval > rightEval);
            case "<=":
                return new Boolean(leftEval <= rightEval);
            case ">=":
                return new Boolean(leftEval >= rightEval);
        }
        throw new RuntimeException();
    }
}
