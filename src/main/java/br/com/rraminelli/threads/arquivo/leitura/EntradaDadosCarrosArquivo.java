package br.com.rraminelli.threads.arquivo.leitura;

import br.com.rraminelli.model.Carro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntradaDadosCarrosArquivo implements EntradaDados<Carro> {

    private final Path[] arquivos;

    public EntradaDadosCarrosArquivo(Path... arquivos) {
        this.arquivos = arquivos;
    }

    @Override
    public List<Carro> lerArquivo() {
        return Arrays.stream(arquivos)
                .parallel()
                .map(path -> {
                    //lista de carro
                    try {
                        return Files.lines(path)
                                .parallel()
                                .skip(1)
                                .map(linha -> {
                                    String[] split = linha.split(";");
                                    return new Carro(split[0], split[1]);
                                })
                                .collect(Collectors.toList());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                //Stream<List<Carro>>
                .flatMap(lista -> lista.stream())
                .collect(Collectors.toList());
    }

}
