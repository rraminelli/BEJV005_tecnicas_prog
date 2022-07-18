package br.com.rraminelli.fileNIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class LerArquivo {

    public static void main(String[] args) throws IOException {

        final String src = "C:/carros/marcas_carros.csv";

        Files.lines(Path.of(src))
                .map(linha -> linha.split(";")[1])
                .filter(nome -> nome.startsWith("F"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

}
