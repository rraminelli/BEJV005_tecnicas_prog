package br.com.rraminelli.threads.arquivo.escrita;

import java.util.List;

public interface SaidaDados<T> {

    void escreverArquivo(List<T> linhas);

}
