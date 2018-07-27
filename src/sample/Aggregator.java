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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class Aggregator {


    private Map<String, String> allClipNames;

    private File fileClipsName;
    private File directoryFilesHTML;

    Aggregator(String fileClipsName, File directoryFilesHTML){

        this.directoryFilesHTML = directoryFilesHTML;
        this.fileClipsName = new File(fileClipsName);
        allClipNames = new HashMap<>();


        if (this.fileClipsName.exists() && this.fileClipsName.isFile()){
//            readFileToMap(this.fileClipsName);
            allClipNames = Code.readFileToMap(this.fileClipsName);
//            System.out.println(allClipNames.get("GIO023").toString());


        } else {

        }

        this.fileClipsName.delete();
        }

    public void start(){

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
            if (subStr.matches("[A-z]+[0-9]+") || subStr.matches("#[0-9]+")){
                keyClipName = subStr;
            } else {
                fullClipName = fullClipName + subStr + " ";
            }
        }
        fullClipName = Code.correctFilename(fullClipName.substring(0,fullClipName.length() - 1));
      //  fullClipName = fullClipName.substring(0,fullClipName.length()-1);


        if (keyClipName.equals("")) {
            keyClipName = "NONAME" + (int) (Math.random() * 100);
        }
        allClipNames.put(keyClipName,fullClipName);


        return true;
    }

    public void writeFile(){
        try {
        //    BufferedWriter writer = new BufferedWriter(new FileWriter(fileClipsName));
            PrintWriter writer = new PrintWriter(this.fileClipsName);

            // сортировка по ключу

            LinkedHashMap<String, String> collect = allClipNames
                    .entrySet()
                    .stream()
                    .sorted(comparingByKey())
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));



//            for (Map.Entry<String, String> entry : allClipNames.entrySet()) {
            for (Map.Entry<String, String> entry : collect.entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue());
              //  writer.println(entry.getValue());
                }

            writer.close();

        } catch (IOException e){
            System.out.println("Ошибка");
        }
    }
}
