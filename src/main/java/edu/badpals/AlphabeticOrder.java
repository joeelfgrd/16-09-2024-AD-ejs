package edu.badpals;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AlphabeticOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo:");
        String path = sc.nextLine();
        Path dir = Path.of(path);
        List<String> lines = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(dir.toString()))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                lines.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Elige el tipo de orden:");
        System.out.println("1. _asc_case (case-sensitive)");
        System.out.println("2. _asc_non_case (case-insensitive)");
        System.out.println("3. _desc_case (case-sensitive)");
        System.out.println("4. _desc_non_case (case-insensitive)");
        String opcion = scanner.nextLine();

        Comparator<String> comparator = null;
        String nameArquive = null;

        if (opcion.equals("1")) {
            comparator = Comparator.naturalOrder();
            nameArquive = "_asc_case";
        } else if (opcion.equals("2")) {
            comparator = String.CASE_INSENSITIVE_ORDER;
            nameArquive = "_asc_non_case";
        } else if (opcion.equals("3")) {
            comparator = Comparator.reverseOrder();
            nameArquive = "_desc_case";
        } else if (opcion.equals("4")) {
            comparator = String.CASE_INSENSITIVE_ORDER.reversed();
            nameArquive = "_desc_non_case";
        }

        lines.sort(comparator);

        String originalFileName = dir.getFileName().toString();
        int lastDotIndex = originalFileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            System.out.println("El archivo no tiene extensión.");
            return;
        }
        String nameWithoutExtension = originalFileName.substring(0, lastDotIndex);
        String extension = originalFileName.substring(lastDotIndex);
        String outputFileName = nameWithoutExtension + nameArquive + extension;
        Path outputPath = dir.resolveSibling(outputFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toString()))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Archivo ordenado generado: " + outputFileName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
