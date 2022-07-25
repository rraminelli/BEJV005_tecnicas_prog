package br.com.rraminelli.threads.arquivo.leitura;

import br.com.rraminelli.model.Carro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class EntradaDadosCarrosArquivo implements EntradaDados<Carro> {

    private final Path[] arquivos;

    public EntradaDadosCarrosArquivo(Path... arquivos) {
        this.arquivos = arquivos;
    }

    @Override
    public List<Carro> lerArquivo() {

        //com threads e ExecutorService
        /*List<Callable<List<Carro>>> callableList = new ArrayList<>();
        for (Path arquivo : arquivos) {
            Callable<List<Carro>> callable = () -> {
                try {
                    return Files.lines(arquivo)
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
            };
            callableList.add(callable);
        }

        ExecutorService executor = Executors.newCachedThreadPool();

        try {
            List<Future<List<Carro>>> futureCarros = executor.invokeAll(callableList);

             return futureCarros.stream().flatMap(f -> {
                try {
                    return f.get().stream();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }*/




        //Com streams
        return Arrays.stream(arquivos)
                .parallel()
                .map(path -> {
                    //lista de carro
                    System.out.println("Lendo arquivo: " + path.getFileName());
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
