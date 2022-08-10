package top.chitucao.tmp.structure;

/**
 * @author chitucao
 * @since 2022/8/10 10:56
 */
public class ArrayTrieT implements Trie {

    class Node {
        boolean isWord;
        Node[] next;

        public Node() {
            isWord = false;
            next = new Node[6666];
        }
    }

    int size;
    Node root;

    public ArrayTrieT() {
        this.size = 0;
        this.root = new Node();
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
            Node node = cur.next[c - 'a'];
            if (node == null) {
                cur.next[c - 'a'] = new Node();
            }
            cur = cur.next[c - 'a'];
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
            if (cur.next[c - 'a'] == null) {
                return false;
            }
            cur = cur.next[c - 'a'];
        }
        return cur.isWord;
    }

    @Override
    public boolean isPrefix(String word) {
        Node cur = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (cur.next[c - 'a'] == null) {
                return false;
            }
            cur = cur.next[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new ArrayTrieT();
        trie.add("hello");
        trie.add("hellotom");
        System.out.println("size：" + trie.getSize());
        System.out.println("contains：" + trie.contains("hello"));
        System.out.println("contains：" + trie.contains("hell"));
        System.out.println("prefxi："+trie.isPrefix("hell"));
        System.out.println("prefix：" + trie.isPrefix("tom"));
    }
}
