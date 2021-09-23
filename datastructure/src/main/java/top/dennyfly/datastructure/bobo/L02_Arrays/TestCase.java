package top.dennyfly.datastructure.bobo.L02_Arrays;

public class TestCase {

    public static void main(String[] args) {
        Array<Integer> dynamicArray = new Array<>();
        for (int i = 0; i <10 ; i++) {
            dynamicArray.addLast(i);
        }

        dynamicArray.removeLast();
        dynamicArray.removeFirst();

        System.out.println(dynamicArray);
    }
}
