/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unibh.computacao.tclf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author FATORM_05
 */
public class Lexan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
    }

    public static String MelhorarTexto(String nomeArquivo) {
        try (FileReader in = new FileReader("C:\\token.txt");
                BufferedReader reader = new BufferedReader(in)) {

            StringBuilder buffer = new StringBuilder();
            int caractere;

            while ((caractere = reader.read()) != -1) {
                if (caractere != 9 && caractere != 10 && caractere != 13) {
                    char ch = (char) caractere;
                    if (buffer.length() > 0) {
                        int ultimoCaractere = (int) buffer.charAt(buffer.length() - 1);
                        if (ultimoCaractere == 32 && caractere == 32) {
                            buffer.deleteCharAt(buffer.length() - 1);
                        }
                    }
                    buffer.append(ch);
                }
            }

            return buffer.toString();

        } catch (Exception ex) {
            //System.out.printf("Error: %s", ex.getMessage());
            return null;
        }
    }
}