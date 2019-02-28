
import com.sun.xml.internal.ws.binding.FeatureListUtil;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

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


            BufferedWriter output = new BufferedWriter(new FileWriter(file, false));
            output.write(photos.size());
            output.newLine();

            for (Photo line : photos) {
                output.write(line.id);
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
                photos.add(new Photo(id, orientation, Integer.parseInt(splitedLine[1]), tags));
                id++;
            }

            return photos;
        }catch (Exception e){
            return null;
        }
    }
}
