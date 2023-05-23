/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mercadosenacDefaults;

/**
 *
 * @author luan.vviana
 */
public class Vendas {
    String cliente;
    int codProduto;
    int quantidade;
    String produto;
    float valorProduto;
    String data;

    
    public Vendas(String cliente, int codProduto, int quantidade, String produto, float valorProduto) {
        this.cliente = cliente;
        this.codProduto = codProduto;
        this.quantidade = quantidade;
        this.produto = produto;
        this.valorProduto = valorProduto;
    }

    
    
    
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public float getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(float valorProduto) {
        this.valorProduto = valorProduto;
    }
    
    
    
}
