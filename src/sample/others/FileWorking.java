package sample.others;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.tools.SimpleJavaFileObject;
import java.io.*;

public class FileWorking {
    public static boolean writeToFile(String perAndToken){
        try {
            FileWriter fileWriter = new FileWriter("src/sample/others/User.json");
            fileWriter.write(perAndToken);
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error in write to User: " + e.getMessage());
            return false;
        }
    }
    public static String readFromFile(){
        JSONParser parser = new JSONParser();
        try{
            FileReader fileReader = new FileReader("src/sample/others/User.json");
            JSONObject object = (JSONObject) parser.parse(fileReader);
            fileReader.close();
            if (object.toString().compareTo("{}") == 0){
                return "{}";
            }
            return object.toJSONString();
        } catch (IOException | ParseException e) {
            System.out.println("Error in read from User: " + e.getMessage());
            return "";
        }
    }
}
