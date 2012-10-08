/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unibh.computacao.tclf.afd;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Rodrigo Reis
 */
public class Alphabet extends ArrayList<String> {

    /**
     * Construtor, cria um alfabeto baseando-se nos caracteres de uma string.
     * @param symbols Símbolos do alfabeto.
     */
    public Alphabet(String symbols) {
        for (int i = 0; i < symbols.length(); i++) {
            String tmp = "" + symbols.charAt(i);
            if (! this.contains(tmp)) {
                this.add(tmp);
            }
        }
        this.order();
    }

    /**
     * Determina se um símbolo está contido no alfabeto.
     * @param symbols Caracter a ser pesquisado no alfabeto.
     * @return true caso encontre, falso caso contrário.
     */
    public final boolean contains(String symbols) {
        if ( this.contains(symbols) ) {
            return true;
        }
        return false;
    }

    /**
     * Faz a ordenação do alfabeto.
     */
    private void order() {
        String a[] = new String[1];
        a = this.toArray(a);
        java.util.Arrays.sort(a);

        this.removeAll(this);
        this.addAll(java.util.Arrays.asList(a));
    }
}
