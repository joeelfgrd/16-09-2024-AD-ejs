package edu.badpals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class caracterSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo:");
        String path = sc.nextLine();
        Path dir = Path.of(path);
        int contador = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el caracter a buscar:");
        String caracter = scanner.nextLine();

        char charABuscar = caracter.charAt(0);

        try (BufferedReader lector = new BufferedReader(new FileReader(dir.toString()))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                for (int i = 0; i < linea.length(); i++) {
                    if (linea.charAt(i) == charABuscar)
                        contador++;
                    }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());

        }
        System.out.println("El total de ocurrencias del caracter '" + caracter + "' es: " + contador);
    }
}




