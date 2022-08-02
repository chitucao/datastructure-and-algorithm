package top.chitucao.datastructure.L10_Trie;

import java.util.HashMap;

/**
 * @author DennyFly
 * @since 2021/10/15 13:13
 * 基于hashmap实现的字典树
 * <p>
 * 基本操作
 * 1.添加一个单词；
 * 2.判断是否包含某个单词；
 * 3.判断是否包含某个前缀；
 * <p>
 * <p>
 * 内部方法
 */
public class HashMapTrie {

    class Node {

        private boolean isWord;
        private HashMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private int size;
    private Node root;

    public HashMapTrie() {
        this.size = 0;
        this.root = new Node();
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }
}
