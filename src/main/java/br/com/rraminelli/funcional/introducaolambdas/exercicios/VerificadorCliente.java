package br.com.rraminelli.funcional.introducaolambdas.exercicios;

import br.com.rraminelli.funcional.introducaolambdas.model.Cliente;

@FunctionalInterface
public interface VerificadorCliente {

    boolean verificar(Cliente cliente);

}
