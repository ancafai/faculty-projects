package Utils;

import java.io.BufferedReader;

public class FileData {
    //region Fields
    private String fileName;
    private BufferedReader reader;
    //endregion

    //region Constructor
    public FileData(String fileName, BufferedReader reader){
        this.fileName = fileName;
        this.reader = reader;
    }
    //endregion

    //region Properties
    public String getFileName(){return fileName;}

    public BufferedReader getReader() { return reader; }

    public void setFileName(String newFileName) { fileName = newFileName; }

    public void setReader(BufferedReader newReader) { reader = newReader; }
    //endregion Properties

    //region Methods
    public String toString(){
        return fileName;
    }
    //endregion Methods
}
