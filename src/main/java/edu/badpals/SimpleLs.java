package edu.badpals;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.DirectoryIteratorException;
import java.util.Scanner;

public class SimpleLs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del directorio:");
        String nombre = sc.nextLine();
        Path dir = Path.of(nombre);

        if (Files.isDirectory(dir)) {
            System.out.println("El directorio es correcto. Listando archivos:");
            StringBuilder paths = new StringBuilder();
            try {
                for (Path listado : Files.newDirectoryStream(dir)) {
                    paths.append(Files.isDirectory(dir) ? "d" : "-");
                    paths.append(Files.isReadable(dir) ? "r" : "-");
                    paths.append(Files.isWritable(dir)? "w" : "-");
                    paths.append(Files.isExecutable(dir)? "x" : "-");
                    paths.append(" ");
                    paths.append(listado.getFileName()).append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(paths.toString());

        }
    }
}