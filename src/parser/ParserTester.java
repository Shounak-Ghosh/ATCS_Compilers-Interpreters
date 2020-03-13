package parser;
import environment.Environment;
import scanner.ScanErrorException;
import scanner.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import ast.Program;


/**
 * Driver class; Tests if input PASCAL code can be parsed and executed correctly;
 * Also tests whether input PASCAL code can be translated correctly into output
 * MIPS assembly code
 * @author Shounak Ghosh
 * @version 12.01.19
 */
public class ParserTester
{
    /**
     * Driver method; used for testing purposes
     * @param args Command-line argument
     * @throws IOException thrown in the case of a file-reading error
     * @throws ScanErrorException thrown if there is an input-mismatch exception
     */
    public static void main(String[] args) throws IOException, ScanErrorException
    {
        for (int i = 0; i <= 15; i++)
        {
            //parseProgram("parserText" + i + ".txt");
            compileProgram("parserText" + i + ".txt", "outText" + i + ".asm");
        }
    }

    /**
     * Parses and compiles a given file
     * @param infile the name of the file to parse from
     * @param outfile the name of the file to write to
     * @throws IOException thrown in the case of a file-reading error
     * @throws ScanErrorException  thrown if there is an input-mismatch exception
     */
    private static void compileProgram(String infile,String outfile) throws IOException, ScanErrorException
    {
        System.out.println(infile);
        InputStream inputstream = new FileInputStream(infile);
        Scanner scanner = new Scanner(inputstream);
        Environment global = new Environment(null);
        Parser parser = new Parser(scanner);
        Program p = null;
        while (scanner.hasNext())
        {
            p  = parser.parseProgram();
        }
        if (p != null)
        {
            p.exec(global);
            p.compile(outfile);
        }
        System.out.println();
    }

    /**
     * Parses a given file; handling for procedure calls and declarations
     * @param filename the name of the file to parse from
     * @throws IOException thrown in the case of a file-reading error
     * @throws ScanErrorException thrown if there is an input-mismatch exception
     */
    private static void parseProgram(String filename) throws IOException, ScanErrorException
    {
        System.out.println(filename);
        InputStream inputstream = new FileInputStream(filename);
        Scanner scanner = new Scanner(inputstream);
        Environment global = new Environment(null);
        Parser parser = new Parser(scanner);
        Program p = null;
        while (scanner.hasNext())
        {
            p  = parser.parseProgram();
        }
        if (p != null)
        {
            p.exec(global);
        }
        System.out.println();
    }

    /**
     * Parses a given file; does not include procedures
     * @param filename the name of the file to parse from
     * @throws IOException thrown in the case of a file-reading error
     * @throws ScanErrorException thrown if there is an input-mismatch exception
     */
    private static void parse(String filename) throws IOException, ScanErrorException
    {
        System.out.println(filename);
        InputStream inputstream = new FileInputStream(filename);
        Scanner scanner = new Scanner(inputstream);
        Environment global = new Environment(null);
        Parser parser = new Parser(scanner);
        while (scanner.hasNext())
        {
            parser.parseStatement().exec(global);
        }
        System.out.println();
    }

}
