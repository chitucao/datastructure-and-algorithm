package lv1simple;

import javafx.util.Pair;
import temp.TreeNode;
import top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C02_queue.LinkedListQueue;
import top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C02_queue.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Q102 有效的括号
 * <p>
 * difficulty: medium
 * <p>
 * think1 队列
 *
 * @link { https://leetcode-cn.com/problems/valid-parentheses/}
 */
public class Q102_M_binary_tree_level_order_traversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        /**
         * list1：level
         * list2：element
         */
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        /**
         * pair1：element
         * pair2：level
         */
        Queue<Pair<TreeNode, Integer>> queue = new LinkedListQueue<>();

        queue.enqueue(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> cur = queue.dequeue();
            TreeNode node = cur.getKey();
            Integer level = cur.getValue();

            if (level == res.size()) {
                res.add(new ArrayList<>());
            }

            res.get(level).add(node.val);

            if (node.left != null) {
                queue.enqueue(new Pair<>(node.left, level + 1));
            }
            if (node.right != null) {
                queue.enqueue(new Pair<>(node.right, level + 1));
            }
        }

        return res;
    }


}
