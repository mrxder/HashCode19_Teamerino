import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Main2 {

    public static void main(String[] args) {

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < 80000; i++) {
            strings.add("" + i);
        }

        Collections.shuffle(strings);

        for (String s: strings) {
            System.out.println(s);
        }

    }
}
