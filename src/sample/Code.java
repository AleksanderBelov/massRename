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

    public boolean startRename (File listClips, File dirWithClips) {
        allClipNames = new HashMap<String, String>();
        readFileToMap(listClips);
        walkDirectory(dirWithClips);


        return true;
    }

    private void readFileToMap(File listClips){
        try{
            FileInputStream fstream = new FileInputStream(listClips);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLineKey;
            String strLineValye;
            while ((strLineKey = br.readLine()) != null){
/*                System.out.printf(strLineKey);
                if ((strLineValye = br.readLine()) != null) {
                    System.out.println(":" + strLineValye);
                    allClipNames.put(strLineKey,strLineValye);
                } else {
                    allClipNames.put(strLineKey," not find name");
                    }
*/
                String[] words = strLineKey.split(":\\s");
                allClipNames.put(words[0],words[1]);
                System.out.println("key:" + words[0] + " value:" + words[1]);

            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }
    }

    private void walkDirectory(File dirWithClips) {
        try (Stream<Path> filePathStream=Files.walk(Paths.get(dirWithClips.getPath()))) {
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
    private void renameFile(File file){

    }


}
