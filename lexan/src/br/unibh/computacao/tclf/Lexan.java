package br.unibh.computacao.tclf;

import br.unibh.computacao.tclf.afd.Afd;

/**
 * Analisador Léxico.
 *
 * @author Rodrigo Reis (RA: 406204771)
 */
public class Lexan {

    /**
     * Lexan.
     *
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        
        if (args.length > 0){
            Afd automato = new Afd(args[0]);
            automato.analisar();
        }else {
            System.out.println("A execucao do programa deve ser: "
                    + "lexan <fonte>. Onde <fonte> e o caminho do "
                    + "arquivo a ser analisado.");
        }
    }
}
