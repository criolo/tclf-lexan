/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unibh.computacao.tclf;

import br.unibh.computacao.tclf.afd.Alfabeto;
import br.unibh.computacao.tclf.afd.Arquivo;

/**
 *
 * @author FATORM_05
 */
public class Lexan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        
        Alfabeto letra = new Alfabeto("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        Alfabeto digito = new Alfabeto("0123456789");
        Alfabeto simbolo = new Alfabeto("'!@#$%¨&()");
        Alfabeto operador = new Alfabeto("+-*/=");        
        
        String texto = Arquivo.obterTextoMelhorado("C:\\token.txt");
        
        System.out.println(texto);
        
        int estado = 0;
        
        for (int i = 0; i < texto.length(); ++i) {
            
            char caractere = texto.charAt(i);
            
            switch (estado) {
                case 0:
                    if (caractere == ' ') {
                        estado = 0;
                    } else if (letra.contains(caractere)) {
                        estado = 1;
                    } else if (digito.contains(caractere)) {
                        estado = 3;
                    } else if (operador.contains(caractere)) {
                        estado = 5;
                    } else if (caractere == '"') {
                        estado = 6;
                    }
                    break;
                case 1:
                    if (letra.contains(caractere) || digito.contains(caractere)) {
                        estado = 1;
                    } else {
                        estado = 2;
                    }
                    break;
                case 2:
                    System.out.print("ID ");
                    //--i;
                    estado = 0;
                    break;
                case 3:
                    if (digito.contains(caractere)) {
                        estado = 3;
                    } else if (caractere == '.') {
                        estado = 8;
                    } else {
                        estado = 4;
                    }
                    break;
                case 4:
                    System.out.print("nint ");
                    //--i;
                    estado = 0;
                    break;
                case 5:
                    System.out.print("op ");
                    //--i;
                    estado = 0;
                    break;
                case 6:
                    if (letra.contains(caractere) || digito.contains(caractere) || simbolo.contains(caractere)) {
                        estado = 6;
                    } else if (caractere == '"') {
                        estado = 7;
                    }
                    break;
                case 7:
                    System.out.print("nstring ");
                    //--i;
                    estado = 0;
                    break;
                case 8:
                    if (digito.contains(caractere)) {
                        estado = 8;
                    }else{
                        estado = 9;
                    }
                    break;
                case 9:
                    System.out.print("nreal ");
                    //--i;
                    estado = 0;
                    break;
            }
        }
        System.out.println();
    }
}
