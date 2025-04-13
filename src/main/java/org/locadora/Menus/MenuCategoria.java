package org.locadora.Menus;

import org.locadora.Models.Categoria;
import org.locadora.Models.CategoriaLista;

import java.util.Scanner;

public class MenuCategoria {
    private CategoriaLista categoriasLista;
    private Scanner scanner;

    public MenuCategoria(CategoriaLista categoriasLista){
        this.categoriasLista = categoriasLista;
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
//                    excluirCategoria();
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
}
