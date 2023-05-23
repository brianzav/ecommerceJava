/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mercadosenacDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luan.vviana
 */
public class VendasDAO {
    
        public static float Venda(String produto){
        float sumEstoque = 0;
        Connection conexao = null;
        
        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - Abrir conexão
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercadoSENAC", "root", "");
            
            //Passo 3 - Criar o comando
            PreparedStatement comandoSQL =  conexao.prepareStatement("Select sum(totalEstoque) as somaPreco from( select descricao, preco*quantidade as totalEstoque from produtosSENAC where descricao like '%" + produto + "%') as tab1");
            
            
            //comandoSQL.setString(4, processador);
            
            //ResultSet = Tabela na memória
            ResultSet rs = comandoSQL.executeQuery();
            
            if (rs.next()) {
                sumEstoque = rs.getInt("somaPreco");
                System.out.println(sumEstoque);
            }
               
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return sumEstoque;
    
}
        
    public static String ProdutoVendas(int produto){
        String descricao = "a";
        Connection conexao = null;
        
        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - Abrir conexão
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercadoSENAC", "root", "");
            
            //Passo 3 - Criar o comando
            PreparedStatement comandoSQL =  conexao.prepareStatement("SELECT descricao FROM produtosSenac WHERE idProduto = " + produto + " LIMIT 1");
            
            
            //comandoSQL.setString(4, processador);
            
            //ResultSet = Tabela na memória
            ResultSet rs = comandoSQL.executeQuery();
            
            if (rs.next()) {
                descricao = rs.getString("descricao");
                System.out.println(descricao);
            }
               
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return descricao;
    }
        
    
    public static float precoProdutoVenda(int produto){
        float precoProduto = 0;
        Connection conexao = null;
        
        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - Abrir conexão
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercadoSENAC", "root", "");
            
            //Passo 3 - Criar o comando
            PreparedStatement comandoSQL =  conexao.prepareStatement("SELECT preco FROM produtosSenac WHERE idProduto = " + produto + " LIMIT 1");
            
            
            //comandoSQL.setString(4, processador);
            
            //ResultSet = Tabela na memória
            ResultSet rs = comandoSQL.executeQuery();
            
            if (rs.next()) {
                precoProduto = rs.getInt("preco");
                System.out.println(precoProduto);
            }
               
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return precoProduto;
    }
    
        public static String nomeClienteVendas(String documento){
        String nomeCliente = "a";
        Connection conexao = null;
        
        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - Abrir conexão
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercadoSENAC", "root", "");
            
            //Passo 3 - Criar o comando
            PreparedStatement comandoSQL =  conexao.prepareStatement("SELECT nome_cliente FROM clientesSenac WHERE CPF_cliente LIKE '%" + documento + "%' LIMIT 1");
            
            
            //comandoSQL.setString(4, processador);
            
            //ResultSet = Tabela na memória
            ResultSet rs = comandoSQL.executeQuery();
            
            if (rs.next()) {
                nomeCliente = rs.getString("nome_cliente");
                System.out.println(nomeCliente);
            }
               
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return nomeCliente;
    }
        
        public static String CPFCliente(String documento){
        String cpfCli = "1";
        Connection conexao = null;
        
        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - Abrir conexão
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercadoSENAC", "root", "");
            
            //Passo 3 - Criar o comando
            PreparedStatement comandoSQL =  conexao.prepareStatement("SELECT CPF_cliente FROM clientesSenac WHERE CPF_cliente LIKE '%" + documento + "%' LIMIT 1");
            
            
            //comandoSQL.setString(4, processador);
            
            //ResultSet = Tabela na memória
            ResultSet rs = comandoSQL.executeQuery();
            
            if (rs.next()) {
                cpfCli = rs.getString("CPF_cliente");
                System.out.println(cpfCli);
            }
               
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cpfCli;
    }
}
