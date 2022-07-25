package br.com.rraminelli.threads.arquivo.escrita;

import br.com.rraminelli.model.Carro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SaidaDadosCarrosArquivoLetraD implements SaidaDados<Carro> {

    @Override
    public String escreverArquivo(List<Carro> linhas) {
        System.out.println("criando arquivo letra D");

        final Path destino = Path.of("c:/carros/letraD.csv");

        try {
            var linhasFile = linhas.stream()
                    .parallel()
                    .filter(l -> l.getMarca().startsWith("D"))
                    .map(Carro::getMarca)
                    .collect(Collectors.toList());

            Files.write(destino, linhasFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return destino.toString();
    }

}
