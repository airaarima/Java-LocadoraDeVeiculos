package org.locadora.Models;

public class CategoriaLista extends Lista<Categoria> {
    public boolean editar(int id, Categoria categoriaAtualizada){
        No<Categoria> editarElemento = getById(id);
        if(editarElemento == null) return false;

        editarElemento.setElemento(categoriaAtualizada);
        return true;
    }

    public No<Categoria> getById(int id){
        No<Categoria> encontrarElemento = getInicio();
        while (encontrarElemento != null && encontrarElemento.getElemento().getIdentificador() == id ) {
            encontrarElemento = encontrarElemento.getProximo();
        }
        return encontrarElemento;
    }
}
