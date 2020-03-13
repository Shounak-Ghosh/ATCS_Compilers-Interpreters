package ast;
import environment.Environment;
import emitter.*;

/**
 * The Number class stores the value of the Integer passed in
 * @author Shounak Ghosh
 * @version 10.08.2019
 */
public class Number extends Expression
{
    private int value;

    /**
     * Constructor; Creates Number objects
     * @param num the integer value of this
     */
    public Number(int num)
    {
        value = num;
    }

    /**
     * Returns the value of the number
     * @param env The current environment keep track of the variables
     * @return the value of the number
     */
    public int eval(Environment env)
    {
        return value;
    }

    /**
     * Translates a PASCAL number into MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        e.emit("li $v0 " + value);
    }
}
