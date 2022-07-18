package br.com.rraminelli.fileNIO;

import br.com.rraminelli.model.Carro;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class LeituraArquivoCarros {

    public static void main(String[] args) throws IOException {

        Path pathMarca = Path.of("C:/carros/marcas_carros.csv");

        Path pathMarcaNome = Path.of("C:/carros/marcas_carros_nomes.csv");

        List<Carro> carroLista = leitura(pathMarca);

        escrita(pathMarcaNome, carroLista);

    }

    static List<Carro> leitura(Path path) throws IOException {
        return Files.lines(path)
                .map(linha -> {
                    String[] linhaSplit = linha.split(";");
                    return new Carro(linhaSplit[0], linhaSplit[1]);
                })
                .collect(Collectors.toList());
    }

    private static void escrita(Path pathMarcaNome, List<Carro> carroLista) throws IOException {
        Files.write(pathMarcaNome,
                    carroLista.stream()
                            .map(Carro::getMarca)
                            .map(String::toLowerCase)
                            .collect(Collectors.toList())
                );
    }

}
