package sample;

public class FileInfo {
    private String key;
    private String name;
    private String exension;
    private String quality;
    private boolean findInBase;
    private boolean equalsBase;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getExension() {
        return exension;
    }

    public String getQuality() {
        return quality;
    }
    public boolean getEqualsBase() {
        return equalsBase;
    }
    public boolean getFindInBase() {
        return findInBase;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExension(String exension) {
        this.exension = exension;
    }

    public void setExtName(String filename) {
        setExension(getFileExtension(filename));
        setName(filename.substring(0, filename.length() - getExension().length() - 1));
    }


    public void setQuality(String quality) {
        this.quality = quality;
    }
    public void setFindInBase(boolean findInBase) {
        this.findInBase =  findInBase;
    }

    public void setEqualsBase(boolean equalsBase) {
        this.equalsBase =  equalsBase;
    }

    public FileInfo() {
        setExension("");
        setKey("");
        setName("");
        setQuality("");
        setFindInBase(false);
        setEqualsBase(false);
    }

    public void print() {
        System.out.println(" ");
        System.out.println("name: " + getName());
        System.out.println(" ext: " + getExension());
        System.out.println(" qal: " + getQuality());
        System.out.println(" key: " + getKey());
        System.out.println("find: " + getFindInBase());
        System.out.println("comp: " + getEqualsBase());
    }

    private static String getFileExtension(String fullFileName) {
        // если в имени файла есть точка и она не является первым символом в названии файла
        if (fullFileName.lastIndexOf(".") != -1 && fullFileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

}
