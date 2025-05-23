package org.locadora;

import java.util.Scanner;

import org.locadora.Menus.MenuCategoria;
import org.locadora.Menus.MenuClientes;
import org.locadora.Menus.MenuLocacao;
import org.locadora.Menus.MenuVeiculos;
import org.locadora.Models.ListaCategoria;
import org.locadora.Models.ListaCliente;
import org.locadora.Models.ListaLocacao;
import org.locadora.Models.ListaVeiculo;
import org.locadora.Validadores.Validacoes;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ListaVeiculo veiculos = new ListaVeiculo();
    private static ListaCliente clientes = new ListaCliente();
    private static ListaCategoria categorias = new ListaCategoria();
    private static ListaLocacao locacoes = new ListaLocacao();
    private static Validacoes valid = new Validacoes(clientes, veiculos, categorias);
    private static MenuLocacao menuLocacoes = new MenuLocacao(locacoes, clientes, veiculos);
    private static MenuCategoria menuCategoria = new MenuCategoria(categorias, valid);
    private static MenuClientes menuClientes = new MenuClientes(clientes, valid, locacoes);
    private static MenuVeiculos menuVeiculos = new MenuVeiculos(veiculos, categorias, locacoes);

    public static void main(String[] args) {
        // Realiza a leitura dos arquivos CSV
        carregarDadosCsv();

        char opcao;
        do {
            System.out.println("\n===== LOCADORA DE VEÍCULOS =====");
            System.out.println("[1] Gerenciar Clientes");
            System.out.println("[2] Gerenciar Veículos");
            System.out.println("[3] Gerenciar Categorias");
            System.out.println("[4] Gerenciar Locações");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.next().charAt(0);
            scanner.nextLine();

            switch (opcao) {
                case '1':
                    menuClientes.exibir();
                    break;
                case '2':
                    menuVeiculos.exibir();
                    break;
                case '3':
                    menuCategoria.exibir();
                    break;
                case '4':
                    menuLocacoes.exibir();
                    break;
                case '0':
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != '0');
    }

    private static void carregarDadosCsv() {
        try {
            categorias.lerCategoriasCsv();
            veiculos.lerVeiculosCsv(categorias);
            clientes.lerClientesCsv();
            System.out.println("Dados carregados com sucesso!");
            locacoes.lerLocacoesCsv(clientes, veiculos);
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados do arquivo CSV: " + e.getMessage());
        }
    }
}
