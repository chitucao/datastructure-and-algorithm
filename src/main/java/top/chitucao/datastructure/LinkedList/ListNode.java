package top.chitucao.datastructure.LinkedList;

/**
 * @author DennyFly
 * @since 2021/9/15 10:01
 * 不带虚拟头节点的链表
 * <p>
 * <p>
 * 基本操作
 * <p>
 * 内部方法
 */
public class ListNode<E> {

    public E val;

    public ListNode<E> next;

    public ListNode(E x) {
        this.val = x;
    }

    public ListNode(E[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr cannot be empty");
        }

        this.val = arr[0];
        ListNode<E> cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode<E>(arr[i]);
            cur = cur.next;
        }

        // 注意这里的this引用永远不会变，cur会变
        System.out.println(this.val);
        System.out.println(cur.val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ListNode：");
        ListNode<E> cur = this;
        while (cur != null) {
            sb.append(cur.val + "->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

}
