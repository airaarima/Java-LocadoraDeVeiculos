package org.locadora.Menus;

import org.locadora.Models.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuLocacao {
    private ListaLocacao locacoesLista;
    private ListaCliente clientesLista;
    private ListaVeiculo veiculosLista;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuLocacao(ListaLocacao locacoes, ListaCliente clientes, ListaVeiculo veiculos) {
        this.locacoesLista = locacoes;
        this.clientesLista = clientes;
        this.veiculosLista = veiculos;
        this.scanner = new Scanner(System.in);
    }
    public void exibir() {
        int opcao;
        do {
            System.out.println("\n===== MENU LOCAÇÕES =====");
            System.out.println("[1] Locar Veículo");
            System.out.println("[2] Devolver Veículo");
            System.out.println("[3] Filtrar Veículos Disponíveis");
            System.out.println("[4] Listar Locações Ativas");
            System.out.println("[5] Listar Veículos Disponíveis");
            System.out.println("[0] Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    locarVeiculo();
                    break;
                case 2:
                    devolverVeiculo();
                    break;
                case 3:
                    filtrarVeiculosDisponiveis();
                    break;
                case 4:
                    //listarLocacoesAtivas();
                    break;
                case 5:
                    listarVeiculosDisponiveis();
                    break;
            }
        } while (opcao != 0);
    }

    private void locarVeiculo(){
        System.out.println("\n--- LOCAR VEÍCULO ---");

        // Listar veículos disponíveis
        listarVeiculosDisponiveis();

        // Obter dados da locação
        System.out.print("Placa do veículo: ");
        String placa = scanner.nextLine();

        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        System.out.print("Data de retirada (dd/mm/aaaa): ");
        LocalDate dataRetirada = LocalDate.parse(scanner.nextLine(), dateFormatter);

        System.out.print("Data de devolução (dd/mm/aaaa): ");
        LocalDate dataDevolucao = LocalDate.parse(scanner.nextLine(), dateFormatter);

        System.out.print("Valor total: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        // Validar e criar locação
        No<Cliente> cliente = clientesLista.buscarPorCpf(cpf);
        No<Veiculo> veiculo = veiculosLista.buscarPorPlaca(placa);

        if (cliente == null || veiculo == null) {
            System.out.println("Cliente ou veículo não encontrado!");
            return;
        }

        if (!locacoesLista.verificarVeiculoDisponivel(placa)) {
            System.out.println("Veículo já está locado!");
            return;
        }

        Locacao novaLocacao = new Locacao(
                cliente.getElemento(),
                veiculo.getElemento(),
                dataRetirada,
                dataDevolucao,
                valor
        );

        locacoesLista.locarVeiculo(novaLocacao);
        System.out.println("Locação registrada com sucesso!");
    }

    private void devolverVeiculo(){
        System.out.println("\n--- DEVOLVER VEÍCULO ---");
        System.out.print("Placa do veículo: ");
        String placa = scanner.nextLine();

        // Encontrar a locação ativa
        No<Locacao> atual = locacoesLista.getInicio();
        Locacao locacaoParaDevolver = null;

        while (atual != null) {
            if (atual.getElemento().getVeiculo().equals(placa)) {
                locacaoParaDevolver = atual.getElemento();
                break;
            }
            atual = atual.getProximo();
        }

        if (locacaoParaDevolver == null) {
            System.out.println("Nenhuma locação ativa encontrada para este veículo!");
            return;
        }

//        locacoesLista.devolverVeiculo(locacaoParaDevolver);
        System.out.println("Veículo devolvido com sucesso!");
    }

    private void filtrarVeiculosDisponiveis(){}

    private void listarVeiculosDisponiveis(){
        locacoesLista.listarTodosVeiculosDisponiveis(veiculosLista);
    }
}
