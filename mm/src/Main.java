import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static final String fileA = "b_lovely_landscapes.txt";

    public static void main(String[] args) {

        ArrayList<String> out = new ArrayList<String>();

        ArrayList<Photo> photos = Util.readFile("mm/"+fileA);

        Collections.sort(photos, new Comparator<Photo>() {
            @Override
            public int compare(Photo p2, Photo p1)
            {

                return p1.noOfTags - p2.noOfTags;
            }
        });


        for (Photo p : photos) {
            out.add(p.id+"");
        }

        ArrayList<String> outFile = new ArrayList<String>();

        outFile.add(out.size()+"");
        for(String s: out) {
            outFile.add(s);
        }


        Util.writeFile("bres.txt", outFile);








    }
}
