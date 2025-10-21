import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        SkipList skipList = new SkipList();
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        skipList.insert(values);
        System.out.println(skipList);
    }




}
