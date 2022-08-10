package top.chitucao.tmp.structure;

import java.util.TreeMap;

/**
 * @author chitucao
 * @since 2022/8/10 13:35
 */
public class TreeMapTrieT implements Trie {

    class Node {
        boolean isWord;
        TreeMap<Character, Node> next;

        public Node() {
            this.isWord = false;
            this.next = new TreeMap<>();
        }
    }

    int size;
    Node root;

    public TreeMapTrieT() {
        root = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void add(String word) {
        int n = word.length();
        Node cur = root;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            Node node = cur.next.get(c);
            if (node == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    @Override
    public boolean contains(String word) {
        int n = word.length();
        Node cur = root;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            Node node = cur.next.get(c);
            if (node == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    @Override
    public boolean isPrefix(String word) {
        int n = word.length();
        Node cur = root;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            Node node = cur.next.get(c);
            if (node == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
