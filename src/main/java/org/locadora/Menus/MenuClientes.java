package org.locadora.Menus;

import org.locadora.Validadores.Validacoes;
import org.locadora.Models.ListaClientes;
import org.locadora.Models.Clientes;
import org.locadora.Models.No;


import java.util.Scanner;

public class MenuClientes {
    private Validacoes valid;
    private ListaClientes listaClientes;
    private Scanner scanner;

    public MenuClientes(ListaClientes listaClientes, Validacoes valid) {
        this.listaClientes = listaClientes;
        this.scanner = new Scanner(System.in);
        this.valid = valid;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n===== MENU CLIENTES =====");
            System.out.println("[1] Cadastrar Cliente");
            System.out.println("[2] Excluir Cliente");
            System.out.println("[3] Editar Cliente");
            System.out.println("[4] Listar Clientes");
            System.out.println("[0] Voltar");
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

    // LÓGICA PARA CADASTRAR UM NOVO CLIENTE
    public void cadastrarCliente() {
        String cpf;
        boolean cpf_valido = false;
        do {
            System.out.print("Digite o CPF do cliente (xxx.xxx.xxx-xx) ou 0 para cancelar a qualquer momento: ");
            cpf = scanner.nextLine().trim();

            if (cpf.equals("0")) {
                System.out.println("Cancelando cadastro...");
                return;
            }
            if (cpf.isEmpty()) {
                System.out.println("CPF INVÁLIDO! O campo CPF deve ser preenchido!");
                continue;
            }

            if (!valid.cpfValido(cpf)) {
                System.out.println("CPF INVÁLIDO! O CPF digitado não contém 11 digitos ou contém caracteres inválidos!");
                continue;
            }

            if (valid.existeCpf(cpf)) {
                System.out.println("CPF INVÁLIDO! Esse CPF já existe no sistema!");
                continue;
            }
            cpf_valido = true;
        } while (!cpf_valido);

        String nome;
        boolean nome_valido = false;
        do {
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();

            if (nome.equals("0")) {
                System.out.println("Cancelando cadastro...");
                return;
            }

            if (nome.isEmpty()) {
                System.out.println("NOME INVÁLIDO! O campo Nome não pode ser vazio!");
                continue;
            }

            if (!valid.validaNome(nome)) {
                System.out.println("NOME INVÁLIDO! Digite um nome válido (Ex: 'João Almeida')");
                continue;
            }
            nome_valido = true;
        } while (!nome_valido);


        System.out.print("CNH: ");
        String cnh = scanner.nextLine().trim();
        if (cnh.isEmpty()) {
            System.out.println("CNH INVÁLIDO! CNH não pode ser vazia!");
            return;
        }

        String telefone;
        boolean telefoneValido = false;
        do {
            System.out.print("Telefone: ");
            telefone = scanner.nextLine().trim();

            if (telefone.equals("0")) {
                System.out.println("Cancelando cadastro...");
                return;
            }

            if (telefone.isEmpty()) {
                System.out.println("NÚMERO INVÁLIDO! O campo Telefone não pode ser vazio!");
                continue;
            }

            if (!valid.validaTelefone(telefone)) {
                System.out.println("NÚMERO INVÁLIDO! Digite um número de telefone válido (Ex: '99134567891')");
                continue;
            }
            telefoneValido = true;
        } while (!telefoneValido);

        Clientes novoCliente = new Clientes(nome, cpf, cnh, telefone);
        listaClientes.adicionarCliente(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    // LÓGICA PARA EXCLUIR UM CLIENTE
    public void excluirCliente() {

    }

    // LÓGICA DE EDITAR UM CLIENTE
    // EDITAR POR NOME
    public void editarNome(Clientes cliente) {
        String novoNome;
        boolean nome_valido = false;
        do {
            System.out.print("Digite o novo nome ou 0 para cancelar: ");
            novoNome = scanner.nextLine().trim();

            if (novoNome.equals("0")) {
                System.out.println("Cancelando cadastro...");
                return;
            }

            if (novoNome.isEmpty()) {
                System.out.println("NOME INVÁLIDO! O campo nome deve ser preenchido!");
                continue;
            }

            if (!valid.validaNome(novoNome)) {
                System.out.println("NOME INVÁLIDO! Digite um nome válido (Ex: 'João Almeida')");
                continue;
            }
            nome_valido = true;
        } while (!nome_valido);
        cliente.setNome(novoNome);

        if(listaClientes.editarClientes(cliente.getCpf(), cliente)){
            System.out.println("Nome atualizado para " + novoNome);
        }
    }


    // EDITAR POR CPF
    public void editarCpf(Clientes cliente) {
        String cpfAntigo = cliente.getCpf();
        String novoCpf;
        boolean cpf_valido = false;
        do {
            System.out.print("Digite o novo CPF (xxx.xxx.xxx-xx) ou 0 para cancelar a qualquer momento: ");
            novoCpf = scanner.nextLine().trim();

            if (novoCpf.equals("0")) {
                System.out.println("Cancelando cadastro...");
                return;
            }
            if (novoCpf.isEmpty()) {
                System.out.println("CPF INVÁLIDO! O campo CPF deve ser preenchido!");
                continue;
            }

            if (!valid.cpfValido(novoCpf)) {
                System.out.println("CPF INVÁLIDO! O CPF digitado não contém 11 digitos ou contém caracteres inválidos!");
                continue;
            }

            if (valid.existeCpf(novoCpf)) {
                System.out.println("CPF INVÁLIDO! O CPF digitado já existe no sistema!");
                continue;
            }
            cpf_valido = true;
        } while (!cpf_valido);
        cliente.setCpf(novoCpf);

        if(listaClientes.editarClientes(cpfAntigo, cliente)) {
            System.out.println("CPF alterado para " + novoCpf);
        }
    }


    // EDITAR POR CNH
    public void editarCnh(Clientes cliente) {

    }


    // EDITAR POR TELEFONE
    public void editarTelefone(Clientes cliente) {
        String novoTelefone;
        boolean telefoneValido = false;
        do {
            System.out.print("Digite o novo número de telefone (somente números) ou 0 para cancelar: ");
            novoTelefone = scanner.nextLine().trim();

            if (novoTelefone.equals("0")) {
                System.out.println("Cancelando cadastro...");
                return;
            }

            if (novoTelefone.isEmpty()) {
                System.out.println("NÚMERO INVÁLIDO! O campo Telefone não pode ser vazio!");
                continue;
            }

            if (!valid.validaTelefone(novoTelefone)) {
                System.out.println("NÚMERO INVÁLIDO! Digite um número de telefone válido (Ex: '99134567891')");
                continue;
            }
            telefoneValido = true;
        } while (!telefoneValido);

        cliente.setTelefone(novoTelefone);

        if(listaClientes.editarClientes(cliente.getCpf(), cliente)){
            System.out.println("Número de telefone alterado para " + novoTelefone);
        }
    }

    // INTERFACE DE EDIÇÃO
    public void editarCliente() {
        String cpf;
        System.out.println("\n--- Editar Cliente ---");

        System.out.println("Digite o CPF que deseja editar: ");

            cpf = scanner.nextLine().trim();

        No<Clientes> procuraCpf = listaClientes.buscarPorCpf(cpf);
        if (procuraCpf == null) {
            System.out.println("Esse CPF não existe no sistema");
            return;
        }

        Clientes clienteOriginal = procuraCpf.getElemento();

        Clientes clienteEditado = new Clientes(
                clienteOriginal.getNome(),
                clienteOriginal.getCpf(),
                clienteOriginal.getCnh(),
                clienteOriginal.getTelefone()
        );

        int opcao;
        do {
            System.out.println("[1] Editar CPF");
            System.out.println("[2] Editar Nome");
            System.out.println("[3] Editar CNH");
            System.out.println("[4] Editar Telefone");
            System.out.println("[0] Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    editarCpf(clienteEditado);
                    break;
                case 2:
                    editarNome(clienteEditado);
                    break;
                case 3:
                    editarCnh(clienteEditado);
                    break;
                case 4:
                    editarTelefone(clienteEditado);
                    break;
                case 5:
                    if (listaClientes.editarClientes(cpf, clienteEditado)) {
                        System.out.println("Alterações feitas com sucesso!");
                        return;
                    } else {
                        System.out.println("Erro ao salvar alterações!");
                    }
                    break;
            }
        } while (opcao != 0);
    }
}
