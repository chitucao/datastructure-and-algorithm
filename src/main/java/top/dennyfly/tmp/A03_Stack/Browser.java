package top.dennyfly.tmp.A03_Stack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.dennyfly.datastructure.L03_Stacks_and_Queues.C01_stack.LinkedListStack;
import top.dennyfly.datastructure.L03_Stacks_and_Queues.C01_stack.Stack;

/**
 * @author dennyfly
 * @since 2021/11/26 14:18
 * 模拟浏览器功能
 * #利用两个栈，一个前进栈，一个后退栈
 */
public class Browser {

    @Getter
    @AllArgsConstructor
    enum Operation {
        // 打开新网页
        OPEN,
        // 前进
        GO_FORWARD,
        // 后退
        GO_BACK;
    }

    private String currentPage;
    private Stack<String> forwardStack;
    private Stack<String> backStack;

    public Browser() {
        this.forwardStack = new LinkedListStack<>();
        this.backStack = new LinkedListStack<>();
    }

    // 打开一个地址
    public void open(String url) {
        // 如果当前地址不为空，当前地址加入历史地址
        if (currentPage != null) {
            backStack.push(currentPage);
            forwardStack.clear();
        }
        showUrl(Operation.OPEN, url);
    }

    // 后退
    public void goBack() {
        if (canGoBack()) {
            forwardStack.push(currentPage);
            String url = backStack.pop();
            showUrl(Operation.GO_BACK, url);
            return;
        }
        throw new IllegalArgumentException("cannot go back,no pages behind");
    }

    // 前进
    public void goForward() {
        if (canGoForward()) {
            backStack.push(currentPage);
            String url = forwardStack.pop();
            showUrl(Operation.GO_FORWARD, url);
            return;
        }
        throw new IllegalArgumentException("cannot go forward,no pages ahead");
    }

    // 是否能够前进
    private boolean canGoForward() {
        return !forwardStack.isEmpty();
    }

    // 是否能够回退
    private boolean canGoBack() {
        return !backStack.isEmpty();
    }

    // 打印浏览器地址栏显示
    private void showUrl(Operation operation, String url) {
        this.currentPage = url;
        System.out.println(operation.name() + "  page ==" + url);
    }
}
