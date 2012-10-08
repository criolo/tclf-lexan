/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unibh.computacao.tclf.afd;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo Reis
 */
public class Alfabeto extends ArrayList<Character> {
    
    public Alfabeto(String simbolos){
        for (int i = 0; i < simbolos.length(); ++i) {
            if (!this.contains(simbolos.charAt(i))){
                this.add(simbolos.charAt(i));
            }
        }
    }
    
}
