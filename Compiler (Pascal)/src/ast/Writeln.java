package ast;
import emitter.Emitter;
import environment.Environment;

/**
 * Prints out the Expression passed in to the console
 * @author Shounak Ghosh
 * @version 10.08.2019
 */
public class Writeln extends Statement
{
    private Expression expression;

    /**
     * Constructor; creates Writeln objects
     * @param exp the Expression to be printed
     */
    public Writeln(Expression exp)
    {
        expression = exp;
    }

    /**
     * Prints out the Expression
     * @param env stores the state of the variables in use
     */
    public void exec(Environment env)
    {
        System.out.println(expression.eval(env));
    }

    /**
     * Translates a PASCAL WRITELN statement into MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        expression.compile(e);
        e.emit("move $a0 $v0");
        e.emit("li $v0 1");
        e.emit("syscall");
        e.emit("li $v0 4");
        e.emit("la $a0 newline ");
        e.emit("syscall");
    }

}