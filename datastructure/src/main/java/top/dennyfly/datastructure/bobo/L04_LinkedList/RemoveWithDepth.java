package top.dennyfly.datastructure.bobo.L04_LinkedList;

import java.util.Objects;

/**
 * @author DennyFly
 * @since 2021/9/15 13:31
 */
public class RemoveWithDepth {

    public ListNode<Integer> removeElements(ListNode<Integer> head, int val, int depth) {

        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if(head == null){
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode<Integer> res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode<Integer> ret;
        if (Objects.equals(head.val,val)) {
            head.next = res;
            ret = head;
        } else {
            ret = res;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);

        return ret;
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {

        Integer[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode<Integer> head = new ListNode<Integer>(nums);
        System.out.println(head);

        ListNode<Integer> res = (new RemoveWithDepth()).removeElements(head, 6, 0);
        System.out.println(res);
    }

}
