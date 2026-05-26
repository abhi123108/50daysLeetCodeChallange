class Solution {
    public int numberOfSpecialChars(String word) {
        
        int count = 0;

        for(char ch = 'a'; ch <= 'z'; ch++) {

            boolean sml = false;
            boolean cap = false;

            for(int i = 0; i < word.length(); i++) {

                if(word.charAt(i) == ch) {
                    sml = true;
                }

                if(word.charAt(i) == (char)(ch - 32)) {
                    cap = true;
                }
            }

            if(sml && cap) {
                count++;
            }
        }

        return count;
    }
}