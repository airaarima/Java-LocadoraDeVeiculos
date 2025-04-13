package org.locadora.Models;

public class ListaClientes extends Lista<Clientes> {

    public No<Clientes> buscarPorCpf(String cpf) {
        No<Clientes> encontrarElemento = getInicio();
        while (encontrarElemento != null) {
            if (encontrarElemento.getElemento().getCpf().equals(cpf)) {
                return encontrarElemento;
            }
            encontrarElemento = encontrarElemento.getProximo();
        }
        return null;
    }

    public void adicionarCliente(Clientes cliente) {
        super.insereFim(cliente);
    }

    public boolean editarClientes(String cpf, Clientes clienteAtualizado) {
        No<Clientes> editarElemento = buscarPorCpf(cpf);
        if (editarElemento == null) {
            return false;
        }
        editarElemento.setElemento(clienteAtualizado);
        return true;
    }

    public boolean removerPorCpf(String cpf) {
        No<Clientes> excluirElemento = buscarPorCpf(cpf);
        if (excluirElemento == null) {
            return false;
        }
        Clientes clienteExcluido = excluirElemento.getElemento();
        return super.remove(clienteExcluido);
    }

    public String listarClientesDoInicio() {
        return super.toStringReverso();

    }


    public String listarClientesDoFim() {
        return super.toString();
    }
}

