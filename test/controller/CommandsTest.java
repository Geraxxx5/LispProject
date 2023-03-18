/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import java.util.List;
import modelo.FunctionModel;
import modelo.VariableModel;
import org.junit.Test;
import static org.junit.Assert.*;
import parser.ParserExpression;

public class CommandsTest {
    
    /**
     * Test of evaluate method, of class EvaluateExpression for Quote command.
     */
    @Test
    public void testQuoteCommand() {
        System.out.println("Quote Command");
        List<Object> expr = Arrays.asList("quote",Arrays.asList("+",5,2));
        VariableModel variable = null;
        FunctionModel functions = null;
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = Arrays.asList("+",5,2);
        Object result = instance.evaluate(expr, variable, functions);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of evaluate method, of class EvaluateExpression for Atom command.
     */
    @Test
    public void testAtomCommand() {
        System.out.println("Atom Command");
        List<Object> expr = Arrays.asList("atom",Arrays.asList(5,2,1));
        VariableModel variable = null;
        FunctionModel functions = null;
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = "Nil";
        Object result = instance.evaluate(expr, variable, functions);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluate method, of class EvaluateExpression for Setq command.
     */
    @Test
    public void testSetqCommand() {
        System.out.println("setq Command");
        List<Object> expr = Arrays.asList(Arrays.asList("setq","x",10));
        VariableModel variable = new VariableModel();
        FunctionModel functions = null;
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = Arrays.asList("x","10");
        instance.evaluate(expr, variable, functions);
        Object result = Arrays.asList("x",variable.lastValue("x"));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluate method, of class EvaluateExpression for defvar command.
     */
    @Test
    public void testDefVarCommand() {
        System.out.println("defvar Command");
        List<Object> expr = Arrays.asList(Arrays.asList("defvar","x",10));
        VariableModel variable = new VariableModel();
        FunctionModel functions = null;
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = Arrays.asList("x","10");
        instance.evaluate(expr, variable, functions);
        Object result = Arrays.asList("x",variable.lastValue("x"));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluate method, of class EvaluateExpression for cond command.
     */
    @Test
    public void testCondCommand() {
        System.out.println("Cond Command");
        List<Object> expr = ParserExpression.readFileExpression("(defvar x 10) (defvar y 20) (cond ((> x y) x) (t y))", false);
        VariableModel variable = new VariableModel();
        FunctionModel functions = null;
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = "20.0";
        Object result = instance.evaluate(expr, variable, functions);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluate method, of class EvaluateExpression for cond command.
     */
    @Test
    public void testListCommand() {
        System.out.println("List Command");
        List<Object> expr = ParserExpression.readFileExpression("(list (' a) (' (a b)))", false);
        VariableModel variable = new VariableModel();
        FunctionModel functions = null;
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = Arrays.asList("a",Arrays.asList("a","b"));
        Object result = instance.evaluate(expr, variable, functions);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluate method, of class EvaluateExpression for defun command.
     */
    @Test
    public void testDefunCommand() {
        System.out.println("Defun Command");
        List<Object> expr = ParserExpression.readFileExpression("(defun convertirCelsius (x) (* (- x 32) (/ 5 9)))", false);
        VariableModel variable = new VariableModel();
        FunctionModel functions = new FunctionModel();
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = Arrays.asList("convertirCelsius",Arrays.asList("x"),
                Arrays.asList("*",Arrays.asList("-","x",32),Arrays.asList("/",5,9)));
        instance.evaluate(expr, variable, functions);
        List<Object> result = Arrays.asList("convertirCelsius",functions.getParams("convertirCelsius"),functions.getCondition("convertirCelsius"));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluate method, of class EvaluateExpression for function call.
     */
    @Test
    public void testFunctionsEvaluate() {
        System.out.println("Call functions");
        List<Object> expr = ParserExpression.readFileExpression("(defun convertirCelsius (x) (* (- x 32) (/ 5 9))) (convertirCelsius 50)", false);
        VariableModel variable = new VariableModel();
        FunctionModel functions = new FunctionModel();
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = 10.0;
        Object result = instance.evaluate(expr, variable, functions);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
}