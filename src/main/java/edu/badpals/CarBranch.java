package edu.badpals;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class CarBranch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo:");
        String path = sc.nextLine();
        Path dir = Path.of(path);
        HashMap<String, ArrayList<String>> carBrand = new HashMap<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(dir.toString()))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }
                int espacio = linea.indexOf(" ");
                String brand = linea.substring(0, espacio);
                String car = linea.substring(espacio + 1);
                carBrand.computeIfAbsent(brand, k -> new ArrayList<>()).add(car);
            }

            try (PrintWriter escritor = new PrintWriter("res/marcas.txt")) {
                for (Map.Entry<String, ArrayList<String>> entry : carBrand.entrySet()) {
                    escritor.println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
                }
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
