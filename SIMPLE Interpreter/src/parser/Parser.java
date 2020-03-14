package parser;
import ast.Boolean;
import scanner.*;
import ast.*;
import ast.Numeral;

/**
 * Parses in tokens read by the scanner and generates the abstract syntax tree
 * that is evaluated upon execution
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public class Parser
{
    private Scanner myScanner;
    private String currentToken;

    /**
     * Constructor: creates Parser objects
     * @param scanner the Scanner used to read in the HHL code
     */
    public Parser(Scanner scanner)
    {
        myScanner = scanner;
        try
        {
            currentToken = myScanner.nextToken();
        }
        catch(Exception condition)
        {
            condition.printStackTrace();
        }
    }

    /**
     * Checks whether currentChar and the parameter are equal
     * @param expected the character currentChar is being compared to
     */
    private void eat(String expected)
    {
        if(!expected.equals(currentToken))
        {
            throw new IllegalArgumentException("Expected \""
                    + expected + "\", Found \"" + currentToken + "\"");
        }
        try
        {
            currentToken = myScanner.nextToken();
        }
        catch(Exception condition)
        {
            condition.printStackTrace();
        }
    }

    /**
     * Parses in a Statement, and then another Program
     * @return the Program object created after parsing
     */
    public Program parseProgram()
    {
        Statement s = parseStatement();
        Program child = null;
        if (!currentToken.equals("End of file: scanning complete."))
        {
            child = parseProgram();
        }
        return new Program(s, child);
    }

    /**
     * Parses in a Statement, and then a
     * Program beginning with a specific Statement type.
     * Used when parsing If and While structures.
     * @return the Program object created after parsing
     */
    private Program parseChildProgram()
    {
        Statement s = parseStatement();
        Program child = null;
        if(currentToken.equals("display")
                || currentToken.equals("assign")
                || currentToken.equals("while")
                || currentToken.equals("if"))
            child = parseChildProgram();
        return new Program(s, child);
    }


    /**
     * Parses in display, read, assign, if and while statements
     * @return the Statement object created after parsing
     */
    private Statement parseStatement()
    {
        switch (currentToken)
        {
            case "display":
                eat("display");
                Expression condition = parseExpression();
                if(currentToken.equals("read"))
                {
                    eat("read");
                    String varName = currentToken;
                    eat(currentToken);
                    return new Display(condition,new Read(varName));
                }
                return new Display(condition, null);
            case "assign":
                eat("assign");
                String varName = currentToken;
                eat(currentToken);
                eat("=");
                return new Assign(varName, parseExpression());
            case "if":
                eat("if");
                condition = parseExpression();
                eat("then");
                Program main = parseChildProgram();
                Program child = null;
                if(currentToken.equals("else"))
                {
                    eat("else");
                    child = parseChildProgram();
                }
                eat("end");
                return new If(condition, main, child);
            case "while":
                eat("while");
                condition = parseExpression();
                eat("do");
                child = parseChildProgram();
                eat("end");
                return new While(condition, child);
        }
        return null;
    }

    /**
     * Parses in an AddExpr, a relational operator, and an Expression
     * @return the Expression object created after parsing
     */
    private Expression parseExpression()
    {
        AddExpr left = parseAddExpr();
        String relop = "";
        Expression right = null;
        if(currentToken.equals("=")
                || currentToken.equals("<>")
                || currentToken.equals("<")
                || currentToken.equals(">")
                || currentToken.equals("<=")
                || currentToken.equals(">="))
        {
            relop = currentToken;
            eat(relop);
            right = parseExpression();
        }
        return new Expression(relop, left, right);
    }

    /**
     * Parses in a MultExpr, an addiion or subtraction operator, and an AddExpr
     * @return the AddExpr object created after parsing
     */
    private AddExpr parseAddExpr()
    {
        MultExpr left = parseMultExpr();
        String binOp = "";
        AddExpr right = null;
        if(currentToken.equals("+") || currentToken.equals("-"))
        {
            binOp = currentToken;
            eat(binOp);
            right = parseAddExpr();
        }
        return new AddExpr(binOp, left, right);
    }

    /**
     * Parses in a NegExpr, an division or multiplication operator, and a MultExpr
     * @return the MultExpr object created after parsing
     */
    private MultExpr parseMultExpr()
    {
        NegExpr left = parseNegExpr();
        String binOp = "";
        MultExpr right = null;
        if(currentToken.equals("*") || currentToken.equals("/"))
        {
            binOp = currentToken;
            eat(binOp);
            right = parseMultExpr();
        }
        return new MultExpr(binOp, left, right);
    }

    /**
     * Parses in negation operators followed by a Value
     * @return the NegExpr object created after parsing
     */
    private NegExpr parseNegExpr()
    {
        String binOp = "+";
        if(currentToken.equals("-"))
        {
            binOp = "-";
            eat("-");
        }
        else if(currentToken.equals("+"))
        {
            binOp = "+";
            eat("+");
        }
        return new NegExpr(parseValue(), binOp);
    }

    /**
     * Parses in integers, booleans, and expressions
     * @return the specific Value object created after parsing
     */
    private Value parseValue()
    {
        if(currentToken.equals("("))
        {
            eat("(");
            Expression exp1 = parseExpression();
            eat(")");
            return exp1;
        }
        else if (currentToken.equals("true"))
        {
            eat("true");
            return new Boolean(true);
        }
        else if(currentToken.equals("false"))
        {
            eat("false");
            return new Boolean(false);
        }
        else if(isNumber(currentToken))
        {
            return parseNumeral();
        }
        else
            return parseVariable();
    }

    /**
     * Current token is the name of the Variable object being created
     * @return the Variable object created after parsing
     */
    private Variable parseVariable()
    {
        String varName = currentToken;
        eat(varName);
        return new Variable(varName);
    }

    /**
     * Checks whether a given String is a number
     * @param num the String that is being checked for being a number
     * @return true if the given String is a number, false otherwise
     */
    private boolean isNumber(String num)
    {
        char first = num.toCharArray()[0];
        return first >= 48 && first <= 57;
    }


    /**
     * Precondition: current token is a number, represented as a String
     * @return the Numerical object created after parsing
     */
    private Numeral parseNumeral()
    {
        int varName = Integer.parseInt(currentToken);
        eat(currentToken);
        return new Numeral(varName);
    }


}