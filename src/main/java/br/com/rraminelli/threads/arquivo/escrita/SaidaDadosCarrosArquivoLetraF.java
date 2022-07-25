package br.com.rraminelli.threads.arquivo.escrita;

import br.com.rraminelli.model.Carro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SaidaDadosCarrosArquivoLetraF implements SaidaDados<Carro> {

    public String escreverArquivo(List<Carro> linhas) {

        System.out.println("criando arquivo letra F");

        final Path destino = Path.of("c:/carros/letraF.csv");

        try {
            Files.write(
                    destino,
                    linhas.stream()
                            .filter(l -> l.getMarca().startsWith("F"))
                            .map(Carro::getMarca)
                            .collect(Collectors.toList())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return destino.toString();
    }

}
