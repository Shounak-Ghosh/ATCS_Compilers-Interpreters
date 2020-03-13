package ast;

import emitter.Emitter;
import environment.Environment;

/**
 * The Condition class compares to Expressions using a specified conditional
 * @author Shounak Ghosh
 * @version 10.08.2019
 */
public class Condition extends Expression
{
    private String conditional;
    private Expression left;
    private Expression right;

    /**
     * Constructor; Creates Condition objects
     * @param con The conditional applied to the two Expressions
     * @param e1 The expression to the left of the conditional
     * @param e2 The expression to the right of the conditional
     */
    public Condition (String con, Expression e1, Expression e2)
    {
        conditional = con;
        left = e1;
        right = e2;
    }

    /**
     * Evaluates the two Expressions separately,
     * and then applies the given conditional to the results
     * @param env  stores the state of the variables in use
     * @return 0 is returned when the condition is true, 1 is returned if the condition is false
     */
    public int eval(Environment env)
    {
        if(conditional.equals("=")) // left == right
        {
            if(left.eval(env) == right.eval(env))
            {
                return 0;
            }
            return 1;
        }
        else if (conditional.equals("<>")) // left != right
        {
            if(left.eval(env) != right.eval(env))
            {
                return 0;
            }
            return 1;
        }
        else if (conditional.equals("<"))
        {
            if(left.eval(env) < right.eval(env))
            {
                return 0;
            }
            return 1;
        }
        else if (conditional.equals(">"))
        {
            if(left.eval(env) > right.eval(env))
            {
                return 0;
            }
            return 1;
        }
        else if (conditional.equals("<="))
        {
            if(left.eval(env) <= right.eval(env))
            {
                return 0;
            }
            return 1;
        }
        else if (conditional.equals(">="))
        {
            if(left.eval(env) >= right.eval(env))
            {
                return 0;
            }
            return 1;
        }
        return 1;
    }

    /**
     * Translates a PASCAL conditional into MIPS assembly code
     * @param e emits the MIPS assembly code
     * @param targetLabel the label jumped to if the condition is false
     */
    public void compile(Emitter e, String targetLabel)
    {
        left.compile(e);
        e.emit("move $t2 $v0");
        right.compile(e);
        e.emit("move $t3 $v0");

        if(conditional.equals("=")) // left == right
        {
            e.emit("# return true if equal");
            e.emit("bne $t2 $t3 " + targetLabel);
        }
        else if (conditional.equals("<>")) // left != right
        {
            e.emit("# return true if not equal");
            e.emit("beq $t2 $t3 " + targetLabel);
        }
        else if (conditional.equals("<"))
        {
            e.emit("# return true if less than");
            e.emit("bge $t2 $t3 " + targetLabel);
        }
        else if (conditional.equals(">"))
        {
            e.emit("# return true if greater than");
            e.emit("ble $t2 $t3 " + targetLabel);
        }
        else if (conditional.equals("<="))
        {
            e.emit("# return true if less than equal to");
            e.emit("bgt $t2 $t3 " + targetLabel);
        }
        else if (conditional.equals(">="))
        {
            e.emit("# return true if greater than equal to");
            e.emit("blt $t2 $t3 " + targetLabel);
        }

    }


}
