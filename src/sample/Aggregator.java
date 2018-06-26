package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Aggregator {


    private Map<String, String> allClipNames;

    private File fileClipsName;
    private File directoryFilesHTML;

    Aggregator(String fileClipsName, File directoryFilesHTML){

        this.directoryFilesHTML = directoryFilesHTML;
        this.fileClipsName = new File(fileClipsName);
        allClipNames = new HashMap<>();

        if (this.fileClipsName.exists() && this.fileClipsName.isFile()){
            readFileToMap(this.fileClipsName);

        } else {

        }

        this.fileClipsName.delete();
        }

    public void start(){

//        File htmlFile = new File("/Users/sasha/Downloads/testLP.htm");



        try (Stream<Path> filePathStream=Files.walk(Paths.get(directoryFilesHTML.getPath()))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    System.out.println("--> read file: " + filePath.getFileName());
                    readSingleFile(filePath.toFile());

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    public boolean readSingleFile(File htmlFile){

        try {
            Document doc = Jsoup.parse(htmlFile, "UTF-8");
            Elements elements = doc.select("div.thumbnail-title.gradient");
            for (Element element : elements) {
                parseSingleClipname(element.text());
            }
        } catch (IOException e) {return false;}
        return true;
    }

    public boolean parseSingleClipname(String str) {


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

/*
        fullClipName = fullClipName.replace(" / " , ", ");
        fullClipName = fullClipName.replace("/ " , ", ");
        fullClipName = fullClipName.replace(" /" , ", ");
        fullClipName = fullClipName.replace("/" , ", ");

*/
        allClipNames.put(keyClipName,fullClipName);

//        System.out.println(keyClipName + ": " + fullClipName);

        return true;
    }

    public void writeFile(){
        try {
        //    BufferedWriter writer = new BufferedWriter(new FileWriter(fileClipsName));
            PrintWriter writer = new PrintWriter(this.fileClipsName);

            for (Map.Entry<String, String> entry : allClipNames.entrySet()) {
                writer.println(entry.getKey());
                writer.println(entry.getValue());
                }

            writer.close();

        } catch (IOException e){
            System.out.println("Ошибка");
        }
    }
}
