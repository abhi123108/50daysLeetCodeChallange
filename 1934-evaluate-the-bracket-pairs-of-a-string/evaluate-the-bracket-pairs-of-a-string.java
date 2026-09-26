import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Build lookup map for knowledge pairs
        Map<String, String> map = new HashMap<>(knowledge.size());
        for (List<String> entry : knowledge) {
            map.put(entry.get(0), entry.get(1));
        }

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = 0;

        while (i < n) {
            char c = s.charAt(i);

            if (c == '(') {
                int start = i + 1;
                while (i < n && s.charAt(i) != ')') {
                    i++;
                }
                // Extract key between '(' and ')'
                String key = s.substring(start, i);
                sb.append(map.getOrDefault(key, "?"));
                i++; // Skip closing ')'
            } else {
                sb.append(c);
                i++;
            }
        }

        return sb.toString();
    }
}