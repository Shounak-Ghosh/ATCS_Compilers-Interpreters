package ast;
import emitter.Emitter;
import environment.Environment;

/**
 * Represents Variables in the AST; stores variable names
 * @author  Shounak Ghosh
 * @version 12.01.2019
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Constructor; creates Variable objects
     * @param n the name of the variable
     */
    public Variable(String n)
    {
        name = n;
    }


    /**
     * Retrieves the value assigned to the variable
     * @param env  stores the state of the variables in use
     * @return the value associated with this
     */
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }

    /**
     * Retrieves the variable's value in MIPS assembly code
     * @param e emits the MIPS assembly code
     */
    public void compile(Emitter e)
    {
        if(e.isLocalVariable(name))
        {
            e.emit("# accessing local variable " + name);
            e.emit("lw $v0 " + e.getOffset(name) + "($sp)");
            return;
        }
        e.emit("# accessing global variable var" + name);
        e.emit("la $t0 var" + name);
        e.emit("lw $v0 ($t0)");
    }

}
