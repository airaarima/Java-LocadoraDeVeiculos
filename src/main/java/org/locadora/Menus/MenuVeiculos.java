package org.locadora.Menus;

import java.util.Scanner;

import org.locadora.Models.Categoria;
import org.locadora.Models.ListaVeiculo;
import org.locadora.Models.No;
import org.locadora.Models.Veiculo;

public class MenuVeiculos {
    private ListaVeiculo veiculosLista;
    private Scanner scanner;

    public MenuVeiculos(ListaVeiculo veiculosLista){
        this.veiculosLista = veiculosLista;
        this.scanner = new Scanner(System.in);
    }

    public void exibir(){
        int opcao;
        do {
            System.out.println("\n===== MENU VEÍCULOS =====");
            System.out.println("1. Incluir Veículo");
            System.out.println("2. Excluir Veículo");
            System.out.println("3. Editar Veículo");
            System.out.println("4. Listar Veículos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    incluirVeiculo();
                    break;
                case 2:
                    excluirVeiculo();
                    break;
                case 3:
                    editarVeiculo();
                    break;
                case 4:
                    veiculosLista.listarDoInicio();
                    break;
            }
        } while (opcao != 0);
    }

    private void incluirVeiculo(){
        System.out.println("\n--- INSERIR VEÍCULO ---");
        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        if(veiculosLista.buscarPorPlaca(placa) != null){
            System.out.println("Essa placa já está vinculada a um veículo existente.");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        System.out.print("Potência: ");
        double potencia = scanner.nextDouble();
        System.out.print("Número de lugares: ");
        int numeroLugares = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Categoria (nome): ");
        String categoriaNome = scanner.nextLine();
        
        // Criação do novo veículo com todos os dados
        Categoria categoria = new Categoria(numeroLugares, categoriaNome); 
        Veiculo novoVeiculo = new Veiculo(placa, modelo, marca, ano, potencia, numeroLugares, categoria);

        veiculosLista.adicionar(novoVeiculo);
    }

    private void excluirVeiculo(){
        System.out.println("\n--- EXCLUIR VEÍCULO ---");
        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        if(veiculosLista.buscarPorPlaca(placa) == null){
            System.out.println("Veículo não encontrado.");
            return;
        }

        if(veiculosLista.remover(placa)) {
            System.out.println("Veículo excluído com sucesso!");
        } else {
            System.out.println("Ocorreu um erro na exclusão do veículo.");
        }
    }

    private void editarVeiculo(){
        System.out.println("\n--- EDITAR VEÍCULO ---");
        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        No<Veiculo> veiculoEncontrado = veiculosLista.buscarPorPlaca(placa);
        if(veiculoEncontrado == null){
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Novo modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Nova marca: ");
        String marca = scanner.nextLine();
        System.out.print("Novo ano: ");
        int ano = scanner.nextInt();
        System.out.print("Nova potência: ");
        double potencia = scanner.nextDouble();
        System.out.print("Novo número de lugares: ");
        int numeroLugares = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Nova categoria (nome): ");
        String categoriaNome = scanner.nextLine();

        // Atualizar o veículo
        Categoria categoria = new Categoria(numeroLugares, categoriaNome); // Criar nova categoria se necessário
        Veiculo veiculoAtualizado = new Veiculo(placa, modelo, marca, ano, potencia, numeroLugares, categoria);

        if(veiculosLista.editar(placa, veiculoAtualizado)) {
            System.out.println("Veículo atualizado com sucesso!");
        } else {
            System.out.println("Ocorreu um erro na atualização do veículo.");
        }
    }
}
