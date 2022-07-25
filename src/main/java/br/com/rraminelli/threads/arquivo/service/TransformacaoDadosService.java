package br.com.rraminelli.threads.arquivo.service;

import br.com.rraminelli.threads.arquivo.escrita.SaidaDados;
import br.com.rraminelli.threads.arquivo.leitura.EntradaDados;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TransformacaoDadosService<T> {

    private final EntradaDados<T> entradaDados;
    private final SaidaDados<T>[] saidaDados;

    public TransformacaoDadosService(EntradaDados<T> entradaDados, SaidaDados<T>... saidaDados) {
        this.entradaDados = entradaDados;
        this.saidaDados = saidaDados;
    }

    public void transformar() {

        System.out.println("Inicio transformar ");

        //executa a leitura dos dados
        final List<T> linhasArquivoOrigem = entradaDados.lerArquivo();



        //executa a escrita dos dados

        ExecutorService executorService = Executors.newCachedThreadPool();

        /*metodo 1

        Arrays.stream(saidaDados)
                .forEach(saida -> executorService.submit(() -> saida.escreverArquivo(linhasArquivoOrigem)));

        try {
            if (executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Envio email finalizado");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

         */


        /*metodo 2
        Arrays.stream(saidaDados)
                .parallel()
                .map(saida -> saida.escreverArquivo(linhasArquivoOrigem))
                .collect(Collectors.joining());
         */


        /*
            metodo 3
         */
        List<Callable<String>> processos = Arrays.stream(saidaDados)
                .map(saida -> (Callable<String>) () -> saida.escreverArquivo(linhasArquivoOrigem))
                .collect(Collectors.toList());


        try {
            List<Future<String>> resultados = executorService.invokeAll(processos);

            String listaArquivos = resultados.stream()
                            .map(future -> {
                                try {
                                    return future.get();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                } catch (ExecutionException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .collect(Collectors.joining(";"));
                            /*.reduce((p1, p2) -> p1 + ";"+p2)
                            .get();*/

            System.out.println("Processamento realizado com sucesso. \n " + listaArquivos);

            System.out.println("Fim transformar ");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }




    }

}
