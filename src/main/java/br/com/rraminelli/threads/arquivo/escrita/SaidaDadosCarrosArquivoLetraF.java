package br.com.rraminelli.threads.arquivo.escrita;

import br.com.rraminelli.model.Carro;

import java.nio.file.Path;
import java.util.List;

public class SaidaDadosCarrosArquivoLetraF implements SaidaDados<Carro> {

    public void escreverArquivo(List<Carro> linhas) {

        final Path destino = Path.of("c:/carros/letraF.csv");


    }

}
