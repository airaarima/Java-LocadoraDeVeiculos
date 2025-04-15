package org.locadora.Models;

import java.time.LocalDate;
import java.util.Comparator; // serve para ordenar os veiculos, mas eu não entendi muito bem como funciona, então fiz um método pra cada ordenação

public class ListaLocacao extends Lista<Locacao> {

    public No<Veiculo> estaAlocado(String placa, ListaVeiculo lista) {
        return lista.buscarPorPlaca(placa);
    }

    public boolean verificarVeiculoDisponivel(String placa) {
        No<Locacao> atual = getInicio();
        while (atual != null) {
            Locacao locacao = atual.getElemento();
            if (locacao.getVeiculo().getPlaca().equals(placa)) {
                return false;
            }
            atual = atual.getProximo();
        }
        return true;
    }

    public ListaVeiculo filtrarVeiculosDisponiveis(ListaVeiculo todosVeiculos, int potenciaMinima, int lugares, String categoria) {
        ListaVeiculo veiculosFiltrados = new ListaVeiculo();
        No<Veiculo> atual = todosVeiculos.getInicio();
        while (atual != null) {
            Veiculo veiculo = atual.getElemento();
            if (verificarVeiculoDisponivel(veiculo.getPlaca())
                    && veiculo.getPotencia() >= potenciaMinima
                    && veiculo.getNumeroLugares() >= lugares
                    && veiculo.getCategoria() != null
                    && veiculo.getCategoria().getNome().equals(categoria)) {
                veiculosFiltrados.insereFim(veiculo);
            }
            atual = atual.getProximo();
        }
        return veiculosFiltrados;
    }

    public boolean clientePossuiLocacaoAtiva(String cpf) {
        No<Locacao> atual = getInicio();
        while (atual != null) {
            Locacao locacao = atual.getElemento();
            if (locacao.getCliente().getCpf().equals(cpf)) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public boolean devolverLocacao(String placa) {
        No<Locacao> atual = getInicio();
        while (atual != null) {
            Locacao locacao = atual.getElemento();
            if (locacao.getVeiculo().getPlaca().equals(placa)) {
                return super.remove(locacao);
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public void locarVeiculo(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao, double valor) {
        if(dataRetirada.isAfter(dataDevolucao)){
            System.out.println("Data de retirada não pode ser depois da data de devolução.");
            return;
        }
        if (verificarVeiculoDisponivel(veiculo.getPlaca())) {
            Locacao locacao = new Locacao(cliente, veiculo, dataRetirada, dataDevolucao, valor);
            super.insereFim(locacao);
        } else {
            System.out.println("Esse veículo já está locado.");
        }
    }

    public void ordenarVeiculosPorPotencia(ListaVeiculo veiculos) {
        boolean trocou;
        do {
            trocou = false;
            No<Veiculo> atual = veiculos.getInicio();
            while (atual != null && atual.getProximo() != null) {
                if (atual.getElemento().getPotencia() > atual.getProximo().getElemento().getPotencia()) {
                    Veiculo mudaPosicao = atual.getElemento();
                    atual.setElemento(atual.getProximo().getElemento());
                    atual.getProximo().setElemento(mudaPosicao);
                    trocou = true;
                }
                atual = atual.getProximo();
            }
        } while (trocou);
    }

    public void ordenarVeiculosPorAno(ListaVeiculo veiculos) {
        boolean trocou;
        do {
            trocou = false;
            No<Veiculo> atual = veiculos.getInicio();
            while (atual != null && atual.getProximo() != null) {
                if (atual.getElemento().getAno() > atual.getProximo().getElemento().getAno()) {
                    Veiculo mudaLugar = atual.getElemento();
                    atual.setElemento(atual.getProximo().getElemento());
                    atual.getProximo().setElemento(mudaLugar);
                    trocou = true;
                }
                atual = atual.getProximo();
            }
        } while (trocou);
    }

    public void ordenarVeiculosPorModelo(ListaVeiculo veiculos) {
        boolean trocou;
        do {
            trocou = false;
            No<Veiculo> atual = veiculos.getInicio();
            while (atual != null && atual.getProximo() != null) {
                if (atual.getElemento().getModelo().compareTo(atual.getProximo().getElemento().getModelo()) > 0) {
                    Veiculo mudaLugar = atual.getElemento();
                    atual.setElemento(atual.getProximo().getElemento());
                    atual.getProximo().setElemento(mudaLugar);
                    trocou = true;
                }
                atual = atual.getProximo();
            }
        } while (trocou);
    }

    public void ordenarVeiculosPorValor(ListaLocacao locacao) {
        boolean trocou;
        do {
            trocou = false;
            No<Locacao> atual = locacao.getInicio();
            while (atual != null && atual.getProximo() != null) {
                if (atual.getElemento().getValor() > atual.getProximo().getElemento().getValor()) {
                    Locacao mudaLugar = atual.getElemento();
                    atual.setElemento(atual.getProximo().getElemento());
                    atual.getProximo().setElemento(mudaLugar);
                    trocou = true;
                }
                atual = atual.getProximo();
            }
        } while (trocou);
    }

        }


