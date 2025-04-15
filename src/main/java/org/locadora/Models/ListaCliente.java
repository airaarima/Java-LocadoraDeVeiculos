package org.locadora.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public boolean removerCliente(String cpf, ListaLocacao listaLocacoes) {
        No<Cliente> excluirElemento = buscarPorCpf(cpf);
        if (excluirElemento == null) {
            return false;
        }
        if(listaLocacoes.clientePossuiLocacaoAtiva(cpf)){
            return false;
        }
        Cliente clienteExcluido = excluirElemento.getElemento();
        return remove(clienteExcluido);
    }

    public String listarClientesDoInicio() {
        return toStringReverso();

    }


    public String listarClientesDoFim() {
        return super.toString();
    }

    public void lerClientesCsv() {
        String caminhoCsv = "src/main/java/org/locadora/Data/Clientes.csv";

        try{
            FileReader arquivoCsv = new FileReader(caminhoCsv);
            BufferedReader br = new BufferedReader(arquivoCsv);
            String linha;

            // Pula o cabeçalho do csv
            br.readLine();

            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(";");
                Cliente cliente = new Cliente(dados[0], dados[1], dados[2], dados[3]);
                insereFim(cliente);
            }
        }catch (IOException exception){
            System.out.println(exception);
        }
    }
}

