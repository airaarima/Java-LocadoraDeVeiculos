package org.locadora.Menus;

import org.locadora.Models.ListaLocacao;
import org.locadora.Validadores.Validacoes;
import org.locadora.Models.ListaCliente;
import org.locadora.Models.Cliente;
import org.locadora.Models.No;


import java.util.Scanner;

public class MenuClientes {
    private Validacoes valid;
    private ListaCliente listaClientes;
    private ListaLocacao listaLocacoes;
    private Scanner scanner;

    public MenuClientes(ListaCliente listaClientes, Validacoes valid, ListaLocacao listaLocacoes) {
        this.listaClientes = listaClientes;
        this.scanner = new Scanner(System.in);
        this.valid = valid;
        this.listaLocacoes = listaLocacoes;
    }

    public void exibir() {
        char opcao;
        do {
            System.out.println("\n===== MENU CLIENTES =====");
            System.out.println("[1] Cadastrar Cliente");
            System.out.println("[2] Excluir Cliente");
            System.out.println("[3] Editar Cliente");
            System.out.println("[4] Listar Clientes Pelo Inicio ");
            System.out.println("[5] Listar Clientes Pelo Final");
            System.out.println("[0] Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.next().charAt(0);
            scanner.nextLine();

            switch (opcao) {
                case '1':
                    cadastrarCliente();
                    break;
                case '2':
                    excluirCliente();
                    break;
                case '3':
                    editarCliente();
                    break;
                case '4':
                    System.out.println(listaClientes.listarClientesDoFim());
                    break;
                case '5':
                    System.out.println(listaClientes.listarClientesDoInicio());
                    break;
                case '0':
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != '0');
    }

    // LÓGICA PARA CADASTRAR UM NOVO CLIENTE
    public void cadastrarCliente() {
        String cpf, nome, cnh, telefone;
        boolean cpfValido = false, cnhValida = false, nomeValido = false, telefoneValido = false;


        // --- CPF
        do {
            System.out.print("Digite o CPF do cliente (Ex: xxx.xxx.xxx-xx) ou 0 para cancelar a qualquer momento: ");
            cpf = scanner.nextLine().trim();

            if (cpf.equals("0")) {
                System.out.println("Voltando para o menu anterior...");
                return;
            }
            if (!valid.cpfValido(cpf)) {
                System.out.println("CPF INVÁLIDO! O CPF digitado não contém 11 digitos ou contém caracteres inválidos! (Ex: 123.456.789-01)");
                continue;
            }

            if (valid.existeCpf(cpf)) {
                System.out.println("CPF INVÁLIDO! Esse CPF já existe no sistema!");
                continue;
            }
            cpfValido = true;
        } while (!cpfValido);


        // --- NOME
        do {
            System.out.print("Digite o nome do cliente (Ex: 'João Almeida da Silva'): ");
            nome = scanner.nextLine().trim();

            if (nome.equals("0")) {
                System.out.println("Voltando para o menu anterior...");
                return;
            }

            if (!valid.validaNome(nome)) {
                System.out.println("NOME INVÁLIDO! Digite um nome válido (Ex: 'João Almeida da Silva')");
                continue;
            }
            nomeValido = true;
        } while (!nomeValido);


        // --- CNH
        do {
            System.out.print("Digite a identificação do CNH (deve conter 11 dígitos exatos): ");
            cnh = scanner.nextLine().trim();
            if (cnh.equals("0")) {
                System.out.println("Voltando para o menu anterior...");
                return;
            }

            if (!valid.validaCnh(cnh)) {
                System.out.println("CNH INVÁLIDO! Digite um CNH com até 11 números");
                continue;
            }
            if (valid.existeCnh(cnh)) {
                System.out.println("CNH INVÁLIDO! Essa CNH já existe no sistema!");
                continue;
            }

            cnhValida = true;
        } while (!cnhValida);


        // --- TELEFONE
        do {
            System.out.print("Digite o número do celular (Ex: (55)991234567): ");
            telefone = scanner.nextLine().trim();

            if (telefone.equals("0")) {
                System.out.println("Voltando para o menu anterior...");
                return;
            }

            if (!valid.validaTelefone(telefone)) {
                System.out.println("NÚMERO INVÁLIDO! Digite um número de telefone válido (Ex: '(55)123456789')");
                continue;
            }

            telefoneValido = true;
        } while (!telefoneValido);

        Cliente novoCliente = new Cliente(nome, cpf, cnh, telefone);
        listaClientes.adicionarCliente(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }


    // LÓGICA PARA EXCLUIR UM CLIENTE
    public void excluirCliente() {
        String excluirCpf;
        boolean excluido = false;
        do {
            System.out.println("Digite o CPF do cliente que deseja excluir ou 0 para cancelar: ");
            excluirCpf = scanner.nextLine().trim();
            if (excluirCpf.equals("0")) {
                System.out.println("Voltando para o menu anterior...");
                return;
            }
            if (!valid.cpfValido(excluirCpf)) {
                System.out.println("CPF INVÁLIDO! O CPF digitado não contém 11 digitos ou contém caracteres inválidos! (Ex: 123.456.789-01)");
                continue;
            }
            excluido = true;
        } while (!excluido);
        boolean sucesso = listaClientes.removerCliente(excluirCpf, listaLocacoes);
        if (sucesso) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Esse cliente possui locações ativas, não é possível excluí-lo!");
        }
    }

    // LÓGICA DE EDITAR UM CLIENTE
    // EDITAR POR NOME
    public void editarNome(Cliente cliente) {
        String novoNome;
        boolean nomeValido = false;
        do {
            System.out.print("Digite o novo nome (Ex: 'João Almeida da Silva')ou 0 para cancelar: ");
            novoNome = scanner.nextLine().trim();

            if (novoNome.equals("0")) {
                System.out.println("Voltando para o menu anterior...");
                return;
            }

            if (!valid.validaNome(novoNome)) {
                System.out.println("NOME INVÁLIDO! Digite um nome válido (Ex: 'João Almeida')");
                continue;
            }

            nomeValido = true;
        } while (!nomeValido);
        cliente.setNome(novoNome);

        if (listaClientes.editarClientes(cliente.getCpf(), cliente)) {
            System.out.println("Nome atualizado para " + novoNome);
        }
    }


    // EDITAR POR CPF
    public void editarCpf(Cliente cliente) {
        String cpfAntigo = cliente.getCpf();
        String novoCpf;
        boolean cpfValido = false;
        do {
            System.out.print("Digite o novo CPF (xxx.xxx.xxx-xx) ou 0 para cancelar: ");
            novoCpf = scanner.nextLine().trim();

            if (novoCpf.equals("0")) {
                System.out.println("Voltando para o menu anterior...");
                return;
            }

            if (!valid.cpfValido(novoCpf)) {
                System.out.println("CPF INVÁLIDO! O CPF digitado não contém 11 digitos ou contém caracteres inválidos!");
                continue;
            }

            if (valid.existeCpf(novoCpf)) {
                System.out.println("CPF INVÁLIDO! O CPF digitado já existe no sistema!");
                continue;
            }

            cpfValido = true;
        } while (!cpfValido);
        cliente.setCpf(novoCpf);

        if (listaClientes.editarClientes(cpfAntigo, cliente)) {
            System.out.println("CPF alterado para " + novoCpf);
        }
    }


    // EDITAR POR CNH
    public void editarCnh(Cliente cliente) {
        String novoCnh;
        boolean cnhValida = false;

        do {
            System.out.println("Digite o novo número do CNH (deve conter 11 dígitos exatos) ou 0 para cancelar: ");
            novoCnh = scanner.nextLine().trim();

            if (novoCnh.equals("0")) {
                System.out.println("Voltando para o menu anterior...");
                return;
            }

            if (!valid.validaCnh(novoCnh)) {
                System.out.println("CNH INVÁLIDO! Essa CNH ja está cadastrada no sistema!");
                continue;
            }

            if (valid.existeCnh(novoCnh)) {
                System.out.println("CNH INVÁLIDO! O CNH digitado já existe no sistema!");
                continue;
            }

            cnhValida = true;
        } while (!cnhValida);
        cliente.setCnh(novoCnh);

        if (listaClientes.editarClientes(cliente.getCpf(), cliente)) {
            System.out.println("CNH alterado para " + novoCnh);
        }
    }


    // EDITAR POR TELEFONE
    public void editarTelefone(Cliente cliente) {
        String novoTelefone;
        boolean telefoneValido = false;
        do {
            System.out.print("Digite o novo número de telefone (Ex: (55)123456789) ou 0 para cancelar: ");
            novoTelefone = scanner.nextLine().trim();

            if (novoTelefone.equals("0")) {
                System.out.println("Cancelando edição...");
                return;
            }

            if (!valid.validaTelefone(novoTelefone)) {
                System.out.println("NÚMERO INVÁLIDO! Digite um número de telefone válido (Ex: (55)123456789))");
                continue;
            }

            telefoneValido = true;
        } while (!telefoneValido);

        cliente.setTelefone(novoTelefone);

        if (listaClientes.editarClientes(cliente.getCpf(), cliente)) {
            System.out.println("Número de telefone alterado para " + novoTelefone);
        }
    }


    // INTERFACE DE EDIÇÃO
    public void editarCliente() {
        String cpf;
        System.out.println("\n--- Editar Cliente ---");

        System.out.println("Digite o CPF que deseja editar: ");
        cpf = scanner.nextLine().trim();

        if (cpf.equals("0")) {
            System.out.println("Cancelando edição...");
            return;
        }

        No<Cliente> procuraCpf = listaClientes.buscarPorCpf(cpf);
        if (procuraCpf == null) {
            System.out.println("Esse CPF não existe no sistema");
            return;
        }

        Cliente clienteOriginal = procuraCpf.getElemento();

        Cliente clienteEditado = new Cliente(
                clienteOriginal.getNome(),
                clienteOriginal.getCpf(),
                clienteOriginal.getCnh(),
                clienteOriginal.getTelefone()
        );

        char opcao;
        do {
            System.out.println("[1] Editar CPF");
            System.out.println("[2] Editar Nome");
            System.out.println("[3] Editar CNH");
            System.out.println("[4] Editar Telefone");
            System.out.println("[0] Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.next().charAt(0);
            scanner.nextLine();

            switch (opcao) {
                case '1':
                    editarCpf(clienteEditado);
                    break;
                case '2':
                    editarNome(clienteEditado);
                    break;
                case '3':
                    editarCnh(clienteEditado);
                    break;
                case '4':
                    editarTelefone(clienteEditado);
                    break;
                case '5':
                    if (listaClientes.editarClientes(cpf, clienteEditado)) {
                        System.out.println("Alterações feitas com sucesso!");
                        return;
                    } else {
                        System.out.println("Erro ao salvar alterações!");
                    }
                    break;
                case '0':
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != '0');
    }
}
