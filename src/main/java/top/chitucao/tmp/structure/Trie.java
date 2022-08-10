package top.chitucao.tmp.structure;

/**
 * @author chitucao
 * @since 2022/8/10 10:55
 */
public interface Trie {

    int getSize();

    void add(String word);

    boolean contains(String word);

    boolean isPrefix(String word);
}
