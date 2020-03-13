package ast;

import emitter.Emitter;
import environment.Environment;

/**
 * The BinOp class evaluates two specified Expressions and applies a specified operand to them
 * @author Shounak Ghosh
 * @version 10.08.2019
 */
public class BinOp extends Expression
{
    private String operand;
    private Expression left;
    private Expression right;

    /**
     * Constructor; Creates BinOp objects
     * @param op the binary operator (+,-,/,*) in use
     * @param e1 the expression to the left of the operator
     * @param e2 the expression to the right of the operator
     */
    public BinOp(String op, Expression e1, Expression e2)
    {
        operand = op;
        left = e1;
        right = e2;
    }

    /**
     * Evaluates the expressions on each side of the operator separately,
     * and then applies the binary operator
     * @param env  stores the state of the variables in use
     * @return the integer value resulting from the binary operation
     */
    public int eval(Environment env)
    {
        if(operand.equals("*"))
        {
            return  left.eval(env) * right.eval(env);
        }
        else if(operand.equals("/"))
        {
            return  left.eval(env) / right.eval(env);
        }
        else if(operand.equals("+"))
        {
            return  left.eval(env) + right.eval(env);
        }
        else
        {
            return  left.eval(env) - right.eval(env);
        }
    }

    /**
     * Provides a String representation of the BinOp
     * @return a String representation of the BinOp
     */
    public String toString()
    {
        return left.toString() + " " + operand + " " + right.toString();
    }

    /**
     * Translates a PASCAL binary operation to MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {

        left.compile(e);
        e.emitPush("$v0");
        right.compile(e);
        // $t0 stores the value of left
        e.emitPop("$t0");
        if (operand.equals("+"))
        {
            e.emit("# perform addition");
            e.emit("addu $v0 $t0 $v0");
        }
        else if (operand.equals("-"))
        {
            e.emit("# perform subtraction");
            e.emit("subu $v0 $t0 $v0");
        }
        else if (operand.equals("*"))
        {
            e.emit("# perform multiplication");
            e.emit("mult $t0 $v0");
            e.emit("mflo $v0");
        }
        else if (operand.equals("/"))
        {
            e.emit("# perform division");
            e.emit("div $t0 $v0");
            e.emit("mflo $v0");
        }
    }

}
