package org.locadora.Menus;

import org.locadora.Models.Categoria;
import org.locadora.Models.ListaCategoria;
import org.locadora.Models.No;
import org.locadora.Validadores.Validacoes;

import java.util.Scanner;

public class MenuCategoria {
    private ListaCategoria categoriasLista;
    private Validacoes valid;
    private Scanner scanner;

    public MenuCategoria(ListaCategoria categoriasLista, Validacoes valid){
        this.categoriasLista = categoriasLista;
        this.valid = valid;
        this.scanner = new Scanner(System.in);
    }

    public void exibir(){
        int opcao;
        do {
            System.out.println("\n===== MENU CATEGORIAS =====");
            System.out.println("[1] Incluir Categoria");
            System.out.println("[2] Excluir Categoria");
            System.out.println("[3] Editar Categoria");
            System.out.println("[4] Listar Categorias");
            System.out.println("[0] Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    incluirCategoria();
                    break;
                case 2:
                    excluirCategoria();
                    break;
                case 3:
                    editarCategoria();
                    break;
                case 4:
                    System.out.println(categoriasLista.toString());
                    break;
            }
        } while (opcao != 0);
    }

    private void incluirCategoria(){
        System.out.println("\n--- INSERIR CATEGORIA ---");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        // Não permite o cadastro de novas categorias se o id já existir no sistema
        if(categoriasLista.getById(id) != null){
            System.out.println("Esse id já está vinculado a uma categoria existente.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        // Não permite o cadastro de novas categorias com nome vazio
        if (nome.isEmpty()) {
            System.out.println("Nome da categoria não pode ser vazio!");
            return;
        }

        Categoria novaCategoria = new Categoria(id, nome);

        categoriasLista.insereFim(novaCategoria);
    }

    private void editarCategoria(){
        System.out.println("\n--- EDITAR CATEGORIA ---");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        // Verifica se a categoria existe no sistema
        if(categoriasLista.getById(id) == null){
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        // Não permite a atualização de categorias com nome vazio
        if (nome.isEmpty()) {
            System.out.println("Nome da categoria não pode ser vazio!");
            return;
        }

        Categoria atualizacaoCategoria = new Categoria(id, nome);

        if(categoriasLista.editar(id, atualizacaoCategoria)) System.out.println("Categoria atualizada com sucesso!");
        else System.out.println("Ocorreu um erro na atualização da categoria");
    }

    private void excluirCategoria(){
        System.out.println("\n--- EXCLUIR CATEGORIA ---");
        System.out.println("--- CATEGORIAS EM SISTEMA ---");
        System.out.println(categoriasLista);
        System.out.print("Informe o ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        No<Categoria> categoria = categoriasLista.getById(id);

        if(categoria == null){
            System.out.println("Categoria não encontrada.");
            return;
        }

        if (valid.existeVeiculoComCategoria(id)) {
            System.out.println("Não é possível excluir: existem veículos vinculados a esta categoria!");
            return;
        }

        if(categoriasLista.remove(categoria.getElemento())){
            System.out.println("Categoria excluída com sucesso!");
        }
    }
}
