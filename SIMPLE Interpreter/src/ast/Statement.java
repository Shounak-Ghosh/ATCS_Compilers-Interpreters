package ast;

import environment.*;

/**
 * The abstract class Statement represents the
 * different kinds of statements that can be parsed in: display, assign, read
 * @author Shounak Ghosh
 * @version 12.21.2019
 */
public abstract class Statement
{
    /**
     * Executes a given statement
     * @param env the environment in which variables are stored
     */
    public abstract void exec(Environment env);
}
