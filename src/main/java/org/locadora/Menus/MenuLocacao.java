package org.locadora.Menus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.locadora.Models.Cliente;
import org.locadora.Models.ListaCliente;
import org.locadora.Models.ListaLocacao;
import org.locadora.Models.ListaVeiculo;
import org.locadora.Models.Locacao;
import org.locadora.Models.No;
import org.locadora.Models.Veiculo;

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
            System.out.println("[5] Listar Todos Os Veículos Disponíveis");
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
                    listarLocacoesAtivas();
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
            if (atual.getElemento().getVeiculo().getPlaca().equals(placa)) {
                locacaoParaDevolver = atual.getElemento();
                break;
            }
            atual = atual.getProximo();
        }

        if (locacaoParaDevolver == null) {
            System.out.println("Nenhuma locação ativa encontrada para este veículo!");
            return;
        }

        locacoesLista.devolverLocacao(locacaoParaDevolver.getVeiculo().getPlaca());
        System.out.println("Veículo devolvido com sucesso!");
    }

    private void filtrarVeiculosDisponiveis(){
        System.out.println("\n--- FILTRAR VEÍCULOS DISPONÍVEIS ---");
        System.out.println("Filtrar por:");
        System.out.println("[1] Potência");
        System.out.println("[2] Número de lugares");
        System.out.println("[3] Categoria");
        System.out.print("Escolha uma opção: ");
        int filtro = scanner.nextInt();
        scanner.nextLine();

        if(!(filtro > 0 && filtro <= 3)) {
            System.out.println("Tipo de filtro incorreto!");
            return;
        }

        System.out.println("Ordenar por:");
        System.out.println("[1] Crescente");
        System.out.println("[2] Decrescente");
        System.out.print("Escolha uma opção: ");
        int ordem = scanner.nextInt();
        scanner.nextLine();

        if(!(ordem > 0 && ordem <= 2)) {
            System.out.println("Escolha um tipo de ordenação correto!");
            return;
        }

        String tipoOrdem = (ordem == 1) ? "crescente" : "decrescente";

        switch(filtro) {
            case 1:
                filtroVeiculosPorPotencia(tipoOrdem);
                break;
            case 2:
                filtroVeiculosPorLugares(tipoOrdem);
                break;
            case 3:
                filtroVeiculosPorCategoria(tipoOrdem);
                break;
        }
    }

    public void listarLocacoesAtivas(){
        if(locacoesLista.isVazia()) System.out.println("Nenhuma locação foi realizada ainda.");
        else System.out.println(locacoesLista);
    }

    private void listarVeiculosDisponiveis(){
        System.out.println("\n--- VEÍCULOS DISPONÍVEIS PARA LOCAÇÃO ---");
        System.out.println(locacoesLista.listarTodosVeiculosDisponiveis(veiculosLista));
    }

    private void filtroVeiculosPorCategoria(String tipoOrdenacao){
        System.out.println("\n--- FILTRAR VEÍCULOS POR CATEGORIA ---");
        System.out.print("Informe a categoria: ");
        String categoria = scanner.nextLine();
        
        ListaVeiculo veiculosFiltrados = locacoesLista.filtrarVeiculosPorCategoria(categoria, veiculosLista);

        if(veiculosFiltrados.isVazia()){
            System.out.println("Nenhum veículo dessa categoria foi encontrado!");
        }else{
            if(tipoOrdenacao == "crescente") System.out.println(veiculosFiltrados);
            else if (tipoOrdenacao == "decrescente") System.out.println(veiculosFiltrados.toStringReverso());        }
    }

    private void filtroVeiculosPorPotencia(String tipoOrdenacao){
        System.out.println("\n--- FILTRAR VEÍCULOS POR POTÊNCIA ---");

        System.out.print("Informe a potência: ");
        double potencia = scanner.nextDouble();
        scanner.nextLine();

        ListaVeiculo veiculosFiltrados = locacoesLista.filtrarVeiculosPorPotencia(potencia, veiculosLista);

        if(veiculosFiltrados.isVazia()){
            System.out.println("Nenhum veículo com essa potência foi encontrado!");
        }else{
            if(tipoOrdenacao == "crescente") System.out.println(veiculosFiltrados);
            else if (tipoOrdenacao == "decrescente") System.out.println(veiculosFiltrados.toStringReverso());
        }
    }

    private void filtroVeiculosPorLugares(String tipoOrdenacao){
        System.out.println("\n--- FILTRAR VEÍCULOS POR NÚMERO DE LUGARES ---");

        System.out.print("Informe o número de lugares desejado: ");
        int lugaresDesejados = scanner.nextInt();
        scanner.nextLine();

        ListaVeiculo veiculosFiltrados = locacoesLista.filtrarVeiculosPorLugares(lugaresDesejados, veiculosLista);

        if(veiculosFiltrados.isVazia()){
            System.out.println("Nenhum veículo com essa quantidade de lugares foi encontrado!");
        }else{
            if(tipoOrdenacao == "crescente") System.out.println(veiculosFiltrados);
            else if (tipoOrdenacao == "decrescente") System.out.println(veiculosFiltrados.toStringReverso());
        }
    }
}
