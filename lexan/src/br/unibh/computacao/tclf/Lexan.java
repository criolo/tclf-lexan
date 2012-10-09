package br.unibh.computacao.tclf;

import br.unibh.computacao.tclf.afd.Afd;


/**
 *
 * @author Rodrigo Reis
 */
public class Lexan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Afd automato = new Afd("C:\\Users\\FATORM_05\\Documents\\FatorM\\token.txt");
        automato.analisar();
    }
}
