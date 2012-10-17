package br.unibh.computacao.tclf.afd;

import br.unibh.computacao.tclf.Arquivo;
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
        this.simbolo = new Alfabeto("'!@#$%¨&() ");
        this.operador = new Alfabeto("+-*/=");
    }

    /**
     *
     * @param caractere
     * @return
     */
    public boolean letra(char caractere) {
        return letra.contains(caractere);
    }

    /**
     *
     * @param caractere
     * @return
     */
    public boolean digito(char caractere) {
        return digito.contains(caractere);
    }

    /**
     *
     * @param caractere
     * @return
     */
    public boolean operador(char caractere) {
        return operador.contains(caractere);
    }

    /**
     *
     * @param caractere
     * @return
     */
    public boolean simbolo(char caractere) {
        return simbolo.contains(caractere);
    }

    /**
     *
     * @param caractere
     * @return
     */
    public boolean letraDigito(char caractere) {
        return letra(caractere) || digito(caractere);
    }

    /**
     *
     * @param caractere
     * @return
     */
    public boolean letraDigitoSimbolo(char caractere) {
        return letra(caractere) || digito(caractere) || simbolo(caractere);
    }

    /**
     *
     * @param caractere
     * @return
     */
    public boolean aspas(char caractere) {
        return (caractere == '"');
    }

    /**
     *
     * @param caractere
     * @return
     */
    public boolean ponto(char caractere) {
        return (caractere == '.');
    }

    /**
     *
     * @param caractere
     * @return
     */
    public boolean outro(char caractere) {
        return (!letra(caractere)
                && !digito(caractere)
                && !operador(caractere)
                && !aspas(caractere));
    }

    /**
     * Inicia a analise léxica, e salva o resultado em um arquivo texto.
     */
    public void analisar() {

        String conteudo = "";
        String token = "";

        for (int i = 0; i < this.texto.length(); ++i) {

            char caractere = this.texto.charAt(i);

            switch (this.estado) {
                case Inicial:

                    if (outro(caractere)) {
                        token = token + caractere;
                        this.estado = Estado.Inicial;

                    } else if (letra(caractere)) {
                        if (token.length() > 0) {
                            conteudo = conteudo + "UNKNOW[" + token + "] ";
                            token = "";
                        }
                        token = token + caractere;
                        this.estado = Estado.ID;

                    } else if (digito(caractere)) {
                        if (token.length() > 0) {
                            conteudo = conteudo + "UNKNOW[" + token + "] ";
                            token = "";
                        }
                        token = token + caractere;
                        this.estado = Estado.nint;

                    } else if (operador(caractere)) {
                        if (token.length() > 0) {
                            conteudo = conteudo + "UNKNOW[" + token + "] ";
                            token = "";
                        }
                        token = token + caractere;
                        this.estado = Estado.op;

                    } else if (aspas(caractere)) {
                        if (token.length() > 0) {
                            conteudo = conteudo + "UNKNOW[" + token + "] ";
                            token = "";
                        }
                        token = token + caractere;
                        this.estado = Estado.nstring;
                    }
                    break;

                // ID
                case ID:
                    if (letraDigito(caractere)) {
                        token = token + caractere;
                        this.estado = Estado.ID;
                    } else {
                        if (!outro(caractere)) {
                            conteudo = conteudo + "ID[" + token + "] ";
                            token = "";
                        }
                        --i;
                        this.estado = Estado.Inicial;
                    }
                    break;

                // nint
                case nint:
                    if (digito(caractere)) {
                        token = token + caractere;
                        this.estado = Estado.nint;
                    } else if (ponto(caractere)) {
                        token = token + caractere;
                        this.estado = Estado.nreal;
                    } else {
                        if (!outro(caractere)) {
                            conteudo = conteudo + "nint[" + token + "] ";
                            token = "";
                        }
                        --i;
                        this.estado = Estado.Inicial;
                    }
                    break;

                // nreal
                case nreal:
                    if (digito(caractere)) {
                        token = token + caractere;
                        this.estado = Estado.nreal;
                    } else {
                        if (!outro(caractere)) {
                            conteudo = conteudo + "nreal[" + token + "] ";
                            token = "";
                        }
                        --i;
                        this.estado = Estado.Inicial;
                    }
                    break;

                // op
                case op:
                    conteudo = conteudo + "op[" + token + "] ";
                    token = "";
                    --i;
                    this.estado = Estado.Inicial;
                    break;

                // nstring
                case nstring:
                    if (letraDigitoSimbolo(caractere)) {
                        token = token + caractere;
                        this.estado = Estado.nstring;
                    } else if (caractere == '"') {
                        token = token + caractere;
                        conteudo = conteudo + "nstring[" + token + "] ";
                        token = "";
                        //--i;
                        this.estado = Estado.Inicial;
                    }
                    break;
            }
        }
        
        if (token.length() > 0) {
            conteudo = conteudo + "UNKNOW[" + token + "] ";
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
