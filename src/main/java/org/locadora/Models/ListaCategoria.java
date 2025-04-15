package org.locadora.Models;

import org.locadora.Validadores.Validacoes;

import java.io.*;

public class ListaCategoria extends Lista<Categoria> {
    private Validacoes valid;

    public ListaCategoria(Validacoes valid){
        this.valid = valid;
    }
    public boolean editar(int id, Categoria categoriaAtualizada){
        No<Categoria> editarElemento = getById(id);
        if(editarElemento == null) return false;

        editarElemento.setElemento(categoriaAtualizada);
        return true;
    }

    public No<Categoria> getById(int id){
        No<Categoria> encontrarElemento = getInicio();
        while (encontrarElemento != null){
            if(encontrarElemento.getElemento().getIdentificador() == id){
                return encontrarElemento;
            }
            encontrarElemento = encontrarElemento.getProximo();
        }
        return null;
    }

    public void lerCategoriasCsv() {
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

    public boolean excluirCategoria(int idCategoria){
        // Verifica se a categoria está atrelada a um veículo. Se sim, retorna false, identificando que a categoria não pode ser deletada
        if (valid.existeVeiculoComCategoria(idCategoria)) {
            return false;
        }
        No<Categoria> categoria = getById(idCategoria);

        if(remove(categoria.getElemento())){
            return true;
        }
        return false;
    }
}
