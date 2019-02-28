
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

        if()


    }


    public static int numOfCommonTags(Photo p1, Photo p2){

        ArrayList<String> p1Tags = p1.tags;
        ArrayList<String> p2Tags = p2.tags;

        int result = 0;

        for (String t : p1Tags) {
            if(p2Tags.contains(t)) {
                result++;
            }
        }

        return result;
    }

    public static void writeFile(String file, ArrayList<String> lines){


        try {


            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
            output.write(lines.size());
            output.newLine();

            for (String line : lines) {
                output.write(line);
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
