package org.locadora.Models;

public class Veiculo {

    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private double potencia;
    private int numeroLugares;
    private Categoria categoria;

    public Veiculo(String placa, String modelo, String marca, int ano, double potencia, int numeroLugares, Categoria categoria) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.potencia = potencia;
        this.numeroLugares = numeroLugares;
        this.categoria = categoria;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getAno() {
        return ano;
    }

    public double getPotencia() {
        return potencia;
    }

    public int getNumeroLugares() {
        return numeroLugares;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }
    public void setNumeroLugares(int numeroLugares) {
        this.numeroLugares = numeroLugares;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return "Veiculo{" +
                "Placa: '" + placa + "\n" +
                "Modelo: '" + modelo + "\n" +
                "Marca: '" + marca + "\n" + 
                "Ano: " + ano + "\n"+
                "Potência: " + potencia + "\n"+
                "Número de lugares: " + numeroLugares + "\n" +
                "Categoria:" + categoria + "\n" +
                "}";
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Veiculo) {
            Veiculo veiculo = (Veiculo) obj;
            return this.placa.equals(veiculo.getPlaca());
        }
        return false;
    }
}
