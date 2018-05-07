package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.util.Map;

public class Lib {


    private Map<String, String> allClipNames;
    //public static void main(String[] args) {

    public static void startAggregator(){

        File htmlFile = new File("/Users/sasha/Downloads/testLP.htm");
        readSingleFile(htmlFile);
    }



    private void readFileToMap(File listClips){
        try{
            FileInputStream fstream = new FileInputStream(listClips);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLineKey;
            String strLineValye;
            while ((strLineKey = br.readLine()) != null){
                System.out.printf(strLineKey);
                if ((strLineValye = br.readLine()) != null) {
                    System.out.println(":" + strLineValye);
                    allClipNames.put(strLineKey,strLineValye);
                } else {
                    allClipNames.put(strLineKey," not find name");
                }
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }
    }
    public static boolean readSingleFile(File htmlFile){

        try {
            Document doc = Jsoup.parse(htmlFile, "UTF-8");
            Elements elements = doc.select("div.thumbnail-title.gradient");
            for (Element element : elements) {
                parseSingleClipname(element.text());
            }
        } catch (IOException e) {return false;}
        return true;
    }

    public static boolean parseSingleClipname(String str) {


        String[] words = str.split("\\s");
        String fullClipName = "";
        String keyClipName = "";
        for (String subStr : words){
            if (subStr.matches("[A-Z]+[0-9]+") || subStr.matches("#[0-9]+")){
                keyClipName = subStr;
            } else {
                fullClipName = fullClipName + subStr + " ";
            }
        }
        fullClipName = fullClipName.substring(0,fullClipName.length() - 1);
        System.out.println(keyClipName + ": " + fullClipName);
        //allClipNames.put(keyClipName,fullClipName);
        return true;
    }
}
