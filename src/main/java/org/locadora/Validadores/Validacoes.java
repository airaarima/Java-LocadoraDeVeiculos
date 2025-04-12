package org.locadora.Validadores;

import org.locadora.Models.ListaClientes;

public class Validacoes {
    ListaClientes cliente;

    public Validacoes(ListaClientes cliente){
        this.cliente = cliente;
    }
    public boolean cpfValido(String cpf){
        // retorna se o cpf está no formato certo (xxx.xxx.xxx-xx)
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public boolean existeCpf(String cpf){
        // retorna se o cpf digitado ja está cadastrado no sistema
        return cliente.buscarPorCpf(cpf) != null;
    }

    public boolean validaNome(String nome){
        // permite somente letras com espaço no meio para sobrenome. Ex: (Ismael Oliveira)
        return nome != null && nome.matches("\\p{L}+( \\p{L}+)*");
    }

    public boolean validaTelefone(String telefone){
        // permite somente números de 0 a 9 com no mínimo 11 caracteres
        return telefone != null && telefone.matches("\\d{10,11}");
    }
}
