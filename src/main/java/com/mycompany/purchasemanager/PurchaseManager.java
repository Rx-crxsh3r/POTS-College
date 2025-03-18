package com.mycompany.purchasemanager;
import Classes.FilePath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PurchaseManager {

    public static void main(String[] args) {
        
        String filePath = FilePath.itemPath;
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                List<String> lineData = Arrays.asList(line.split(","));
                
                // Use .equals() for string comparison
                if(lineData.get(0).equals("123")) {
                    System.out.println(lineData.get(1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
