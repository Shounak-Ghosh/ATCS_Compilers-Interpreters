package environment;
import ast.ProcedureDeclaration;
import java.util.HashMap;

/**
 * Stores the state of all the variables and procedures created
 * Procedures are stored at the global environment, and
 * variables are stored in the environment they are declared at
 * @author Shounak Ghosh
 * @version 10.20.2019
 */
public class Environment
{
    private HashMap<String, Integer> variables;
    private Environment parent;
    private HashMap<String, ProcedureDeclaration> procedures;

    /**
     * Constructor; creates Environment objects
     * @param p the parent Environment
     */
    public Environment(Environment p)
    {
        variables = new HashMap<String, Integer>();
        procedures = new HashMap<String, ProcedureDeclaration>();
        parent = p;
    }

    /**
     * Associates a given variable name to a given value
     * @param variable the variable name
     * @param value the evaluated integer value of the variable
     */
    public void setVariable (String variable, Integer value)
    {
        if (!variables.containsKey(variable))
        {
            if(parent == null)
            {
                variables.put(variable, value);
            }
            else
            {
                parent.setVariable(variable,value);
            }
        }
        else
        {
            variables.put(variable,value);
        }
    }


    /**
     * Associates the variable name with its value in the local environment
     * @param variable the name of the variable
     * @param value the Integer value corresponding to the variable
     */
    public void declareVariable(String variable, Integer value)
    {
        variables.put(variable,value);
    }

    /**
     * Retrieves the value of a variable, given its name
     * If the variable is not found in the local environment,
     * the parent environment is searched
     * @param variable the name of the variable
     * @return the value associated with the variable
     */
    public int getVariable(String variable)
    {
        if(variables.containsKey(variable))
        {
            return variables.get(variable);
        }
        return parent.getVariable(variable);
    }

    /**
     * Associates a procedure name with a procedure declaration
     * @param name the name of the procedure
     * @param pd the ProcedureDeclaration of the given procedure
     */
    public void setProcedure(String name, ProcedureDeclaration pd)
    {
        if(parent == null) // global
        {
            procedures.put(name, pd);
        }
        else
        {
            parent.setProcedure(name,pd);
        }

    }

    /**
     * Retrieves the ProcedureDeclaration object for a given name
     * @param name the name of the procedure
     * @return the ProcedureDeclaration object associated with the procedure name
     */
    public ProcedureDeclaration getProcedure(String name)
    {
        if(parent == null) // the global environment
        {
            return procedures.get(name);
        }
        return parent.getProcedure(name);
    }
}
