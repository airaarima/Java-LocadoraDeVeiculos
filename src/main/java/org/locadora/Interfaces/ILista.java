package org.locadora.Interfaces;

public interface ILista<T> {
    void insereInicio(T elemento);
    void insereFim(T elemento);
    boolean isVazia();
    boolean remove(T elemento);
    int tamanho();
}
