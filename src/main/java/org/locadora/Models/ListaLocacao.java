package org.locadora.Models;

public class ListaLocacao extends Lista<Locacao> {

    public boolean verificarVeiculoDisponivel(String placa) {
        No<Locacao> atual = getInicio();
        while (atual != null) {
            Locacao locacao = atual.getElemento();
            if (locacao.getVeiculo().equals(placa)) {
                return false;
            }
            atual = atual.getProximo();
        }
        return true;
    }

        public boolean clientePossuiLocacaoAtiva(String cpf){
            No<Locacao> atual = getInicio();
            while (atual != null) {
                Locacao locacao = atual.getElemento();
                if (locacao.getCliente().equals(cpf)) {
                    return false;
                }
                atual = atual.getProximo();
            }
            return true;
    }
    public void locarVeiculo(Locacao locacao) {
        super.insereFim(locacao);
    }

    public void devolverVeiculo(Locacao locacao) {
        super.remove(locacao);
    }

    public String listarEmOrdemCrescente() {
        return super.toString();
    }

    public String listarEmOrdemDecrescente() {
        return super.toStringReverso();
    }
}
