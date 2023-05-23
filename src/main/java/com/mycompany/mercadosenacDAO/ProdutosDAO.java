/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mercadosenacDAO;

import com.mycompany.mercadosenacDefaults.Conexao;
import com.mycompany.mercadosenacDefaults.Produtos;
import java.rmi.server.ObjID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author luanv
 */
public class ProdutosDAO {
    
    public static boolean salvarProdutos(Produtos obj){
        boolean retorno = false;
        Connection conexao = null;
        String url = Conexao.url;
        String user = Conexao.user;
        String password = Conexao.password;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(url, user, password);

            PreparedStatement comando = conexao.
                    prepareStatement("INSERT INTO produtosSENAC(descricao, preco, validade, quantidade) "
                            + "VALUES(?,?,?,?)");

            comando.setString(1, obj.getDescricao());
            comando.setFloat(2, obj.getPreco());
            comando.setString(3, obj.getValidade());
            comando.setInt(4, obj.getQuantidade());


            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver");
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar com o banco");
            JOptionPane.showMessageDialog(null, "Produto já existente. "
                    + "Se precisar alterar a quantidade no estoque utilize a guia de consulta e altere a quantidade");
                   
        }

        return retorno;
    }
    
    
    public static ArrayList<Produtos> listar(String produto){
        ArrayList<Produtos> lista = new ArrayList<>();
        Connection conexao = null;
        
        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - Abrir conexão
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercadoSENAC", "root", "");
            
            //Passo 3 - Criar o comando
            PreparedStatement comandoSQL =  conexao.prepareStatement("SELECT * FROM produtosSENAC WHERE descricao LIKE '%"+ produto +"%'");
            
            
            //comandoSQL.setString(4, processador);
            
            //ResultSet = Tabela na memória
            ResultSet rs = comandoSQL.executeQuery();
            
            if(rs!=null){
                while(rs.next()){
                    Produtos novoObj = new Produtos();
                    novoObj.setDescricao(rs.getString("descricao"));
                    novoObj.setPreco(rs.getFloat("preco"));
                    novoObj.setValidade(rs.getString("validade")); 
                    novoObj.setQuantidade(rs.getInt("quantidade"));
                    
                    lista.add(novoObj);
                    
                }
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }
    
    
    public static int countEstoque(String produto){
        int qtdEstoque = 0;
        Connection conexao = null;
        
        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - Abrir conexão
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercadoSENAC", "root", "");
            
            //Passo 3 - Criar o comando
            PreparedStatement comandoSQL =  conexao.prepareStatement("SELECT sum(quantidade) as qtdEstoque FROM produtosSENAC WHERE descricao LIKE '%"+ produto +"%'");
            
            
            //comandoSQL.setString(4, processador);
            
            //ResultSet = Tabela na memória
            ResultSet rs = comandoSQL.executeQuery();
            
            if (rs.next()) {
                qtdEstoque = rs.getInt("qtdEstoque");
                System.out.println(qtdEstoque);
            }
               
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return qtdEstoque;
    }
    
    
    public static float sumEstoque(String produto){
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
    
      public static boolean atualizaEstoque(Produtos obj, String descricao){
      boolean retorno = false;
      Connection conexao = null;
      
      
      try {
            //Receita de bolo JDBC
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Passo 2 - Abrir conexão
            //"jdbc:mysql//localhost:3306/basenotafiscal", "root", "")
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mercadoSENAC", "root", "");
            
            //Passo 3 - Criar comando SQL
            PreparedStatement comando = conexao.prepareStatement("UPDATE produtosSENAC SET descricao = ?, preco = ?, validade = ?, quantidade = ? WHERE descricao LIKE '%" + descricao +"%'");

            comando.setString(1, obj.getDescricao());
            comando.setFloat(2, obj.getPreco());
            comando.setString(3, obj.getValidade());
            comando.setInt(4, obj.getQuantidade());
            
            
            //Passo 4 - Executar comando SQL
            //comando.setInt(1, obj.getCodigo());
            //comando.setString(2, obj.getMarca());
           // comando.setString(3, obj.getHD());
            //comando.setString(4, obj.getProcessador());
            
            
            //comando.setDouble(2, obj.getValorNota());
            
            int linhasAfetadas = comando.executeUpdate();
            
            if(linhasAfetadas>0){
                retorno = true;
            }
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver");
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar com o banco");
        }
        
        
        return retorno;

    }
}
