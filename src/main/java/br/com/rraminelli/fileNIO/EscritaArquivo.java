package br.com.rraminelli.fileNIO;

import br.com.rraminelli.model.Ordem;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EscritaArquivo {

    public static void main(String[] args) throws  Exception{

        final List<String> linhas = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            linhas.add("linha " + i);
        }

        //java io - antigo
        final String dest = "C:/carros/marcas_carros_novo.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(dest))) {
            for (String linha : linhas) {
               writer.println(linha);
            }
        }


       //java NIO - novo
       Files.write(Path.of("C:/carros/marcas_carros_novo_NIO.csv"), linhas);


        


        new ArrayList<Ordem>().stream()
                .flatMap(o -> o.getItems().stream())
                .filter(item -> item.getIdItem() > 0)
                .collect(Collectors.toList());


    }

}
