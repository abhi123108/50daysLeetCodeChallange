import java.util.*;

class Solution {
    private int index;
    private String s;

    public List<String> braceExpansionII(String expression) {
        this.s = expression;
        this.index = 0;

        Set<String> set = parseExpr();
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    // Expr -> Term (',' Term)*
    private Set<String> parseExpr() {
        Set<String> res = parseTerm();

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // consume ','
            Set<String> nextTerm = parseTerm();
            res.addAll(nextTerm);
        }

        return res;
    }

    // Term -> Factor+
    private Set<String> parseTerm() {
        Set<String> res = new HashSet<>();
        res.add(""); // Neutral element for cartesian product

        while (index < s.length() && s.charAt(index) != ',' && s.charAt(index) != '}') {
            Set<String> factor = parseFactor();
            res = multiply(res, factor);
        }

        return res;
    }

    // Factor -> letter+ | '{' Expr '}'
    private Set<String> parseFactor() {
        Set<String> res = new HashSet<>();

        if (s.charAt(index) == '{') {
            index++; // consume '{'
            res = parseExpr();
            index++; // consume '}'
        } else {
            StringBuilder sb = new StringBuilder();
            while (index < s.length() && Character.isLowerCase(s.charAt(index))) {
                sb.append(s.charAt(index));
                index++;
            }
            res.add(sb.toString());
        }

        return res;
    }

    // Cartesian product of two string sets
    private Set<String> multiply(Set<String> setA, Set<String> setB) {
        Set<String> res = new HashSet<>();
        for (String a : setA) {
            for (String b : setB) {
                res.add(a + b);
            }
        }
        return res;
    }
}