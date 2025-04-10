package org.locadora.Models;

public class ListaClientes extends Lista<Clientes>{
    private No<Clientes> inicio;
    No<Clientes> obtemCpf;

    public Clientes buscarPorCpf(String cpf){
        return null;
    }

    public boolean editarClientes(String cpf, Clientes clienteAtualizado){
        return false;
    }

    public boolean removerPorCpf(String cpf){
        return false;
    }
    public void listarClientesDoInicio() {
        No<Clientes> atual = inicio;
        while (atual != null) {
            System.out.println(atual.getElemento().toString());
            atual = atual.getProximo();
        }
    }

    public void listarClientesDoFim() {
        No<Clientes> atual = fim;
        while (atual != null) {
            System.out.println(atual.getElemento().toString());
            atual = atual.getAnterior();
        }
    }
}
