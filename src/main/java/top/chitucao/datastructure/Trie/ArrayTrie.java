package top.chitucao.datastructure.Trie;

/**
 * @author DennyFly
 * @since 2021/10/15 13:27
 * 基于单词数组实现的字典树
 * <p>
 * 这里的单词数组只存储字母
 * <p>
 * 基本操作
 * 1.添加一个单词；
 * 2.判断是否包含某个单词；
 * 3.判断是否包含某个前缀；
 * <p>
 * 内部方法
 */
public class ArrayTrie {

    class Node {
        private boolean isWord;
        private Node[] next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new Node[6666];
        }

        public Node() {
            this(false);
        }
    }

    private int size;
    private Node root;

    public ArrayTrie() {
        this.size = 0;
        this.root = new Node();
    }

    public void add(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new Node();
            }
            cur = cur.next[c - 'a'];
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
            if (cur.next[c - 'a'] == null) {
                return false;
            }
            cur = cur.next[c - 'a'];
        }
        return cur.isWord;
    }
}
