import java.util.ArrayList;

public class Main {

    public static void main (String[] args){

        ArrayList<Photo> photos = Util.readFile("b_lovely_landscapes.txt");
        ArrayList<Photo> slideShow = new ArrayList<>();

        int maxId = 0;

        while (photos.size() > 0) {

            Photo photo = photos.get(maxId);
            photos.remove(maxId);
            slideShow.add(photo);

            int max = 0;
            maxId = 0;

            for (int i = 0; i < photos.size(); i++) {
                Photo p1 = photo;
                Photo p2 = photos.get(i);

                int min = Util.calcInterestFactor(p1, p2);

                if (min >= max) {
                    max = min;
                    maxId = i;
                }
            }

        }

        for (Photo g: slideShow) {

            System.out.println(g.id);

        }

        /*
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("A");

        Util.writeFile("test.txt", strings);*/

    }
}