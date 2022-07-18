package br.com.rraminelli.fileNIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileToPath {

    public static void main(String[] args) {

        final File fileIO = new File("C:/carros/marcas_carros.csv");
        if (fileIO.exists()) {
            System.out.println("Caminho absoluto: " + fileIO.getAbsolutePath());
            System.out.println("Diretorio?: " + fileIO.isDirectory());
            System.out.println("Caminho pai: " + fileIO.getParent());
            if (fileIO.isFile()) {
                System.out.println("Tamanho: " + fileIO.length());
                System.out.println("Ultima modificacao: " + fileIO.lastModified());
            } else {
                for (File subfile : fileIO.listFiles()) {
                    System.out.println(" " + subfile.getName());
                }
            }
        }

        final Path pathNIO = Path.of("C:/carros/marcas_carros.csv");
        try {
            if (Files.exists(pathNIO)) {
                System.out.println("Caminho absoluto: " + pathNIO.toAbsolutePath());
                System.out.println("Diretorio?: " + Files.isDirectory(pathNIO));
                System.out.println("Caminho pai: " + pathNIO.getParent());
                if (Files.isRegularFile(pathNIO)) {
                    System.out.println("Tamanho: " + Files.size(pathNIO));
                    System.out.println("Ultima modificacao: " + Files.getLastModifiedTime(pathNIO));
                } else {
                    try (Stream<Path> stream = Files.list(pathNIO)) {
                        stream.forEach(p -> System.out.println(" " + p.getFileName()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
