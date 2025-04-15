package org.locadora.Validadores;

import org.locadora.Models.Categoria;
import org.locadora.Models.ListaCategoria;
import org.locadora.Models.ListaCliente;
import org.locadora.Models.ListaVeiculo;
import org.locadora.Models.No;
import org.locadora.Models.Veiculo;

public class Validacoes {
    ListaCliente cliente;
    ListaVeiculo veiculo;
    ListaCategoria categoria;

    public Validacoes(ListaCliente cliente, ListaVeiculo veiculo, ListaCategoria categoria) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.categoria = categoria;
    }

    public boolean cpfValido(String cpf) {
        // retorna se o cpf está no formato certo (xxx.xxx.xxx-xx)
        return cpf != null && cpf.matches("^[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}+$");
    }

    public boolean existeCpf(String cpf) {
        // retorna se o cpf digitado ja está cadastrado no sistema
        return cliente.buscarPorCpf(cpf) != null;
    }

    public boolean validaNome(String nome) {
        // permite somente letras com espaço no meio para sobrenome
        return nome != null && nome.matches("^[a-zA-Zà-úÀ-Ú ]+$");
    }

    public boolean validaTelefone(String telefone) {
        // permite somente números de 0 a 9 com no exatos 11 caracteres numéricos
        return telefone != null && telefone.matches("^\\([0-9]{2}\\)[0-9]{9}$");
    }

    public boolean validaCnh(String cnh) {
        ;
        // permite 11 digitos de 0 a 9
        return cnh != null && cnh.matches("^[0-9]{11}+$");
    }

    public boolean existeCnh(String cnh){
        return cliente.buscarPorCnh(cnh) != null;

    }

    public boolean existeVeiculoComCategoria(int idCategoria){
        No<Veiculo> atual = veiculo.getInicio();
        while (atual != null) {
            if (atual.getElemento().getCategoria().getIdentificador() == idCategoria) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }
    public Categoria validarCategoriaPorNome(String nomeCategoria, ListaCategoria listaCategorias){
            No<Categoria> atual = listaCategorias.getInicio();
            while (atual != null) {
                if (atual.getElemento().getNome().equalsIgnoreCase(nomeCategoria)) {
                    return atual.getElemento();
                }
                atual = atual.getProximo();
            }
            return null;
        }
    
}
