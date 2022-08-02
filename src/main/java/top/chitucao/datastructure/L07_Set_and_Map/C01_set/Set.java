package top.chitucao.datastructure.L07_Set_and_Map.C01_set;

public interface Set<E> {

    void add(E e);

    boolean contains(E e);

    void remove(E e);

    int getSize();

    boolean isEmpty();
}
