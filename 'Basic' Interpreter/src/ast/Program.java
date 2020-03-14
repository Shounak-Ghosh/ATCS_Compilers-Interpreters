package ast;
import environment.*;

/**
 * Executes a statement, and then a child program if one exists.
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Program
{
    private Statement statement;
    private Program child;

    /**
     * Constructor: creates Program objects
     * @param s The initial Statement of the program
     * @param c The child Program, can be null
     */
    public Program(Statement s, Program c)
    {
        statement = s;
        child = c;
    }

    /**
     * Executes the given Statement and the child Program if it is non-null
     * @param env the environment in which variables are stored
     */
    public void exec(Environment env)
    {
        statement.exec(env);
        if(child!=null)
        {
            child.exec(env);
        }
    }
}
