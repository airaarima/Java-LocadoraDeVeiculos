package org.locadora;

import org.locadora.Menus.MenuCategoria;
import org.locadora.Menus.MenuClientes;
import org.locadora.Models.CategoriaLista;
import org.locadora.Models.ListaClientes;
import org.locadora.Validadores.Validacoes;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    // Cria as listas de cada classe
    private static CategoriaLista categorias = new CategoriaLista();

    // Cria os menus de cada classe e realiza injeção de dependências
    private static MenuCategoria menuCategoria = new MenuCategoria(categorias);

    private static ListaClientes clientes = new ListaClientes();

    private static Validacoes valid = new Validacoes(clientes);

    private static MenuClientes menuClientes = new MenuClientes(clientes, valid);

    public static void main(String[] args) {
        // Realiza a leitura dos arquivos CSV
        carregarDadosCsv();

        int opcao;
        do {
            System.out.println("\n===== LOCADORA DE VEÍCULOS =====");
            System.out.println("[1] Gerenciar Clientes");
            System.out.println("[2] Gerenciar Veículos");
            System.out.println("[3] Gerenciar Categorias");
            System.out.println("[4] Gerenciar Locações");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuClientes.exibir();
                    break;
                case 2:
//                    menuVeiculos();
                    break;
                case 3:
                    menuCategoria.exibir();
                    break;
                case 4:
//                    menuLocacoes();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void carregarDadosCsv() {
        try {
            categorias.lerCategoriasCsv();
            System.out.println("Dados carregados com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados do arquivo CSV: " + e.getMessage());
        }
    }
}
