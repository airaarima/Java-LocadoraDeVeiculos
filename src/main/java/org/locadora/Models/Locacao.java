package org.locadora.Models;

import java.time.LocalDate;

public class Locacao {
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada, dataDevolucao;
    private double valor;

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao, double valor) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
    }
    public String getCliente() {
        return cliente.getCnh();
    }
    public String getVeiculo() {
        return veiculo.getPlaca();
    }
    public LocalDate getDataRetirada() {
        return dataRetirada;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s, Veículo: %s, Data de Retirada: %s, Data de Devolução: %s, Valor: %.2f",
                cliente.getNome(), veiculo.getModelo(), dataRetirada, dataDevolucao, valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Locacao) {
            Locacao locacao = (Locacao) obj;
            return this.cliente.equals(locacao.cliente) && this.veiculo.equals(locacao.veiculo);
        }
        return false;
    }
}
