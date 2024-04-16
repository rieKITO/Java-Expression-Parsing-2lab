import org.example.ExpressionEvaluator;
import org.example.ExpressionParser;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

public class ExpressionParserTest {

    @Test
    public void testParseSimpleExpression() {
        String expression = "2 * (3 + x)";
        ExpressionParser parser = new ExpressionParser(expression);

        List<String> tokens = parser.parse();

        assertEquals(7, tokens.size());
        assertEquals("2", tokens.get(0));
        assertEquals("*", tokens.get(1));
        assertEquals("(", tokens.get(2));
        assertEquals("3", tokens.get(3));
        assertEquals("+", tokens.get(4));
        assertEquals("x", tokens.get(5));
        assertEquals(")", tokens.get(6));
    }

    @Test
    public void testParseExpressionWithSpaces() {
        String expression = "2 * ( 3 + x )";
        ExpressionParser parser = new ExpressionParser(expression);

        List<String> tokens = parser.parse();

        assertEquals(7, tokens.size());
        assertEquals("2", tokens.get(0));
        assertEquals("*", tokens.get(1));
        assertEquals("(", tokens.get(2));
        assertEquals("3", tokens.get(3));
        assertEquals("+", tokens.get(4));
        assertEquals("x", tokens.get(5));
        assertEquals(")", tokens.get(6));
    }

    @Test
    public void testInvalidExpression() {
        String expression = "2 * (3 + x!";
        ExpressionParser parser = new ExpressionParser(expression);

        assertThrows(IllegalArgumentException.class, parser::parse);
    }
}
