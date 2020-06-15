package scanner;
import java.io.*;
/**
 * Tests the scanner.Scanner class via test files
 * @author Shounak Ghosh
 * @version 05/17/2018
 */
public class ScannerTester
{
    /**
     * Tests the scanner.Scanner by reading from the file "ScannerText.txt" and printing each token
     * @param str array of String objects
     * @throws FileNotFoundException thrown if there is an I/O error
     * @throws ScanErrorException thrown if an invalid token is present
     */
    public static void main(String[] str) throws FileNotFoundException, ScanErrorException
    {
        InputStream inputstream = new FileInputStream("ScannerText.txt");
        Scanner scanner = new Scanner(inputstream);

        while (scanner.hasNext())
        {
            System.out.println(scanner.nextToken());
        }
    }
}
