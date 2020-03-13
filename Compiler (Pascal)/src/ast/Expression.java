package ast;
import environment.Environment;
import emitter.Emitter;

/**
 * The abstract Expression class represents the components of
 * the AST that are evaluated to an integer value
 * @author Shounak Ghosh
 * @version 10.08.2019
 */
public abstract class Expression
{
    /**
     * Calculates the numerical value of this
     * @param env  stores the state of the variables in use
     * @return the integer value resulting from evaluating this
     * */
    public abstract int eval(Environment env);

    /**
     * Translates a PASCAL expression into MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        throw new RuntimeException("Implement me!!!!!");
    }
}

