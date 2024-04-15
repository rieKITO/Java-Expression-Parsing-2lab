package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {
    private final String expression;

    public ExpressionParser(String expression) {
        this.expression = expression;
    }

    public List<String> parse() {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!Character.isWhitespace(c)) {
                if (Character.isDigit(c) || c == '.') {
                    token.append(c);
                } else if (Character.isLetter(c)) {
                    token.append(c);
                } else if (isOperator(c) || c == '(' || c == ')') {
                    if (!token.isEmpty()) {
                        tokens.add(token.toString());
                        token.setLength(0);
                    }
                    tokens.add(String.valueOf(c));
                } else throw new IllegalArgumentException("Incorrect symbol in the expression: " + c);
            }
        }

        if (!token.isEmpty())
            tokens.add(token.toString());

        return tokens;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
