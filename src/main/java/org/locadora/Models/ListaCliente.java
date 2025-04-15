package org.locadora.Models;

public class ListaCliente extends Lista<Cliente> {

    public No<Cliente> buscarPorCpf(String cpf) {
        No<Cliente> encontrarElemento = getInicio();
        while (encontrarElemento != null) {
            if (encontrarElemento.getElemento().getCpf().equals(cpf)) {
                return encontrarElemento;
            }
            encontrarElemento = encontrarElemento.getProximo();
        }
        return null;
    }

    public No<Cliente> buscarPorCnh(String cnh){
        No<Cliente> encontrarElemento = getInicio();
        while (encontrarElemento != null) {
            if (encontrarElemento.getElemento().getCnh().equals(cnh)) {
                return encontrarElemento;
            }
            encontrarElemento = encontrarElemento.getProximo();
        }
        return null;
    }

    public void adicionarCliente(Cliente cliente) {
        super.insereFim(cliente);
    }

    public boolean editarClientes(String cpf, Cliente clienteAtualizado) {
        No<Cliente> editarElemento = buscarPorCpf(cpf);
        if (editarElemento == null) {
            return false;
        }
        editarElemento.setElemento(clienteAtualizado);
        return true;
    }

    public boolean removerCliente(String cpf) {
        No<Cliente> excluirElemento = buscarPorCpf(cpf);
        if (excluirElemento == null) {
            return false;
        }
        Cliente clienteExcluido = excluirElemento.getElemento();
        return super.remove(clienteExcluido);
    }

    public String listarClientesDoInicio() {
        return super.toStringReverso();

    }


    public String listarClientesDoFim() {
        return super.toString();
    }
}

