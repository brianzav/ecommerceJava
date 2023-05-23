/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mercadosenacTELAS;

import com.mycompany.mercadosenacDefaults.Vendas;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author luan.vviana
 */
public class MercadoSenac {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        LocalDate data = LocalDate.now();
        String date = data.toString();
        txtDataAtual.setText(date);
    }
}
