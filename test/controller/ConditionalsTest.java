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

public class ConditionalsTest {
    /**
     * Test of evaluate method, of class EvaluateExpression for equals conditional.
     */
    @Test
    public void testEqualConditional() {
        System.out.println("Equals conditional");
        List<Object> expr = ParserExpression.readFileExpression("(= 5 5)", false);
        VariableModel variable = new VariableModel();
        FunctionModel functions = new FunctionModel();
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = "T";
        Object result = instance.evaluate(expr, variable, functions);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluate method, of class EvaluateExpression for equals conditional.
     */
    @Test
    public void testEqualWordConditional() {
        System.out.println("Equals 2 conditional");
        List<Object> expr = ParserExpression.readFileExpression("(equal 5 5)", false);
        VariableModel variable = new VariableModel();
        FunctionModel functions = new FunctionModel();
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = "T";
        Object result = instance.evaluate(expr, variable, functions);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluate method, of class EvaluateExpression for equals conditional.
     */
    @Test
    public void testGreaterThanConditional() {
        System.out.println("Greater Than conditional");
        List<Object> expr = ParserExpression.readFileExpression("(> 5 2)", false);
        VariableModel variable = new VariableModel();
        FunctionModel functions = new FunctionModel();
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = "T";
        Object result = instance.evaluate(expr, variable, functions);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of evaluate method, of class EvaluateExpression for equals conditional.
     */
    @Test
    public void testLessThanConditional() {
        System.out.println("Less Than conditional");
        List<Object> expr = ParserExpression.readFileExpression("(< 5 10)", false);
        VariableModel variable = new VariableModel();
        FunctionModel functions = new FunctionModel();
        EvaluateExpression instance = new EvaluateExpression();
        Object expResult = "T";
        Object result = instance.evaluate(expr, variable, functions);
        assertEquals(expResult, result);
    }
}
