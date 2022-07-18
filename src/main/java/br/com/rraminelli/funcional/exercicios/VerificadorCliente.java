package br.com.rraminelli.funcional.exercicios;

import br.com.rraminelli.model.Cliente;

@FunctionalInterface
public interface VerificadorCliente {

    boolean verificar(Cliente cliente);

}
