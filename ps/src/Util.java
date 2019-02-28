
import com.sun.xml.internal.ws.binding.FeatureListUtil;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    public static ArrayList<Photo> combineList (ArrayList<Photo> photos){

        ArrayList<Photo> HPhotos = new ArrayList<>();
        ArrayList<Photo> VPhotos = new ArrayList<>();
        for (Photo p : photos) {
            if(p.orientation){
                HPhotos.add(p);
            }else{
                VPhotos.add(p);
            }
        }

        //combine Verticals
        ArrayList<Photo> combinedVerticalPhotos = combineVerticals(VPhotos);

        HPhotos.addAll(combinedVerticalPhotos);
        return HPhotos;
    }

    private static ArrayList<Photo> combineVerticals(ArrayList<Photo> vPhotos) {
        ArrayList<Photo> resultList = new ArrayList<>();

        while(vPhotos.size() > 0){

            Photo firstPhotoFromList = vPhotos.get(0);
            vPhotos.remove(firstPhotoFromList);

            Photo bestMatchPhoto = bestMatchFromList(firstPhotoFromList,vPhotos);
            vPhotos.remove(bestMatchPhoto);

            Photo finalPhoto = combinePhotos(firstPhotoFromList, bestMatchPhoto);
            resultList.add(finalPhoto);
        }

        return resultList;
    }

    private static Photo combinePhotos(Photo a, Photo b) {

        ArrayList<String> tmp = new ArrayList<>();

        for (String g : b.tags) {
            tmp.add(g);
        }

        ArrayList<String> tmp2 = removeDuplicates(tmp);

        return new Photo(a.id+" "+b.id, false, tmp2.size() , tmp2);
    }

    // Function to remove duplicates from an ArrayList
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }

    private static Photo bestMatchFromList(Photo firstPhotoFromList, ArrayList<Photo> vPhotos) {
        int maxAmountOfTags = 0;
        maxAmountOfTags = 0;
        Photo result = null;
        for(Photo p2 : vPhotos){
            ArrayList<String> tags = firstPhotoFromList.tags;
            tags.addAll(p2.tags);
            ArrayList<String> photos = removeDuplicates(tags);
            if (photos.size() > maxAmountOfTags){
                maxAmountOfTags = photos.size();
                result = p2;
            }
        }

        return result;
    }




    public static int calcInterestFactor(Photo p1, Photo p2){

        int numOfCommonTags = numOfCommonTags(p1, p2);
        int numOfTagsOnlyInP1 = p1.tags.size() - numOfCommonTags;
        int numOfTagsOnlyInP2 = p2.tags.size() - numOfCommonTags;

        int smallest;
        if (numOfCommonTags <= numOfTagsOnlyInP1 && numOfCommonTags <= numOfTagsOnlyInP2) {
            smallest = numOfCommonTags;
        } else if (numOfTagsOnlyInP1 <= numOfTagsOnlyInP2 && numOfTagsOnlyInP1 <= numOfCommonTags) {
            smallest = numOfTagsOnlyInP1;
        } else {
            smallest = numOfTagsOnlyInP2;
        }

        return smallest;

    }


    //Todo: Optimize to nlogn
    public static int numOfCommonTags(Photo p1, Photo p2){

        ArrayList<String> p1Tags = p1.tags;
        ArrayList<String> p2Tags = p2.tags;

        int result = 0;

        for (String t : p1Tags) {
            for (String t2 : p2Tags){
                if(t.equals(t2)){
                    result++;
                }
            }
        }
        return result;
    }

    public static void writeFile(String file, ArrayList<Photo> photos){


        try {


            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
            output.write(""+photos.size());
            output.newLine();

            for (Photo line : photos) {
                output.write(""+line.id);
                output.newLine();
            }

            output.close();

        }catch (Exception e){

        }
    }

    public static ArrayList<Photo> readFile(String file){

        ArrayList<Photo> photos = new ArrayList<>();
        int id = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitedLine = line.split("\\s+");
                boolean orientation = splitedLine[0].equals("H");
                List<String> tmpTags = Arrays.asList(splitedLine);
                ArrayList<String> tags = new ArrayList<>(tmpTags);
                tags.remove(0);
                tags.remove(0);
                photos.add(new Photo(""+id, orientation, Integer.parseInt(splitedLine[1]), tags));
                id++;
            }

            return photos;
        }catch (Exception e){
            return null;
        }
    }
}
