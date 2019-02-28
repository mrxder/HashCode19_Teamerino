import java.util.ArrayList;

public class Main {

    public static void main (String[] args){

        ArrayList<Photo> photos = Util.readFile("b_lovely_landscapes.txt");

        for(Photo p : photos){
            System.out.println(p);
        }

        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("A");

        Util.writeFile("test.txt", strings);

    }
}