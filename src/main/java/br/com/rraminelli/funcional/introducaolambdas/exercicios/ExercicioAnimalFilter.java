package br.com.rraminelli.funcional.introducaolambdas.exercicios;

import br.com.rraminelli.funcional.introducaolambdas.model.Animal;
import br.com.rraminelli.funcional.introducaolambdas.model.Cliente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ExercicioAnimalFilter {

    public static void main(String[] args) {

        final List<Animal> animais = List.of(
                new Animal("Cachorro", false),
                new Animal("Gato", false),
                new Animal("Ave", true)
        );

        VerificadorAnimal verificador = a -> a.isVoador() == true;

        filtrar(animais, Animal::isVoador).forEach(System.out::println);

    }

    static List<Animal> filtrar(List<Animal> animals, VerificadorAnimal verificador) {
        final List<Animal> listaFiltrada = new ArrayList<>();
        for (Animal animal : animals) {
            if (verificador.verificar(animal)) {
                listaFiltrada.add(animal);
            }
        }
        return listaFiltrada;
    }

}
