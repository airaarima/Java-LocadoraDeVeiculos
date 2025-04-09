package org.locadora.Models;

import org.locadora.Interfaces.ILista;

public abstract class Lista<T> implements ILista<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho = 0;

    @Override
    public void insereInicio(T elemento) {
        No<T> celula = new No<T>(elemento);
        if(this.inicio == null) {
            this.inicio = celula;
            this.fim = celula;
        }
        else {
            celula.setProximo(inicio);
            inicio.setAnterior(celula);
            inicio = celula;
        }
        this.tamanho++;
    }

    @Override
    public void insereFim(T elemento) {
        No<T> celula = new No<T>(elemento);
        if(this.inicio == null) {
            this.inicio = celula;
        }
        else{
            this.fim.setProximo(celula);
            celula.setAnterior(this.fim);
        }
        this.fim = celula;
        this.tamanho++;
    }

    @Override
    public boolean isVazia() {
        if(this.inicio == null) return true;
        return false;
    }

    @Override
    public boolean remove(T elemento) {
        No<T> i = busca(elemento);

        if(i == null) return false;

        if(i == inicio) {
            inicio = i.getProximo();
            if (inicio != null) inicio.setAnterior(null);
            else fim = null;
        } else if (i.getProximo() == null) {
            i.getAnterior().setProximo(null);
            fim = i.getAnterior();
        } else {
            i.getAnterior().setProximo(i.getProximo());
            i.getProximo().setAnterior(i.getAnterior());
        }
        this.tamanho--;
        return true;
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if(isVazia()) return "[]";

        No<T> atual = this.inicio;
        builder.append(atual.getElemento().toString()).append("\n");

        while(atual.getProximo() != null) {
            atual = atual.getProximo();
            builder.append(atual.getElemento().toString()).append("\n");
        }

        return "Alunos:\n" + builder.toString();
    }

    public  String toStringReverso(){
        StringBuilder builder = new StringBuilder();

        if(isVazia()) return "[]";

        No<T> atual = this.fim;
        builder.append(atual.getElemento().toString()).append("\n");

        while(atual.getAnterior() != null) {
            atual = atual.getAnterior();
            builder.append(atual.getElemento().toString()).append("\n");
        }

        return builder.toString();
    }

    public No<T> busca(T elemento) {
        No<T> encontrarElemento = this.inicio;
        while (encontrarElemento != null && !encontrarElemento.getElemento().equals(elemento) ) { // Enquanto 'encontrarElemento' não for nulo ou igual ao elemento que se deseja remover
            encontrarElemento = encontrarElemento.getProximo();
        }
        return encontrarElemento;
    }
}
