import org.example.ExpressionEvaluator;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

public class ExpressionEvaluatorTest {

    private Map<String, Double> variables;

    @Before
    public void setUp() {
        Map<String, Double> variables = new HashMap<>();
    }

    @Test
    public void testSimpleExpression() {
        variables.put("x", 5.0);
        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);

        double result = evaluator.evaluate(Arrays.asList("2", "*", "(", "3", "+", "x", ")"));
        assertEquals(16.0, result);
    }

    @Test
    public void testExpressionWithVariables() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 5.0);
        variables.put("y", 2.0);
        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);

        double result = evaluator.evaluate(Arrays.asList("x", "*", "y"));
        assertEquals(10.0, result);
    }

    @Test
    public void testInvalidExpression() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 5.0);
        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);

        assertThrows(IllegalArgumentException.class, () -> {
            evaluator.evaluate(Arrays.asList("2", "*", "(", "3", "+", "x"));
        });
    }
}
