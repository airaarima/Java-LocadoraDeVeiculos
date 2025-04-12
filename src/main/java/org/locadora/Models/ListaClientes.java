package org.locadora.Models;

public class ListaClientes extends Lista<Clientes>{
    No<Clientes> obtemCpf;

    public boolean cpfValido(String cpf){
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\d{3}-\\d{2}");
    }

    public boolean existeCpf(String cpf){
        return buscarPorCpf(cpf) != null;
    }

    public No<Clientes> buscarPorCpf(String cpf){
        No<Clientes> encontrarElemento = getInicio();
        while (encontrarElemento != null){
            if(encontrarElemento.getElemento().getCpf().equals(cpf)){
                return encontrarElemento;
            }
            encontrarElemento = encontrarElemento.getProximo();
        }
        return null;
    }

    public void adicionarCliente(Clientes cliente) {
        super.insereInicio(cliente);
    }

    public boolean editarClientes(String cpf, Clientes clienteAtualizado){
        No<Clientes> editarElemento = buscarPorCpf(cpf);
        if (editarElemento == null){
            return false;
        }else
            editarElemento.setElemento(clienteAtualizado);
        return true;
    }

    public boolean removerPorCpf(String cpf){
        No<Clientes> excluirElemento = buscarPorCpf(cpf);
            if (excluirElemento == null){
             return false;
            }
            Clientes clienteExcluido = excluirElemento.getElemento();
            return super.remove(clienteExcluido);
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
