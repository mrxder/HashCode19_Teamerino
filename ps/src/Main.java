import java.util.ArrayList;

public class Main {

    public static void main (String[] args){

        String file = "c_memorable_moments.txt";

        ArrayList<Photo> photos = Util.readFile(file);
        ArrayList<Photo> slideShow = new ArrayList<>();

        int maxId = 0;
        int max = 0;
        int totalInterest = 0;

        while (photos.size() > 0) {

            Photo photo = photos.get(maxId);
            photos.remove(maxId);
            slideShow.add(photo);

            max = 0;
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

            totalInterest += max;

        }


        for (Photo g: slideShow) {

            System.out.println(g);

        }
        System.out.println(totalInterest+"");



        Util.writeFile(file, photos);

    }
}