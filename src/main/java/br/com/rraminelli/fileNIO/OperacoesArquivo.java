package br.com.rraminelli.fileNIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class OperacoesArquivo {

    public static void main(String[] args) {

        //Criar novo arquivo - IO
        try {
            final File fileIO = new File("C:/carros/marcas_carrosIO.csv");
            if (!fileIO.exists()) {
                fileIO.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Criar novo arquivo - NIO 2
        try {
            final Path newFileNIO = Path.of("C:/carros/marcas_carrosNIO.csv");
            if (Files.notExists(newFileNIO)) {
                Files.createFile(newFileNIO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //copiar ou mover
        try {
            Files.copy( //move
                    Path.of("C:/carros/marcas_carrosNIO.csv"),
                    Path.of("C:/carros/marcas_carrosNIO_copy.csv"),
                    StandardCopyOption.REPLACE_EXISTING);

            Files.copy(Path.of("C:/carros/marcas_carros.csv"), System.out);

        } catch (IOException e) {
            e.printStackTrace();
        }


        //excluir arquivo
        try {
            boolean excluiu = Files.deleteIfExists(Path.of("C:/carros/marcas_carrosNIO_copy.csv"));
            System.out.println("excluiu? " + excluiu);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
