package parser;

import scanner.*;
import environment.*;
import java.io.*;

/**
 * Driver class; Tests if input SIMPLE code can be parsed and executed correctly
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Tester
{
    /**
     * Driver method; used for testing purposes
     * @param args Command-line argument
     * @throws IOException thrown in the case of a file-reading error
     */
    public static void main(String[] args) throws Exception
    {
        for (int i = 0; i <= 2; i++)
        {
            System.out.println("");
            System.out.println("simpleTest"+ i + ".txt");
            System.out.println("");
            executeProgram("simpleTest" + i + ".txt");
            System.out.println("");
        }
    }

    /**
     * Parses a given file; generates the AST and executes it,
     * and prints the ouptut to the console
     * @param filename the name of the file read from
     * @throws Exception thrown in the case of a file-reading error
     */
    private static void executeProgram(String filename) throws Exception
    {
        Scanner scanner = new Scanner(new FileInputStream(filename));
        Parser parser = new Parser(scanner);
        Environment global = new Environment();
        parser.parseProgram().exec(global);
    }
}
