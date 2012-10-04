/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unibh.computacao.tclf.afd;

/**
 *
 * @author Rodrigo Reis
 */
public class Token {

    private String value;
    private TokenType type;

    public Token(String value) {
        this.setValue(value);
        this.type = TokenType.Unrecognized;
    }

    public final String getValue() {
        return value;
    }

    public final void setValue(String value) {
        this.value = value;
    }

    public void setType(TokenType type){
        this.type = type;
    }
    
    public TokenType getType() {
        return this.type;
    }
}