package ast;
import environment.Environment;
import emitter.Emitter;

/**
 * The abstract class Statement represents the
 * different kinds of statements that can be parsed in: IF, WHILE,
 * BEGIN/END, and WRITELN
 * @author Shounak Ghosh
 * @version 10.08.2019
 */
public abstract class Statement
{
    /**
     * Executes the different components of the AST
     * @param env  stores the state of the variables in use
     */
    public abstract void exec(Environment env);

    /**
     * Translates a PASCAL statement into MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        throw new RuntimeException("Implement me!!!!!");
    }
}
