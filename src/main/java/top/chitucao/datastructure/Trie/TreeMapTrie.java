package top.chitucao.datastructure.Trie;

import java.util.TreeMap;

/**
 * @author DennyFly
 * @since 2021/10/14 16:34
 * 基于TreeMap实现的字典树
 * <p>
 * 基本操作
 * 1.添加一个单词；
 * 2.判断是否包含某个单词；
 * 3.判断是否包含某个前缀；
 * <p>
 * <p>
 * 内部方法
 */
public class TreeMapTrie {

    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }


    private Node root; // 根节点不保存数据
    private int size; // 这里指单词的数量

    public TreeMapTrie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    // 向trie中添加一个新的单词word
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        // 最后一个字符
        if (!cur.isWord) {
            cur.isWord = true;
            // 这里的size表示单词的数量而不是字符的数量
            size++;
        }
    }

    // 检查单词是否在trie中
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 需要判断到最后一个字符
        return cur.isWord;
    }

    // 判断是否以prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
