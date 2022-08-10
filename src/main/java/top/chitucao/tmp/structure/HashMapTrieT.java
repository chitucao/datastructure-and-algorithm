package top.chitucao.tmp.structure;

import java.util.HashMap;

/**
 * @author chitucao
 * @since 2022/8/10 13:26
 */
public class HashMapTrieT implements Trie {

    class Node {
        boolean isWord;
        HashMap<Character, Node> next;

        public Node() {
            this.isWord = false;
            this.next = new HashMap<>();
        }
    }

    Node root;
    int size;

    public HashMapTrieT() {
        this.root = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return this.size;
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
        Node cur = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    @Override
    public boolean isPrefix(String word) {
        Node cur = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new HashMapTrieT();
        trie.add("hello");
        trie.add("hellotom");
        System.out.println("size：" + trie.getSize());
        System.out.println("contains：" + trie.contains("hello"));
        System.out.println("contains：" + trie.contains("hell"));
        System.out.println("prefxi："+trie.isPrefix("hell"));
        System.out.println("prefix：" + trie.isPrefix("tom"));
    }
}
