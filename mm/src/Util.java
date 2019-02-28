
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

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
                photos.add(new Photo(orientation, Integer.parseInt(splitedLine[1]), tags));
            }

            return photos;
        }catch (Exception e){
            return null;
        }
    }
}
