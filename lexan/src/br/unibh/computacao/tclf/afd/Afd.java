package br.unibh.computacao.tclf.afd;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * AFD que reconhece os lexemas dos seguintes tokens: ID, nint, nreal, op,
 * nstring.
 *
 * @author Rodrigo Reis (RA: 406204771)
 */
public class Afd {

    /**
     * Alfabeto de letras.
     */
    public Alfabeto letra;
    /**
     * Alfabeto de dígitos.
     */
    public Alfabeto digito;
    /**
     * Alfabeto de símbolos.
     */
    public Alfabeto simbolo;
    /**
     * Alfabeto de operadores.
     */
    public Alfabeto operador;
    /**
     * Enumeração dos possíveis estados do Autômato.
     */
    public Estado estado;
    /**
     * Texto a ser analisado pelo Autômato.
     */
    public String texto;
    /**
     * Caminho do arquivo a ser analisado.
     */
    Path caminho;

    /**
     * Construtor, cria o analisador léxico que percorre o texto e encontre os
     * tokens
     *
     * @param texto
     */
    public Afd(String arquivo) {
        this.caminho = Paths.get(arquivo);
        this.texto = Arquivo.texto(this.caminho.toString());
        this.estado = Estado.Inicial;
        this.letra = new Alfabeto("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        this.digito = new Alfabeto("0123456789");
        this.simbolo = new Alfabeto("'!@#$%¨&()");
        this.operador = new Alfabeto("+-*/=");
    }

    /**
     * Inicia a analise léxica, e salva o resultado em um arquivo texto.
     */
    public void analisar() {

        String conteudo = "";

        for (int i = 0; i < this.texto.length(); ++i) {

            char caractere = this.texto.charAt(i);

            switch (this.estado) {
                case Inicial:
                    if (caractere == ' ') {
                        this.estado = Estado.Inicial;
                    } else if (letra.contains(caractere)) {
                        this.estado = Estado.ID;
                    } else if (digito.contains(caractere)) {
                        this.estado = Estado.nint;
                    } else if (operador.contains(caractere)) {
                        this.estado = Estado.op;
                    } else if (caractere == '"') {
                        this.estado = Estado.nstring;
                    }
                    break;
                // ID
                case ID:
                    if (letra.contains(caractere) || digito.contains(caractere)) {
                        this.estado = Estado.ID;
                    } else {
                        --i;
                        //System.out.print("ID ");
                        conteudo = conteudo + "ID ";
                        this.estado = Estado.Inicial;
                    }
                    break;
                // nint
                case nint:
                    if (digito.contains(caractere)) {
                        this.estado = Estado.nint;
                    } else if (caractere == '.') {
                        this.estado = Estado.nreal;
                    } else {
                        --i;
                        //System.out.print("nint ");
                        conteudo = conteudo + "nint ";
                        this.estado = Estado.Inicial;
                    }
                    break;
                // nreal
                case nreal:
                    if (digito.contains(caractere)) {
                        this.estado = Estado.nreal;
                    } else {
                        --i;
                        //System.out.print("nreal ");
                        conteudo = conteudo + "nreal ";
                        this.estado = Estado.Inicial;
                    }
                    break;
                // op
                case op:
                    //System.out.print("op ");
                    conteudo = conteudo + "op ";
                    this.estado = Estado.Inicial;
                    break;
                // nstring
                case nstring:
                    if (letra.contains(caractere)
                            || digito.contains(caractere)
                            || simbolo.contains(caractere)
                            || operador.contains(caractere)) {
                        this.estado = Estado.nstring;
                    } else if (caractere == '"') {
                        //System.out.print("nstring ");
                        conteudo = conteudo + "nstring ";
                        this.estado = Estado.Inicial;
                    }
                    break;
            }
        }
        conteudo = conteudo.trim();
        System.out.println("OK..");
        System.out.printf("Resultado: \"%s\"", conteudo);

        if (this.caminho.getParent() == null) {
            try {
                String path = new java.io.File(".").getCanonicalPath();
                Arquivo.salvar(Paths.get(path + "\\analise_" 
                        + this.caminho.getFileName()), conteudo);
            } catch (IOException ex) {
            }            
        } else {
            Arquivo.salvar(Paths.get(this.caminho.getParent()
                    + "\\analise_" + this.caminho.getFileName()), conteudo);
        }
    }
}
