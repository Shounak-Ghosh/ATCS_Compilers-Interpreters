package ast;
import java.util.*;
import environment.*;

/**
 * Reads input from the console and stores it in a variable
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Read extends Statement
{
    private String variable;

    /**
     * Constructor: creates Read objects
     * @param varName the name of the variable
     *                that will store the input read in
     */
    public Read(String varName)
    {
        variable = varName;
    }

    /**
     * Reads in the input from the console, and assigns it to the given variable
     * @param env the environment in which variables are stored
     */
    public void exec(Environment env)
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        Value val;
        if(input.equals("true"))
        {
            val = new Boolean(true);
        }
        else if (input.equals("false"))
        {
            val = new Boolean(false);
        }
        else
        {
            val = new Numeral(Integer.parseInt(input));
        }

        env.declareVariable(variable, val);
    }
}

