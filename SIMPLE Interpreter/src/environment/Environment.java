package environment;

import java.util.*;
import ast.*;

/**
 * Stores the variables created during the execution process
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Environment
{
    private HashMap<String, Value> variables;

    /**
     * Constructor: creates Environment objects
     */
    public Environment()
    {
        variables = new HashMap<String, Value>();
    }

    /**
     * Associates the variable name with its value in the local environment
     * @param name the name of the variable
     * @param v the Integer value corresponding to the variable
     */
    public void declareVariable(String name, Value v)
    {
        variables.put(name, v);
    }


    /**
     * Retrieves the value of a variable, given its name
     * If the variable is not found in the local environment,
     * the parent environment is searched
     * @param name the name of the variable
     * @return the value associated with the variable
     */
    public Value getVariable(String name)
    {
        return variables.get(name);
    }

}
