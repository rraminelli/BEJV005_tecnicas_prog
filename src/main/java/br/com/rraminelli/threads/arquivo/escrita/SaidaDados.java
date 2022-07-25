package br.com.rraminelli.threads.arquivo.escrita;

import java.util.List;

public interface SaidaDados<T> {

    String escreverArquivo(List<T> linhas);

}
