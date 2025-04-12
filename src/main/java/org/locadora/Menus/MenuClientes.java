package org.locadora.Menus;

import org.locadora.Models.ListaClientes;
import org.locadora.Models.Clientes;


import java.util.Scanner;

public class MenuClientes {
    private ListaClientes listaClientes;
    private Scanner scanner;

    public MenuClientes(ListaClientes listaClientes){
        this.listaClientes = listaClientes;
        this.scanner = new Scanner(System.in);
    }

    public void exibir(){
        int opcao;
        do {
            System.out.println("\n===== MENU CLIENTES =====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Excluir Cliente");
            System.out.println("3. Editar Cliente");
            System.out.println("4. Listar Clientes");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    excluirCliente();
                    break;
                case 3:
                    editarCliente();
                    break;
                case 4:
                    System.out.println(listaClientes.toString());
                    break;
            }
        } while (opcao != 0);
    }
    public void cadastrarCliente() {
        String cpf;
        System.out.println("\n--- CADASTRAR CLIENTE ---");

        System.out.print("CPF: ");
        cpf = scanner.nextLine().trim();

        if (cpf.isEmpty()) {
            System.out.println("Erro: CPF não pode ser vazio!");
            return;
        }

        if (!listaClientes.cpfValido(cpf)) {
            System.out.println("Erro: CPF no formato inválido!");
            return;
        }
        if (listaClientes.existeCpf(cpf)) {
            System.out.println("Erro: Esse CPF já existe no sistema!");
        }

        // NOME
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Erro: Nome não pode ser vazio!");
            return;
        }

        System.out.print("CNH: ");
        String cnh = scanner.nextLine().trim();
        if (cnh.isEmpty()) {
            System.out.println("Erro: CNH não pode ser vazia!");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();
        if (telefone.isEmpty()) {
            System.out.println("Erro: Telefone não pode ser vazio!");
            return;
        }

        Clientes novoCliente = new Clientes(nome, cpf, cnh, telefone);

        listaClientes.insereFim(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void excluirCliente(){

    }
    public void editarCliente(){

    }
}
