package br.unibh.computacao.tclf.afd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Manipulador de aqruivo.
 *
 * @author Rodrigo Reis (RA: 406204771)
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
    public static String texto(String nomeArquivo) {
        if (nomeArquivo != null || !nomeArquivo.equals("")) {
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
        }else{
            System.out.println("Arquivo invalido.");
            return null;
        }
    }

    /**
     * Salva o conteúdo no arquivo.
     *
     * @param arquivo
     * @param conteudo
     */
    public static void salvar(Path arquivo, String conteudo) {
        try {
            Files.deleteIfExists(arquivo);
            Files.createFile(arquivo);
            Files.write(arquivo, conteudo.getBytes());

            System.out.println();
            System.out.printf("O Arquivo %s foi salvo com sucesso.", arquivo.toString());
            System.out.println();
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }
    }
}
