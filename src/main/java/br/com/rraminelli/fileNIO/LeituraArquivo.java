package br.com.rraminelli.fileNIO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeituraArquivo {

    public static void main(String[] args) throws Exception {

        final String src = "C:/carros/marcas_carros.csv";

        /*
            IO - antigo
         */
        //ler com IO - versao 1
        BufferedReader bf = new BufferedReader(new FileReader(src));
        try {
            String line = null;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            if (bf != null) {
                bf.close();
            }
        }

        //ler com IO - versao 2
        try (BufferedReader reader = new BufferedReader(new FileReader(src))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }



        /*
            NIO 2 - novo
         */
        //ler como string - carrega o conteudo em memoria
        final String string = Files.readString(Path.of(src));
        System.out.println(string);

        //ler como array de byte
        final byte[] bytes = Files.readAllBytes(Path.of(src));

        //ler todas as linhas como List
        final List<String> lines = Files.readAllLines(Path.of(src));


        //ler como Stream - faz a leitura por demanda
        try (Stream<String> s = Files.lines(Path.of(src))) {
            s.forEach(System.out::println);
        }

        Files.lines(Path.of(src))
                .parallel()
                .map(s -> s.split(";", -1)[1])
                .filter(s -> s.startsWith("F"))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //listar arquivos de um diretorio
        Pattern pattern = Pattern.compile("\\.csv$");
        Files.list(Path.of("c:/carros/"))
                .filter(p -> pattern.matcher(p.getFileName().toString()).find())
                .forEach(System.out::println);

    }

}
