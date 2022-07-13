package br.com.rraminelli.funcional.introducaolambdas.exercicios;

import br.com.rraminelli.funcional.introducaolambdas.model.Animal;

@FunctionalInterface
public interface VerificadorAnimal {

    boolean verificar(Animal animal);

}
