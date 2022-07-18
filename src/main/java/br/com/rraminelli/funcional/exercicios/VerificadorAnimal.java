package br.com.rraminelli.funcional.exercicios;

import br.com.rraminelli.model.Animal;

@FunctionalInterface
public interface VerificadorAnimal {

    boolean verificar(Animal animal);

}
