package parser;
import ast.Expression;
import ast.Number;
import ast.Statement;
import ast.Variable;
import ast.*;
import scanner.ScanErrorException;
import scanner.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The Parser class parses the tokens read in by the Scanner
 * and evaluates the statements parsed in
 * @author Shounak Ghosh
 * @version 9.26.2019
 */
public class Parser
{
    private Scanner myScanner;
    private String currentToken;

    /**
     * Constructor, creates Parser objects
     * @param in The Scanner used to read in the HHL code
     * @throws ScanErrorException thrown if an invalid/unexpected token is parsed in
     */
    public Parser(Scanner in) throws ScanErrorException
    {
        myScanner = in;
        currentToken = myScanner.nextToken();
    }

    /**
     * Checks whether currentChar and the parameter are equal
     * @param expected the character currentChar is being compared to
     * @throws ScanErrorException thrown if an invalid/unexpected token is parsed in
     */
    private void eat(String expected) throws ScanErrorException // nom nom nom
    {
        if(!expected.equals(currentToken))
        {
            throw new IllegalArgumentException("Expected \""
                    + expected + "\", Found \"" + currentToken + "\"");
        }
        if(myScanner.hasNext())
        {
            currentToken = myScanner.nextToken();
        }
    }

    /**
     * Handles parsing the grammar which includes procedures
     * @return The Program object created after parsing
     * @throws ScanErrorException thrown if an invalid/unexpected token is parsed in
     */
    public Program parseProgram() throws ScanErrorException
    {
        HashSet<String> variableNames = new HashSet<String>();
        ArrayList<ProcedureDeclaration> procedureDeclarations;
        procedureDeclarations = new ArrayList<ProcedureDeclaration>();
        do
        {
            if(currentToken.equals("VAR"))
            {
                eat("VAR");
                variableNames.add(currentToken);
                eat(currentToken);
                while (currentToken.equals(","))
                {
                    eat(",");
                    variableNames.add(currentToken);
                    eat(currentToken);
                }
                eat(";");
            }
        }
        while (currentToken.equals("VAR"));


        do
        {
            if(currentToken.equals("PROCEDURE"))
            {
                eat(currentToken); // PROCEDURE
                String name = currentToken;
                eat(currentToken); // name
                eat("(");
                ArrayList<String> param = new ArrayList<String>();
                if (!currentToken.equals(")"))
                {
                    param.add(currentToken);
                    eat(currentToken);
                    while (currentToken.equals(","))
                    {
                        eat(",");
                        param.add(currentToken);
                        eat(currentToken);
                    }
                }
                eat(")");
                eat(";");
                ArrayList<String> localVariables = new ArrayList<String>();
                // parse local variables
                do
                {
                    if(currentToken.equals("VAR"))
                    {
                        eat("VAR");
                        localVariables.add(currentToken);
                        eat(currentToken);
                        while (currentToken.equals(","))
                        {
                            eat(",");
                            localVariables.add(currentToken);
                            eat(currentToken);
                        }
                        eat(";");
                    }
                }
                while (currentToken.equals("VAR"));
                Statement s = parseStatement();
                procedureDeclarations.add(new ProcedureDeclaration(name, s, param,localVariables));
            }
        }
        while (currentToken.equals("PROCEDURE"));
        Statement last = parseStatement();

        return new Program(variableNames,procedureDeclarations,last);
    }


    /**
     * Parses in WHILE, IF,WRITELN, BEGIN/END, and variable statements
     * @return the Statement parsed in
     * @throws ScanErrorException thrown if an invalid/unexpected token is parsed in
     */
    public Statement parseStatement() throws ScanErrorException
    {
        if(currentToken.equals("WHILE"))
        {
            eat("WHILE");
            Expression left = parseExpression();
            String cond = currentToken;
            eat(currentToken);
            Expression right = parseExpression();
            Condition c = new Condition(cond,left,right);
            eat("DO");
            Statement s = parseStatement();
            return new While(c,s);
        }
        else if(currentToken.equals("IF"))
        {
            eat("IF");
            Expression left = parseExpression();
            String cond = currentToken;
            eat(currentToken);
            Expression right = parseExpression();
            Condition c = new Condition(cond,left,right);
            eat("THEN");
            Statement s = parseStatement();
            return new If(c,s);
        }
        else if(currentToken.equals("BEGIN"))
        {
            eat("BEGIN");
            ArrayList<Statement> statements = new ArrayList<Statement>();
            while (!currentToken.equals("END"))
            {
                statements.add(parseStatement());
            }
            eat("END");
            eat(";");
            return new Block(statements);
        }
        else if(currentToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression expr = parseExpression();
            eat(")");
            eat(";");
            return new Writeln(expr);
        }
        else
        {
            String id = currentToken;
            eat(currentToken); // name
            eat(":=");
            Expression expr = parseExpression();
            eat(";");
            return new Assignment(id,expr);
        }
    }

    /**
     * Parses and evaluates mathematical expressions involving addition and subtraction
     * @return the Expression parsed
     * @throws ScanErrorException thrown if an invalid/unexpected token is parsed in
     */
    private Expression parseExpression() throws ScanErrorException
    {
        Expression ans = parseTerm();
        while (currentToken.equals("+") || currentToken.equals("-"))
        {
            if(currentToken.equals("+"))
            {
                eat(currentToken);
                ans = new BinOp("+", ans, parseTerm());
            }
            else // subtraction
            {
                eat(currentToken);
                ans = new BinOp("-", ans, parseTerm());
            }
        }
        return ans;
    }

    /**
     * Parses and evaluates mathematical terms involving division and multiplication
     * @return the Expression parsed in
     * @throws ScanErrorException thrown if an invalid/unexpected token is parsed in
     */
    private Expression parseTerm() throws ScanErrorException
    {
        Expression ans = parseFactor();
        // insert code here
        while (currentToken.equals("*") || currentToken.equals("/"))
        {
            if(currentToken.equals("*"))
            {
                eat(currentToken);
                ans = new BinOp("*",ans,parseFactor());
            }
            else // division
            {
                eat(currentToken);
                ans = new BinOp("/",ans, parseFactor());
            }
        }
        return ans;
    }

    /**
     * Parses in integers, variables and expressions
     * @return The Expression parsed in
     * @throws ScanErrorException thrown if an invalid/unexpected token is parsed in
     */
    private Expression parseFactor() throws ScanErrorException
    {
        Expression temp;
        if(currentToken.equals("-"))
        {
            eat(currentToken);

            return new BinOp("*",new Number(-1),parseFactor());
        }
        else if(currentToken.equals("("))
        {
            eat("(");
            temp = parseExpression();
            eat(")");
            return temp;
        }
        else if(myScanner.isDigit(currentToken.toCharArray()[0])) // kinda dirty
        {
            return parseNumber();
        }
        else // a string is read, either procedure call or variable
        {
            String name = currentToken;
            eat(currentToken);
            if(currentToken.equals("(")) // procedure call
            {
                eat("(");
                ArrayList<Expression> param = new ArrayList<Expression>();
                if (!currentToken.equals(")"))
                {
                    param.add(parseExpression());
                    while (currentToken.equals(","))
                    {
                        eat(",");
                        param.add(parseExpression());
                    }
                }
                eat(")");
                temp = new ProcedureCall(name, param);
                return temp;
            }
            else // variable
            {
                return new Variable(name);
            }
        }
    }

    /**
     * Precondition: current token is a number, represented as a String
     * Parses in a number, a NumberFormatException error is thrown if
     * the precondition is not met-
     * @return the numerical value parsed in
     * @throws ScanErrorException thrown if an invalid/unexpected token is parsed in
     */
    private Number parseNumber() throws ScanErrorException
    {
        int n = Integer.parseInt(currentToken); // can screw up
        eat(currentToken);
        return new Number(n);
    }

}