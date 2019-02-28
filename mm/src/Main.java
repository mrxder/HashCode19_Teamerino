import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Main {

    public static final String fileB = "b_lovely_landscapes.txt";
    public static final String fileC = "c_memorable_moments.txt";
    public static final String fileD = "d_pet_pictures.txt";
    public static final String fileE = "e_shiny_selfies.txt";


    public static void main(String[] args) {

        ArrayList<Photo> out = new ArrayList<Photo>();

        ArrayList<Photo> photos = Util.readFile("mm/"+fileC);

        ArrayList<Photo> allHorizontal = new ArrayList<Photo>();
        for(Photo p:photos) {
            if(p.orientation) {
                allHorizontal.add(p);
            }
        }

        ArrayList<Photo> allVertical = new ArrayList<Photo>();
        for(Photo p: photos) {
            if(!p.orientation) {
                allVertical.add(p);
            }
        }

        Collections.sort(allVertical, new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                return o2.noOfTags - o1.noOfTags;
            }
        });

        System.out.println("1");

        ArrayList<Photo> jointVertical = new ArrayList<Photo>();

        for(int c = 0; c < allVertical.size()-1;c += 2) {

            Photo a = allVertical.get(c);
            Photo b = allVertical.get(c);

            HashSet<String> tags = new HashSet<String>();
            tags.addAll(a.tags);
            tags.addAll(b.tags);

            ArrayList<String> tagsAsArrayList = new ArrayList<>(tags);

            jointVertical.add(new Photo(allVertical.get(c).id+" "+allVertical.get(c+1).id, false, tags.size(), tagsAsArrayList));



        }

        System.out.println("2");

        ArrayList<Photo> finalPhotoList = new ArrayList<>();
        finalPhotoList.addAll(jointVertical);
        finalPhotoList.addAll(allHorizontal);

        photos = finalPhotoList;

        Collections.sort(photos, new Comparator<Photo>() {
            @Override
            public int compare(Photo p2, Photo p1)
            {

                return p1.noOfTags - p2.noOfTags;
                //return  p2.noOfTags - p1.noOfTags;
            }
        });


        Photo lastPhoto = photos.get(0);
        out.add(photos.get(0));
        photos.remove(0);



        while (photos.size() > 0) {

            int maxPoints = 0;
            int maxId = 0;
            for(int c = 0; c < 500 && c < photos.size(); c++) {

                int points = Photo.computePointsOfTwoPhotos(lastPhoto, photos.get(c));
                if(points > maxPoints) {
                    maxPoints = points;
                    maxId = c;
                }

            }

            out.add(photos.get(maxId));
            lastPhoto = photos.get(maxId);
            photos.remove(maxId);


        }





        int pointsSum = 0;

        for(int c = 0; c < out.size()-1; c++) {

            pointsSum += Photo.computePointsOfTwoPhotos(out.get(c), out.get(c+1));


        }

        ArrayList<String> outFile = new ArrayList<String>();
        outFile.add(out.size()+"");
        for(Photo p:out) {
            outFile.add(p.id+"");
        }


        Util.writeFile("cres.txt", outFile);

        System.out.println("points "+pointsSum);







    }
}
