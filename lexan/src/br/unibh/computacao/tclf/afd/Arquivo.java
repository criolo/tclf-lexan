/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unibh.computacao.tclf.afd;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author FATORM_05
 */
public class Arquivo {
        
    static final int TAB = 9;
    static final int NOVA_LINHA = 10;
    static final int QUEBRA_LINHA = 13;
    static final int ESPACO_BRANCO = 32;

    
    /**
     * Obtém o texto contído no arquivo desconsiderando múltiplos espaços em
     * branco e quebras de linha.
     *
     * @param nomeArquivo
     * @return
     */
    public static String obterTextoMelhorado(String nomeArquivo) {
        try {
            FileReader in = new FileReader(nomeArquivo);
            BufferedReader reader = new BufferedReader(in);
            StringBuilder buffer = new StringBuilder();

            int caractere;
            while ((caractere = reader.read()) != -1) {
                if (caractere != TAB && caractere != NOVA_LINHA && caractere != QUEBRA_LINHA) {
                    char ch = (char) caractere;
                    if (buffer.length() > 0) {
                        int ultimoCaractere = (int) buffer.charAt(buffer.length() - 1);
                        if (ultimoCaractere == ESPACO_BRANCO && caractere == ESPACO_BRANCO) {
                            buffer.deleteCharAt(buffer.length() - 1);
                        }
                    }
                    buffer.append(ch);
                }
            }
            return buffer.toString();

        } catch (Exception ex) {
            System.out.printf("Error: %s", ex.getMessage());
            return null;
        }
    }
}
