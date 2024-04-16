import org.example.ExpressionEvaluator;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ExpressionEvaluatorTest {

    @Test
    public void testSimpleExpression() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 5.0);
        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);

        double result = evaluator.evaluate(Arrays.asList("2", "*", "(", "3", "+", "x", ")"));
        assertEquals(16.0, result);
    }
}
