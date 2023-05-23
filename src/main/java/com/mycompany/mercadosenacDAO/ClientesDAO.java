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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.mercadosenacDefaults.Clientes;
import javax.swing.JOptionPane;
import com.mycompany.mercadosenacDefaults.Conexao;

public class ClientesDAO {

    public static boolean salvar(Clientes obj) {
        boolean retorno = false;
        Connection conexao = null;
        String url = Conexao.url;
        String user = Conexao.user;
        String password = Conexao.password;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(url, user, password);
            PreparedStatement comando = conexao.
                    prepareStatement("INSERT INTO clientesSenac (nome_cliente, email_cliente, dtNascimento_cliente, CPF_cliente, tel_cliente, estadoCivil_cliente, genero_cliente, estado_cliente, cidade_cliente, CEP_cliente, rua_cliente, numeroCasa_cliente, complementoEnd_cliente) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

            comando.setString(1, obj.getNome());
            comando.setString(2, obj.getEmail());
            comando.setString(3, obj.getDataNascimento());
            comando.setString(4, obj.getCPF());
            comando.setString(5, obj.getTelefone());
            comando.setString(6, obj.getEstadoCivil());
            comando.setString(7, obj.getGenero());
            comando.setString(8, obj.getEstado());
            comando.setString(9, obj.getCidade());
            comando.setString(10, obj.getCEP());
            comando.setString(11, obj.getRua());
            comando.setString(12, obj.getNumeroCasa());
            comando.setString(13, obj.getComplemento());

            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inserção feita!");
                retorno = true;
            }
            System.out.println(retorno);

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver");
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar com o banco");
            JOptionPane.showMessageDialog(null, "!!!ERROR!!! \nCPF/EMAIL/TEL Já cadastrado! ");
            ex.printStackTrace();
        }

        return retorno;
    }

    public static ArrayList<Clientes> listar(String nome) {

        ArrayList<Clientes> lista = new ArrayList<>();
        Connection conexao = null;
        String url = Conexao.url;
        String user = Conexao.user;
        String password = Conexao.password;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(url, user, password);

            PreparedStatement comandoSQL = conexao.prepareStatement("SELECT * FROM clientesSENAC WHERE nome_cliente LIKE '%" + nome + "%' ");
            //comandoSQL.setString(1, processador);

            ResultSet rs = comandoSQL.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Clientes novoObj = new Clientes();
                    novoObj.setNome(rs.getString("nome_cliente"));
                    novoObj.setEmail(rs.getString("email_cliente"));
                    novoObj.setCPF(rs.getString("CPF_cliente"));
                    novoObj.setTelefone(rs.getString("tel_cliente"));
                    novoObj.setDataNascimento(rs.getString("dtNascimento_cliente"));
                    novoObj.setEstadoCivil(rs.getString("estadoCivil_cliente"));
                    novoObj.setGenero(rs.getString("genero_cliente"));

                    lista.add(novoObj);

                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public static boolean excluir(String CPF) {
        boolean retorno = false;
        Connection conexao = null;
        String url = Conexao.url;
        String user = Conexao.user;
        String password = Conexao.password;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(url, user, password);

            PreparedStatement comando = conexao.
                    prepareStatement("DELETE FROM clientesSenac WHERE CPF_cliente = ?");

            comando.setString(1, CPF);

            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver");
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar com o banco");
        }

        return retorno;
    }

    public static boolean alterar(String nome, String email, String cpf, String telefone, String dtNasc, String estCivil, String genero) {
        boolean retorno = false;
        Connection conexao = null;
        String url = Conexao.url;
        String user = Conexao.user;
        String password = Conexao.password;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(url, user, password);

            PreparedStatement comando = conexao.
                    prepareStatement("UPDATE clientesSENAC SET nome_cliente = ?, email_cliente = ?, CPF_cliente = ?, tel_cliente = ?, dtNascimento_cliente = ?, estadoCivil_cliente = ?, genero_cliente = ?  WHERE CPF_cliente = ?");

            comando.setString(1, nome);
            comando.setString(2, email);
            comando.setString(3, cpf);
            comando.setString(4, telefone);
            comando.setString(5, dtNasc);
            comando.setString(6, estCivil);
            comando.setString(7, genero);
            comando.setString(8, cpf);

            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {
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
