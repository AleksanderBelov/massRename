package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.util.Map;

public class Heard {

    private Map<String, String> allClipNames;

    public static void startAggregator(String pathToFileForCreateBase, File dirWithFileForCreateBase){
        Aggregator aggregator = new Aggregator(pathToFileForCreateBase, dirWithFileForCreateBase);
        aggregator.start();
        aggregator.writeFile();




    }
}
