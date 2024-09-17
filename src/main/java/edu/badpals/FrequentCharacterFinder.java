package edu.badpals;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.String.valueOf;

public class FrequentCharacterFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo:");
        String path = sc.nextLine();
        Path dir = Path.of(path);

        HashMap<String, Integer> frequent = new HashMap<>();
        Map.Entry<String, Integer> maxEntry = null;
        
        
        try (BufferedReader lector = new BufferedReader(new FileReader(dir.toString()));) {
            String linea;


            while ((linea = lector.readLine()) != null)
                for (int i = 0; i < linea.length(); i++) {
                    String c = valueOf(linea.charAt(i));
                    frequent.put(c, frequent.containsKey(c) ? frequent.get(c) + 1 : 1);
                }


            maxEntry = frequent.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .orElse(null);

            for (Map.Entry<String, Integer> entry : frequent.entrySet()) {
                if (entry.getValue().equals(maxEntry.getValue())) {
                    System.out.println("El carácter " + entry.getKey() + " tiene " + entry.getValue() + " apariciones.");
                }
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
        }

    }
}
