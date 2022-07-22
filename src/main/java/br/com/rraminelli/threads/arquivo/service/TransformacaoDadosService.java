package br.com.rraminelli.threads.arquivo.service;

import br.com.rraminelli.threads.arquivo.escrita.SaidaDados;
import br.com.rraminelli.threads.arquivo.leitura.EntradaDados;

import java.util.List;

public class TransformacaoDadosService<T> {

    private final EntradaDados<T> entradaDados;
    private final SaidaDados<T>[] saidaDados;

    public TransformacaoDadosService(EntradaDados<T> entradaDados, SaidaDados<T>... saidaDados) {
        this.entradaDados = entradaDados;
        this.saidaDados = saidaDados;
    }

    public void transformar() {

        //executa a leitura dos dados
        final List<T> linhasArquivoOrigem = entradaDados.lerArquivo();


        //executa a escrita dos dados



    }

}
