package top.dennyfly.datastructure.L02_Arrays;

import org.junit.Test;

public class Main {

    @Test
    public void testBasic() {
        Array<Integer> dynamicArray = new Array<>();
        for (int i = 0; i < 10; i++) {
            dynamicArray.addLast(i);
        }

        dynamicArray.removeLast();
        dynamicArray.removeFirst();

        System.out.println(dynamicArray);
    }
}
