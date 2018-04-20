package sample;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Code {

    private Map hashMap;

    public static boolean startRename (File listClips, File dirWithClips){
        readListAllName(listClips);
        return true;
    }
    private static void readListAllName(File listClips){
        try{
            FileInputStream fstream = new FileInputStream(listClips);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                System.out.println(strLine);
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }

    }

}
