package utils;

import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import models.Etudiant;

public class ReadData {
    private String filename = "src/main/resources/etudiant.json";
    private Etudiant etudiant;

    public Etudiant readDataFromJson() {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            etudiant = gson.fromJson(reader, Etudiant.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return etudiant;
    }
}
