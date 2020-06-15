package scanner;

import java.io.*;
import java.lang.Character;
import java.util.Arrays;

public class MyScannerTester
{
    public static void main(String[] args) throws IOException, ScanErrorException
    {
        //InputStream in = new FileInputStream("test.txt");
        //System.out.println(scanOperand('%'));
//        System.out.println(isDigit('+'));
        System.out.println(isLetter('x'));


    }

    private static String scanOperand(char currentChar) throws ScanErrorException
    {
        char[] operands = {'=','+','-','*','/','%','(',')'};
        boolean found = false;

        for (int i = 0; i < operands.length; i++)
        {
            if (currentChar == operands[i])
            {
                found = true;
            }
        }

        if(!found)
        {
            throw new ScanErrorException("Expected operand");
        }

        return "" + currentChar;
    }

    public static boolean isDigit(char input)
    {
        return input - '0' > 0 && input - '0' <= 9;
    }

    public static boolean isLetter(char input)
    {
        int val = input;
        //System.out.println(val);
        return (val >= 65 && val <= 90) || (val >= 97 && val <= 122);
    }



}
