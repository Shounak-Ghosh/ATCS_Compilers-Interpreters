package ast;
import emitter.Emitter;
import environment.Environment;

import java.util.ArrayList;

/**
 * The Block class executes a specified List of Statements
 * @author Shounak Ghosh
 * @version 10.08.2019
 */
public class Block extends Statement
{
    private ArrayList<Statement> statements;

    /**
     * Constructor; creates Block objects
     * @param statements the List of statements within the BEGIN/END statement
     */
    public Block(ArrayList<Statement> statements)
    {
        this.statements = statements;
    }

    /**
     * Executes all the statements within the BEGIN/END statement
     * @param env stores the state of the variables in use
     */
    public void exec(Environment env)
    {
        for (Statement s : statements)
        {
            s.exec(env);
        }
    }

    /**
     * Translate a Pascal BLOCK statement into MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        for (Statement s : statements)
        {
            s.compile(e);
        }
    }
}
