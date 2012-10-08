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
                        estado = 2;
                    } else if (operador.contains(caractere)) {
                        estado = 4;
                    } else if (caractere == '"') {
                        estado = 5;
                    }
                    break;
                // ID
                case 1:
                    if (letra.contains(caractere) || digito.contains(caractere)) {
                        estado = 1;
                    } else {
                        --i;
                        System.out.print("ID ");
                        estado = 0;
                    }
                    break;
                // nint
                case 2:
                    if (digito.contains(caractere)){
                        estado = 2;
                    }else if (caractere == '.'){
                        estado = 3;
                    }else {
                        --i;
                        System.out.print("nint ");
                        estado = 0;
                    }
                    break;
                // nreal
                case 3:
                    if (digito.contains(caractere)){
                        estado = 3;
                    }else{
                        --i;
                        System.out.print("nreal ");
                        estado = 0;
                    }
                    break;
                // op
                case 4:
                    System.out.print("op ");
                    estado = 0;
                    break;
                // nstring
                case 5:
                    if (letra.contains(caractere) 
                            || digito.contains(caractere) 
                            || simbolo.contains(caractere)
                            || operador.contains(caractere)) {
                        estado = 5;
                    }else if (caractere == '"'){
                        System.out.print("nstring ");
                        estado = 0;
                    }
                    break;                
            }
        }
        System.out.println();
    }
}
