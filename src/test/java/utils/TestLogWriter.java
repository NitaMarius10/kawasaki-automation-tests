package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


  //Utility class to write test logs to text files
public class TestLogWriter {

    private BufferedWriter writer;
    private String filePath;


      //Initialize the writer and create the .txt file
    public TestLogWriter(String testName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        this.filePath = "test-output/logs/" + testName + "_" + timestamp + ".txt";

        File file = new File(filePath);
        file.getParentFile().mkdirs(); // creates the folder if it doesn't exist
        this.writer = new BufferedWriter(new FileWriter(file));
    }


     //Write a message to the file
    public void log(String message) {
        try {
            writer.write(new SimpleDateFormat("HH:mm:ss").format(new Date()) + " - " + message);
            writer.newLine();
            writer.flush(); // ensures that the message is written immediately
        } catch (IOException e) {
            System.out.println("Error writing to log: " + e.getMessage());
        }
    }

     //Close the file
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing log: " + e.getMessage());
        }
    }

      //Returns the full path of the file
    public String getFilePath() {
        return filePath;
    }
}
