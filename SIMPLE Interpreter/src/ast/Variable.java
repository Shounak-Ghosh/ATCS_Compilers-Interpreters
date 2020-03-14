package ast;
import environment.*;

/**
 * Associates a String (the name of the variable) with a Value object
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Variable extends Value
{
    private String name;

    /**
     * Constructor: creates Variable objects
     * @param varName the name of the variable
     */
    public Variable(String varName)
    {
        name = varName;
    }

    /**
     * Retrieves the Value object associated with the variable
     * @param env the environment in which variables are stored
     * @return the Value associated with the variable
     */
    public Value eval(Environment env)
    {
        return env.getVariable(name);
    }
}
