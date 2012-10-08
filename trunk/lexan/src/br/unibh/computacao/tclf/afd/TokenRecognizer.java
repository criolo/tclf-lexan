/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unibh.computacao.tclf.afd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Rodrigo Reis
 */
public class TokenRecognizer extends ArrayList<Token> {

    StringTokenizer tokens;

    public TokenRecognizer(String filename) {
        try (FileReader in = new FileReader(filename);
                BufferedReader reader = new BufferedReader(in)) {
            String line, content;

            content = "";
            while ((line = reader.readLine()) != null) {
                content += line;
            }

            GenerateTokens(new StringTokenizer(content));
            
            this.tokens = new StringTokenizer(content);
        } catch (Exception ex) {
            System.out.printf("Error: %s", ex.getMessage());
        }
    }

    private void GenerateTokens(StringTokenizer tokenizer){
        while (this.tokens.hasMoreElements()) {
            if (this.tokens.nextToken().charAt(0) == '"') {
            }
        }
    }
    
    public void teste() {
        while (this.tokens.hasMoreElements()) {
            if (this.tokens.nextToken().charAt(0) == '"') {
            }
        }
    }
}
