package br.unibh.computacao.tclf.afd;

import java.util.ArrayList;

/**
 * Alfabeto.
 *
 * @author Rodrigo Reis
 */
public class Alfabeto extends ArrayList<Character> {

    /**
     * Construtor.
     *
     * @param simbolos String contento os símbolos que compões o alfabeto.
     */
    public Alfabeto(String simbolos) {
        for (int i = 0; i < simbolos.length(); ++i) {
            if (!this.contains(simbolos.charAt(i))) {
                this.add(simbolos.charAt(i));
            }
        }
    }
}
