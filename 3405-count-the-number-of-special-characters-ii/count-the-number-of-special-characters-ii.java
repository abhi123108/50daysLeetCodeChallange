class Solution {
    public int numberOfSpecialChars(String word) {

        int[] lastSml = new int[26];
        int[] firstCap = new int[26];

        // initialize
        for(int i = 0; i < 26; i++) {
            lastSml[i] = -1;
            firstCap[i] = -1;
        }

        // store last lowercase index
        // and first uppercase index
        for(int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            if(ch >= 'a' && ch <= 'z') {

                lastSml[ch - 'a'] = i;

            } else {

                int idx = ch - 'A';

                if(firstCap[idx] == -1) {
                    firstCap[idx] = i;
                }
            }
        }

        int count = 0;

        for(int i = 0; i < 26; i++) {

            // lowercase and uppercase dono present hone chahiye
            // aur last lowercase < first uppercase
            if(lastSml[i] != -1 &&
               firstCap[i] != -1 &&
               lastSml[i] < firstCap[i]) {

                count++;
            }
        }

        return count;
    }
}