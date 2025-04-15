package org.locadora.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ListaVeiculo extends Lista<Veiculo> {

    public No<Veiculo> buscarPorPlaca(String placa) {
        No<Veiculo> atual = getInicio();
        while (atual != null) {
            if (atual.getElemento().getPlaca().equals(placa)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public void adicionar(Veiculo veiculo) {
        insereFim(veiculo);
    }

    public boolean editar(String placa, Veiculo veiculoAtualizado) {
        No<Veiculo> veiculoParaEditar = buscarPorPlaca(placa);
        if (veiculoParaEditar == null) return false;

        veiculoParaEditar.setElemento(veiculoAtualizado);
        return true;
    }

    public boolean remover(String placa) {
        No<Veiculo> veiculoParaRemover = buscarPorPlaca(placa);
        if (veiculoParaRemover == null) return false;

        Veiculo removido = veiculoParaRemover.getElemento();
        return remove(removido);
    }

    public void listarDoInicio() {
        No<Veiculo> atual = getInicio();
        while (atual != null) {
            System.out.println(atual.getElemento().toString());
            atual = atual.getProximo();
        }
    }

    public void listarDoFim() {
        No<Veiculo> atual = getFim();
        while (atual != null) {
            System.out.println(atual.getElemento().toString());
            atual = atual.getAnterior();
        }
    }
    public void lerVeiculosCsv(ListaCategoria listaCategorias) {
        String caminhoCsv = "src/main/java/org/locadora/Data/Veiculos.csv";
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCsv))) {
            String linha;
            br.readLine(); // pula o cabeçalho
    
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
    
                String placa = dados[0];
                String modelo = dados[2];
                String marca = dados[1];
                int ano = Integer.parseInt(dados[3]);
                double potencia = Double.parseDouble(dados[4]);
                int numeroLugares = Integer.parseInt(dados[5]);
    
                int idCategoria = Integer.parseInt(dados[6]);
                No<Categoria> noCategoria = listaCategorias.getById(idCategoria);
    
                if (noCategoria == null) {
                    System.out.println("Categoria com ID " + idCategoria + " não encontrada.");
                    continue;
                }
    
                Categoria categoria = noCategoria.getElemento();
    
                Veiculo veiculo = new Veiculo(placa, modelo, marca, ano, potencia, numeroLugares, categoria);
    
                insereFim(veiculo);
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
    
    }
    /* public void lerCategoriasCsv() {
        String caminhoCsv = "src/main/java/org/locadora/Data/Categorias.csv";

        try{
            FileReader arquivoCsv = new FileReader(caminhoCsv);
            BufferedReader br = new BufferedReader(arquivoCsv);
            String linha;

            // Pula o cabeçalho do csv
            br.readLine();

            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(";");
                Categoria categoria = new Categoria(Integer.parseInt(dados[0]), dados[1]);
                insereFim(categoria);
            }
        }catch (IOException exception){
            System.out.println(exception);
        }
    }
 */