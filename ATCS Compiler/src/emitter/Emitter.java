package emitter;
import ast.ProcedureDeclaration;
import java.io.*;
import java.util.Collections;

/**
 * Outputs MIPS assembly code
 * @author Anu Datar
 * @author Shounak Ghosh
 * @version 11.28.19
 */
public class Emitter
{
    private PrintWriter out;
    private int labelNum;
    private ProcedureDeclaration currentPD = null;
    private int excessStackHeight;

    /**
     * Constructor: Creates Emitter objects
     * @param outputFileName the name of the file to be written too
     */
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints one line of code to file (with non-labels indented)
     * @param code the MIPS assembly code to be printed
     */
    public void emit(String code)
    {
        if(code.contains("/"))
        {
            code = "\t" + code;
        }
        if (!code.contains(":") && !code.contains("."))
        {
            code = "\t" + code;
        }
        out.println(code);
    }

    /**
     * Closes the output file. This should be called after all calls to emit.
     */
    public void close()
    {
        out.close();
    }

    /**
     * Sets the current procedure context to the parameter passed in
     * @param procDec the ProcedureDeclaration object
     *                being set to the current procedure context
     */
    public void setProcedureContext(ProcedureDeclaration procDec)
    {
        currentPD = procDec;
        excessStackHeight = 0;
    }

    /**
     * Clears the current procedure context by
     * setting the current procedureDeclaration to null
     */
    public void clearProcedureContext()
    {
        currentPD = null;
    }

    /**
     * Checks if a given variable is local or global
     * @param varName the name of the variable to be checked
     * @return true if the variable is local; false otherwise
     */
    public boolean isLocalVariable(String varName)
    {
        if(currentPD != null)
        {
            for(String local: currentPD.getLocalVariables())
            {
                if(varName.equals(local))
                {
                    return true;
                }
            }
            if(varName.equals(currentPD.getName()))
            {
                return true;
            }
            for(String name : currentPD.getParameters())
            {
                if(name.equals(varName))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the offset of the given variable within the memory stack
     * @param localVarName localVarName is the name of a local variable
     * for the procedure currently being compiled
     * @return the offset value of the variable in the memory stack
     */
    public int getOffset(String localVarName)
    {
        int offset = excessStackHeight;
        for(int i = 0; i < currentPD.getLocalVariables().size(); i++)
        {
            if(localVarName.equals(currentPD.getLocalVariables().get(i)))
            {
                return offset;
            }
            offset += 4;
        }
        if (localVarName.equals(currentPD.getName()))
        {
            return offset;
        }
        offset += 4;

        for (int i = currentPD.getParameters().size() -1; i >= 0; i--)
        {
            if(localVarName.equals(currentPD.getParameters().get(i)))
            {
                return offset;
            }
            offset += 4;
        }

        return -1;
    }

    /**
     * Pushes the value stored in the given register to the memory stack
     * @param reg the name of the register to be pushed to the memory stack
     */
    public void emitPush(String reg)
    {
        emit("# push " + reg);
        emit("subu $sp $sp 4");
        emit("sw " + reg + "  ($sp) ");
        excessStackHeight += 4;
    }

    /**
     * Pops the topmost value of the memory stack
     * @param reg the name of the register that will store
     *               the value popped off the memory stack
     */
    public void emitPop(String reg)
    {
        emit("# pop top of stack, store in "  + reg);
        emit("lw " + reg + "  ($sp) ");
        emit("addu $sp $sp 4");
        excessStackHeight -= 4;
    }

    /**
     * Retrieves the next id available for MIPS label names
     * @return the next available integer id
     */
    public int nextLabelID()
    {
        labelNum++;
        return labelNum;
    }



}