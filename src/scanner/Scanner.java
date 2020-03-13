package scanner;


import java.util.HashSet;
import java.io.*;

/**
 * scanner.Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author Shounak Ghosh
 * @author Anu Datar
 * @version 9.06.19
 *
 * Usage:
 * Provide a piece of text (a String or use the InStream class) for the scanner.Scanner to parse.
 * The scanner.Scanner will output the various lexemes in the input text, line by line.
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    /**
     * scanner.Scanner constructor for construction of a scanner that
     * uses an InputStream object for input.
     * Usage:
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * scanner.Scanner lex = new scanner.Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }
    /**
     * scanner.Scanner constructor for constructing a scanner that
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: scanner.Scanner lex = new scanner.Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
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
            int inp = in.read();
            if (inp == -1)
                eof = true;
            else currentChar = (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }


    /**
     * Checks whether currentChar and the parameter are equal
     * @param expected the character currentChar is being compared to
     * @throws ScanErrorException thrown if there is a file-reading error
     */
    private void eat(char expected) throws ScanErrorException
    {
        if(expected != currentChar)
        {
            throw new ScanErrorException("Illegal character: expected currentChar");
        }
        getNextChar();
    }

    /**
     * Checks if there are more characters to be parsed in the file
     * @return true if there are more characters in the file,
     *         false if the end-of-file has been reached
     */
    public boolean hasNext()
    {
        return currentChar != '.' && !eof;
    }

    /**
     * Checks if the given character is a digit (0-9)
     * @param input the character to be checked
     * @return true if the given character is a digit, false otherwise
     */
    public static boolean isDigit(char input)
    {
        return input >= 48 && input <= 57;
    }

    /**
     * Checks if the given character is a letter, without regard to case
     * @param input the character to be checked
     * @return true if the given character is a letter, false otherwise
     */
    public static boolean isLetter(char input)
    {
        return (input >= 65 && input <= 90) || (input >= 97 && input <= 122);
    }

    /**
     * Checks if the given character is a whitespace
     * @param input the character to be checked
     * @return true if the given character is a white space, false otherwise
     */
    public static boolean isWhiteSpace(char input)
    {
        return input == '\t' || input == '\n' || input == '\r' || input == ' ' || input == '\0';
    }


    private char[] operands = {'=','+','-','*','/','%','(',')',';','<', '>',':',','};
    private HashSet<Character> special = new HashSet<>();

    /**
     * All non letter, digit, or operands are considered special
     * @param input the character to be checked
     * @return true if the input character has been seen before, false otherwise
     */
    private boolean isSpecial(char input)
    {
        return special.contains(input);
    }

    /**
     * Checks if the given character is an operand
     * @param input the character to be checked
     * @return true if the given character is an operand, false otherwise
     */
    private boolean isOperand(char input)
    {
        for (int i = 0; i < operands.length; i++)
        {
            if (input == operands[i])
            {
                return true;
            }
        }
        return false;
    }

    /**
     * reads in a number using its regex definition: digit(digit)*
     * @return the number read in, as a String
     * @throws ScanErrorException thrown if the regex definition is not satisfied
     */
    private String scanNumber() throws ScanErrorException
    {
        String lex = "";
        if (isDigit(currentChar))
        {
            lex += currentChar;
            eat(currentChar);
            while(hasNext() && isDigit(currentChar))
            {
                lex += currentChar;
                eat(currentChar);
            }
        }
        else
        {
            throw new ScanErrorException("Expected valid number");
        }
        return lex;
    }

    /**
     * reads in an identifier using its regex definition: letter(letter|digit)*
     * @return the identifier
     * @throws ScanErrorException thrown if the regex definition is not satisfied
     */
    private String scanIdentifier() throws ScanErrorException
    {
        String lex = "";

        if (isLetter(currentChar))
        {
            lex += currentChar;
            eat(currentChar);
            while(hasNext() && (isDigit(currentChar) || isLetter(currentChar) || currentChar == '_') )
            {
                lex += currentChar;
                eat(currentChar);
            }
        }
        else
        {
            throw new ScanErrorException("Expected valid identifier");
        }
        return lex;
    }


    /**
     * Reads in an operand using its regex definition: [‘=’ ‘+’ ‘-‘ ‘*’ ‘/’ ‘%’ ‘(‘ ‘)’]
     * @return the operand read in
     * @throws ScanErrorException thrown if the regex definition is not satisfied
     */
    private String scanOperand() throws ScanErrorException
    {
        char c = currentChar;

        String lex = "" +currentChar;

        if(!isOperand(currentChar))
        {
            throw new ScanErrorException("Expected valid operand");
        }

        if(currentChar == '>' || currentChar == '<' || currentChar == ':')
        {
            eat(currentChar);
            if(c == '<' && currentChar == '>')
            {
                eat(currentChar);
                return "<>";
            }
            if(currentChar == '=')
            {
                eat(currentChar);
                return c + "=";
            }

        }
        else
        {
            eat(currentChar);
        }

        return lex;
    }

    /**
     * Scans in the next token based on the regex definitions of the different tokens
     * @return the token parsed in
     * @throws ScanErrorException thrown if none of the regex definitions were matched
     */
    public String nextToken() throws ScanErrorException
    {
        while (hasNext() && isWhiteSpace(currentChar) || isSpecial(currentChar))
        {
            eat(currentChar);
        }

        String tok = "";

        if(currentChar == '.')
        {
            return "eof: parsing complete.";
        }

        if(isDigit(currentChar))
        {
            tok = scanNumber();

        }
        else if (isLetter(currentChar))
        {
            tok = scanIdentifier();

        }
        else if (isOperand(currentChar))
        {
            tok = scanOperand();

        }
        else
        {
            special.add(currentChar);
            /* comment out the line below for special characters functionality */
            //throw new scanner.ScanErrorException("Expected valid token");
        }

//        if(tok.equals(""))
//            System.out.println("EMPTY TOKEN " + currentChar);
        return tok;
    }
}
