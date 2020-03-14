package scanner;

import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author Shounak Ghosh
 * @author Anu Datar
 * @version 9.06.19
 *
 * Usage:
 * Provide a piece of text (a String or use the InStream class) for the Scanner to parse.
 * The Scanner will output the various lexemes in the input text, line by line.
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;


    /**
     * Scanner constructor for construction of a scanner that
     * uses an InputStream object for input.
     * Usage:
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new scanner.Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }

    /**
     * Retrieves the next character from the input stream
     */
    private void getNextChar()
    {
        try
        {
            int i = in.read();
            if(i == -1)
                eof = true;
            currentChar = (char)i;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether currentChar and the parameter are equal
     * @param expected the character currentChar is being compared to
     * @throws IOException thrown if a file input output error occurs
     */
    private void eat(char expected) throws IOException
    {
        if(currentChar != expected)
            throw new IOException("Illegal Character : expected " + currentChar
                    + " but found " + expected);
        getNextChar();
    }


    /**
     * Checks if there are more characters to be parsed in the file
     * @return true if there are more characters in the file,
     *         false if the end-of-file has been reached
     */
    private boolean hasNext()
    {
        return !eof;
    }


    /**
     * Checks if the given character is a digit (0-9)
     * @param input the character to be checked
     * @return true if the given character is a digit, false otherwise
     */
    private static boolean isDigit(char input)
    {
        return input >= 48 && input <= 57;
    }


    /**
     * Checks if the given character is a letter, without regard to case
     * @param input the character to be checked
     * @return true if the given character is a letter, false otherwise
     */
    private static boolean isLetter(char input)
    {
        return (input >= 65 && input <= 90) || (input >= 97 && input <= 122);
    }


    /**
     * Checks if the given character is a whitespace
     * @param input the character to be checked
     * @return true if the given character is a white space, false otherwise
     */
    private static boolean isBlank(char input)
    {
        String a = input + "";
        return a.equals(" ") || a.equals("\t") || a.equals("\r") || a.equals("\n");
    }

    /**
     * reads in a number using its regex definition: digit(digit)*
     * @return the number read in, as a String
     * @throws IOException thrown if a file input output error occurs
     */
    private String scanNumber() throws IOException
    {
        String lex = "";
        while(!eof && isDigit(currentChar))
        {
            lex += currentChar;
            eat(currentChar);
        }
        return lex;
    }


    /**
     * reads in an identifier using its regex definition: letter(letter|digit)*
     * @return the identifier
     * @throws IOException thrown if a file input output error occurs
     */
    private String scanIdentifier() throws IOException
    {
        String lex = "";
        while(!eof && (isLetter(currentChar) || isDigit(currentChar)))
        {
            lex += currentChar;
            eat(currentChar);
        }
        return lex;
    }


    /**
     * Reads in an operator
     * @return the operator read in
     * @throws IOException thrown if a file input output error occurs
     */
    private String scanOperator() throws IOException
    {
        String lex = String.valueOf(currentChar);
        eat(currentChar);
        if(currentChar == '=')
        {
            lex+=currentChar;
            eat(currentChar);
        }
        else if(lex.equals("<") && currentChar == '>')
        {
            lex+=currentChar;
            eat(currentChar);
        }
        return lex;
    }


    /**
     * Reads in a whitespace
     * @return the whitespace read in
     * @throws IOException thrown if a file input output error occurs
     */
    private String scanBlank() throws IOException
    {
        while(!eof && isBlank(currentChar))
            eat(currentChar);
        return nextToken();
    }


    private static char[] operands = {'=','+','-','*','/','(',')','<','>'};

    /**
     * Checks if the input is one of the following  ['=','+','-','*','/','(',')','<','>']
     * @param input the character to be checked
     * @return true if the input is an operator, false otherwise
     */
    private static boolean isOperator(char input)
    {
        for(char op: operands)
        {
            if (op == input)
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Scans in the next token based on the regex definitions of the different tokens
     * @return the token parsed in
     * @throws IOException thrown if none of the regex definitions were matched
     */
    public String nextToken() throws IOException
    {
        if(!hasNext())
        {
            return "End of file: scanning complete.";
        }
        else if(isDigit(currentChar))
        {
            return scanNumber();
        }
        else if(isLetter(currentChar))
        {
            return scanIdentifier();
        }
        else if(isBlank(currentChar))
        {
            return scanBlank();
        }
        else if(isOperator(currentChar))
        {
            return scanOperator();
        }
        else
        {
            throw new IOException("Illegal Character: " + currentChar);
        }
    }
}
