package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gerax
 */
public class ParserExpression {
    
    /**
     *
     * @param fileRoute
     * @param isFile
     * @return List<Object>
     * 
     * Read the file and return the expression parse
     */
    public static List<Object> readFileExpression(String fileRoute, boolean isFile){
        if(isFile){
            FileReader arr;
            BufferedReader reader;
            try {
                arr = new FileReader(fileRoute); 
                if (arr.ready()) {
                        reader = new BufferedReader(arr); // read the file
                        String line;
                        String expressionToReturn = "";
                        while ((line = reader.readLine()) != null) {
                                expressionToReturn+=line;
                        }
                        
                        return parse(expressionToReturn.toLowerCase());
                } else {
                        throw new RuntimeException("File route dosn't exist");
                }
            } catch (Exception e) {
                    throw new RuntimeException("Unexpected Error: "+e);
            }
        }else{
            return parse(fileRoute);
        }
    }
    
    /**
     *
     * @param input
     * @return expression
     * Parse the expression
     */
    public static List<Object> parse(String input) {
        List<Object> result = new ArrayList<>();
        int i = 0;
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '(') {
                int end = findMatchingParen(input, i);
                result.add(parse(input.substring(i + 1, end)));
                i = end + 1;
            } else if (c == ')') {
                throw new IllegalArgumentException("Unexpected ')'");
            } else if (Character.isWhitespace(c)) {
                i++;
            } else {
                int end = findEndOfAtom(input, i);
                result.add(parseAtom(input.substring(i, end)));
                i = end;
            }
        }
        return result;
    }

    /**
     *
     * @param atom
     * @return Object
     * Return the real value of the string
     */
    private static Object parseAtom(String atom) {
        if (isInteger(atom)) {
            return Integer.parseInt(atom);
        } else if (isDouble(atom)) {
            return Double.parseDouble(atom);
        } else {
            return atom;
        }
    }

    /**
     *
     * @param input
     * @param start
     * @return positionOfLastParen
     * Return the matching paren of the parent sent
     */
    private static int findMatchingParen(String input, int start) {
        int depth = 0;
        for (int i = start; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
                if (depth == 0) {
                    return i;
                }
            }
        }
        throw new IllegalArgumentException("Unmatched '('");
    }

    /**
     *
     * @param input
     * @param start
     * @return positionOfendAtom
     * find out how far the atom exists
     */
    private static int findEndOfAtom(String input, int start) {
        int i = start;
        while (i < input.length() && !Character.isWhitespace(input.charAt(i)) && input.charAt(i) != ')') {
            i++;
        }
        return i;
    }

    /**
     *
     * @param s
     * @return isInteger
     */
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     * @param s
     * @return isDouble
     */
    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
