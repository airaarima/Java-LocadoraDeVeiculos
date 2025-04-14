package org.locadora.Models;

public class ListaVeiculo extends Lista<Veiculo> {

    public No<Veiculo> buscarPorPlaca(String placa) {
        No<Veiculo> atual = getInicio();
        while (atual != null) {
            if (atual.getElemento().getPlaca().equals(placa)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public void adicionar(Veiculo veiculo) {
        insereFim(veiculo);
    }

    public boolean editar(String placa, Veiculo veiculoAtualizado) {
        No<Veiculo> veiculoParaEditar = buscarPorPlaca(placa);
        if (veiculoParaEditar == null) return false;

        veiculoParaEditar.setElemento(veiculoAtualizado);
        return true;
    }

    public boolean remover(String placa) {
        No<Veiculo> veiculoParaRemover = buscarPorPlaca(placa);
        if (veiculoParaRemover == null) return false;

        Veiculo removido = veiculoParaRemover.getElemento();
        return remove(removido);
    }

    public void listarDoInicio() {
        No<Veiculo> atual = getInicio();
        while (atual != null) {
            System.out.println(atual.getElemento().toString());
            atual = atual.getProximo();
        }
    }

    public void listarDoFim() {
        No<Veiculo> atual = getFim();
        while (atual != null) {
            System.out.println(atual.getElemento().toString());
            atual = atual.getAnterior();
        }
    }
}
