package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Code {

    private Map<String, String> allClipNames;

    public boolean startRename(File listClips, File dirWithClips) {
        allClipNames = new HashMap<String, String>();
//        walkDirectory(dirWithClips);


        return true;
    }


/*    private void walkDirectory(File dirWithClips) {
        try (Stream<Path> filePathStream = Files.walk(Paths.get(dirWithClips.getPath()))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {

                    //                System.out.println(":" + filePath);
                    System.out.println("*" + filePath.getFileName());
                    //        renameFile(filePath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/


    public static Map<String, String> readFileToMap(File listClips) {

        Map<String, String> allClipNames = new HashMap<>();

        try {
            FileInputStream fstream = new FileInputStream(listClips);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            String[] strToMap;

            while ((strLine = br.readLine()) != null) {
                strToMap = strLine.split(": ");

                System.out.println("key: " + strToMap[0] + " | " + "value: " + strToMap[1]); // добавить пробел после :
//                allClipNames.put(strToMap[0], strToMap[1]);

            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }

        return allClipNames;

    }


    public static String correctFilename(String originalFilename){
        String correctFilename;
        correctFilename = originalFilename.replace(" / " , ", ");
        correctFilename = correctFilename.replace("/ " , ", ");
        correctFilename = correctFilename.replace(" /" , ", ");
        correctFilename = correctFilename.replace("/" , ", ");
        return correctFilename;
    }


}
