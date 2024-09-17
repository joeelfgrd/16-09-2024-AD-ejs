package edu.badpals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileCorrector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo:");
        String path = sc.nextLine();
        Path dir = Path.of(path);

        HashMap<String, String> usuarioNota = new HashMap<>();
        String correcciones = "";
        try (BufferedReader lector = new BufferedReader(new FileReader(dir.toString()));) {
            String linea;

            int i=1;
            while ((linea = lector.readLine()) != null) {
                if (i == 1) {
                    i=0;
                    correcciones = linea;
                    continue;

                }
                if (linea.isEmpty()){
                    continue;
                }
                int espacio = linea.indexOf(" ");
                String usuario = linea.substring(0, espacio);
                String respuestas = linea.substring(espacio+1);


                double nota= calcularNota(correcciones,respuestas);
                System.out.println("El usuario: " + usuario + " ha sacado un : " +nota);



            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public static double calcularNota(String correcciones, String respuestas) {
        double nota = 0.0;
        for (int i = 0; i < 20; i++) {
            char respuesta=respuestas.charAt(i);
            char correcion = correcciones.charAt(i);
                if (correcion == respuesta) {
                    nota += 0.5;
                } else if (respuesta != ' ') {
                    nota -= 0.15;
                } else {
                    nota -= 0;

            }
        }
        return nota;
    }
}


