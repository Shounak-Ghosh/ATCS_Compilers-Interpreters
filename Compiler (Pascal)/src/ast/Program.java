package ast;

import environment.Environment;
import emitter.Emitter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Program objects stores a list of procedure declarations as well
 * as the program statement
 * @author Shounak Ghosh
 * @version 10.20.2019
 */
public class Program extends Statement
{
    private HashSet<String> variableNames;
    private ArrayList<ProcedureDeclaration> procedureDeclarations;
    private Statement statement;

    /**
     * Constructor: Creates Program objects
     * @param pd an ArrayList of ProcedureDeclaration objects
     * @param s the program Statement to be exeecuted
     */
    public Program(HashSet<String> var, ArrayList<ProcedureDeclaration> pd, Statement s)
    {
        variableNames = var;
        procedureDeclarations = pd;
        statement = s;
    }

    /**
     * Executes each of the Procedure declarations, and then the program statement
     * @param env stores the state of the variables and procedures in use
     */
    public void exec(Environment env)
    {
        for (ProcedureDeclaration pd: procedureDeclarations)
        {
            pd.exec(env);
        }
        statement.exec(env);
    }

    /**
     * Compiles a PASCAL program into MIPS assembly code
     * @param filename the name of the file to which the MIPS code is written to
     */
    public void compile(String filename)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        Emitter e = new Emitter(filename);
        e.emit("# Auto generated MIPS code file");
        e.emit("# @author Shounak Ghosh");
        LocalDateTime now = LocalDateTime.now();
        e.emit("# @version " + dtf.format(now));
        e.emit(".text");
        e.emit(".globl main");
        e.emit("main: ");
        statement.compile(e);
        e.emit("# exit program");
        e.emit("li $v0 10");
        e.emit("syscall");
        for (ProcedureDeclaration pd: procedureDeclarations)
        {
            pd.compile(e);
        }
        e.emit(".data");
        e.emit("newline: ");
        e.emit("\t .asciiz \"\\" + "n\"");
        for (String name : variableNames)
        {
            e.emit("var" + name + ":");
            e.emit("\t .word 0");

        }
        e.close();
    }

}
