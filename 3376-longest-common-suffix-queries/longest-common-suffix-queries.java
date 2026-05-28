class Solution {

    class TrieNode {

        TrieNode[] child = new TrieNode[26];

        int idx = -1;
        int len = Integer.MAX_VALUE;
    }

    TrieNode root = new TrieNode();

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

    
        for(int i = 0; i < wordsContainer.length; i++) {

            String word = new StringBuilder(wordsContainer[i])
                          .reverse()
                          .toString();

            insert(word, i, wordsContainer[i].length());
        }

        int[] ans = new int[wordsQuery.length];

        for(int i = 0; i < wordsQuery.length; i++) {

            String word = new StringBuilder(wordsQuery[i])
                          .reverse()
                          .toString();

            ans[i] = search(word);
        }

        return ans;
    }

    void insert(String word, int idx, int len) {

        TrieNode node = root;

        // update root answer
        if(len < node.len) {
            node.len = len;
            node.idx = idx;
        }

        for(char ch : word.toCharArray()) {

            int c = ch - 'a';

            if(node.child[c] == null) {
                node.child[c] = new TrieNode();
            }

            node = node.child[c];

            // shortest length store karo
            if(len < node.len) {
                node.len = len;
                node.idx = idx;
            }
        }
    }

    int search(String word) {

        TrieNode node = root;

        for(char ch : word.toCharArray()) {

            int c = ch - 'a';

            if(node.child[c] == null) {
                break;
            }

            node = node.child[c];
        }

        return node.idx;
    }
}