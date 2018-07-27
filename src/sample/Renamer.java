package sample;

import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class Renamer {
    private Map<String, String> allClipNames;
    private File fileClipsName;
    private File dirWithClips;
    private String[] allFileName = new String[3];
    private FileInfo fileInfo = new FileInfo();


    Renamer(File listClips, File dirWithClips) {

        this.fileClipsName = listClips;
        this.dirWithClips = dirWithClips;
        allClipNames = Code.readFileToMap(this.fileClipsName);
    }

    public void start() {
        walkDirectory(dirWithClips);

    }

    private void walkDirectory(File dirWithClips) {
        try (Stream<Path> filePathStream = Files.walk(Paths.get(dirWithClips.getPath()))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {


                    workWithFile(filePath);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renameFile(File file) {

    }

    private void workWithFile(Path filePath) {

        //   System.out.println(filePath.getParent() + " - folder (NIO)");
        //   System.out.println(filePath.getFileName() + " - name (NIO)");
        fileInfo = analizeFile(filePath.getFileName().toString());

        if (!compareClips(fileInfo))
            renameClip(filePath, fileInfo);
     //   fileInfo.print();







    }

    private FileInfo analizeFile(String fileName) {
//        System.out.println(fileName);

        FileInfo fi = new FileInfo();

        fi.setExtName(fileName);

        String[] words = fi.getName().split("\\s");
        fi.setName("");

        for (String subStr : words) {
            if (subStr.contains("720")) {
                fi.setQuality("720p");
            } else {
                fi.setName(fi.getName() + subStr + " ");
            }
        }

        fi.setName(fi.getName().substring(0, fi.getName().length() - 1));

        words = fi.getName().split("\\s");
        fi.setName("");

        for (String subStr : words) {
            if (subStr.contains("1080")) {
                fi.setQuality("1080p");
            } else {
                fi.setName(fi.getName() + subStr + " ");
            }
        }

        fi.setName(fi.getName().substring(0, fi.getName().length() - 1));

        words = fi.getName().split("\\s");
        fi.setName("");


        for (String subStr : words) {

            if (subStr.matches("[A-Z]+[0-9]+") || subStr.matches("#[0-9]+")) {
                fi.setKey(subStr);
            } else {
                fi.setName(fi.getName() + subStr + " ");
            }
        }

        fi.setName(fi.getName().substring(0, fi.getName().length() - 1));

        fi.setFindInBase(allClipNames.containsKey(fi.getKey()));

        if (fi.getFindInBase())
            fi.setEqualsBase(compareClips(fi));






        return fi;
    }

    private boolean compareClips(FileInfo fi){


        String a = fi.getKey() + " " + fi.getName();
        String b = fi.getKey() + " " + allClipNames.get(fi.getKey());


        if (a.equals(b))
            return true;
        return false;
    }

    private boolean renameClip(Path filePath, FileInfo fi){

        String toDir = "/";
        String oldName = filePath.getParent().toString() + toDir + filePath.getFileName().toString();
        String newName = filePath.getParent().toString() + toDir + fi.getKey() + " " + fi.getName();

        if (!(fi.getQuality().equals("")))
            newName = newName + " (" + fi.getQuality() + ")";

        newName = newName + "." + fi.getExension();

        File file = new File(oldName);
        File newFile = new File(newName);


        System.out.println(file.toString());
        System.out.println(newFile.toString());

        file.renameTo(newFile);


        return true;
    }



}
