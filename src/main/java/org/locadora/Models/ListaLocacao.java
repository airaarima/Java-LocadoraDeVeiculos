package org.locadora.Models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale; // serve para ordenar os veiculos, mas eu não entendi muito bem como funciona, então fiz um método pra cada ordenação


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

    public ListaVeiculo listarTodosVeiculosDisponiveis(ListaVeiculo todosVeiculos) {
        ListaVeiculo veiculosDisponiveis = todosVeiculos;
        No<Veiculo> atual = todosVeiculos.getInicio();

        while (atual != null) {
            Veiculo veiculo = atual.getElemento();
            if (!verificarVeiculoDisponivel(veiculo.getPlaca())) {
                veiculosDisponiveis.remove(veiculo);
            }
            atual = atual.getProximo();
        }

        return veiculosDisponiveis;
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

    public void locarVeiculo(Locacao novaLocacao) {
        if (novaLocacao.getDataRetirada().isAfter(novaLocacao.getDataDevolucao())) {
            System.out.println("Data de retirada não pode ser depois da data de devolução.");
            return;
        }
        if (verificarVeiculoDisponivel(novaLocacao.getVeiculo().getPlaca())) {
            Locacao locacao = new Locacao(novaLocacao.getCliente(), novaLocacao.getVeiculo(), novaLocacao.getDataRetirada(), novaLocacao.getDataDevolucao(), novaLocacao.getValor());
            super.insereFim(locacao);
        } else {
            System.out.println("Esse veículo já está locado.");
        }
    }

    public void ordenarVeiculosPorCategoria(ListaVeiculo veiculos) {
        boolean trocou;
        do {
            trocou = false;
            No<Veiculo> atual = veiculos.getInicio();
            while (atual != null && atual.getProximo() != null) {
                if (atual.getElemento().getPotencia() > atual.getProximo().getElemento().getCategoria().getIdentificador()) {
                    Veiculo mudaPosicao = atual.getElemento();
                    atual.setElemento(atual.getProximo().getElemento());
                    atual.getProximo().setElemento(mudaPosicao);
                    trocou = true;
                }
                atual = atual.getProximo();
            }
        } while (trocou);
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

    public void lerLocacoesCsv(ListaCliente clientes, ListaVeiculo veiculos) {
        final DateTimeFormatter DATE_FORMATTER =
                DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("pt-BR"));

        String caminhoCsv = "src/main/java/org/locadora/Data/Locacoes.csv";

        try {
            FileReader arquivoCsv = new FileReader(caminhoCsv);
            BufferedReader br = new BufferedReader(arquivoCsv);
            String linha;

            // Pula o cabeçalho do csv
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) {
                    String cpfCliente = dados[0].trim();
                    String placaVeiculo = dados[1].trim();
                    LocalDate dataRetirada = LocalDate.parse(dados[2].trim(), DATE_FORMATTER);
                    LocalDate dataDevolucao = LocalDate.parse(dados[3].trim(), DATE_FORMATTER);
                    double valor = Double.parseDouble(dados[4].trim());

                    // Buscar cliente e veículo nas listas correspondentes
                    No<Cliente> cliente = clientes.buscarPorCpf(cpfCliente);
                    No<Veiculo> veiculo = veiculos.buscarPorPlaca(placaVeiculo);

                    if (cliente != null && veiculo != null) {
                        Locacao locacao = new Locacao(
                                cliente.getElemento(),
                                veiculo.getElemento(),
                                dataRetirada,
                                dataDevolucao,
                                valor
                        );
                        insereFim(locacao);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}