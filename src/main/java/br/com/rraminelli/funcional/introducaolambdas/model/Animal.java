package br.com.rraminelli.funcional.introducaolambdas.model;

public class Animal {

    public Animal(String especie, boolean voador) {
        this.especie = especie;
        this.voador = voador;
    }

    private String especie;
    private boolean voador;

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public boolean isVoador() {
        return voador;
    }

    public void setVoador(boolean voador) {
        this.voador = voador;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{");
        sb.append("especie='").append(especie).append('\'');
        sb.append(", voador=").append(voador);
        sb.append('}');
        return sb.toString();
    }
}
