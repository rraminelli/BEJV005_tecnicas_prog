package br.com.rraminelli.threads.arquivo;

import br.com.rraminelli.model.Carro;
import br.com.rraminelli.threads.arquivo.escrita.SaidaDados;
import br.com.rraminelli.threads.arquivo.escrita.SaidaDadosCarrosArquivoLetraF;
import br.com.rraminelli.threads.arquivo.escrita.SaidaDadosCarrosArquivoLetraD;
import br.com.rraminelli.threads.arquivo.leitura.EntradaDados;
import br.com.rraminelli.threads.arquivo.leitura.EntradaDadosCarrosArquivo;
import br.com.rraminelli.threads.arquivo.service.TransformacaoDadosService;

import java.nio.file.Path;

public class ExecutaTransformacaoArquivos {

    public static void main(String[] args) {

        Path[] arquivos = new Path[] {
                Path.of("c:/carros/marcas_carros1.csv"),
                Path.of("c:/carros/marcas_carros2.csv")
        };

        EntradaDados<Carro> entradaDados = new EntradaDadosCarrosArquivo(arquivos);

        SaidaDados<Carro>[] escritasArquivo = new SaidaDados[]{
                new SaidaDadosCarrosArquivoLetraF(),
                new SaidaDadosCarrosArquivoLetraD()
        };

        TransformacaoDadosService<Carro> transformacaoDadosService =
                new TransformacaoDadosService<>(entradaDados, escritasArquivo);

        transformacaoDadosService.transformar();

    }

}
