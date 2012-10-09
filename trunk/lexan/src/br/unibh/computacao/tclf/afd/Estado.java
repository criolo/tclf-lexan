package br.unibh.computacao.tclf.afd;

/**
 * Enumera os possíveis tipos de token.
 *
 * @author Rodrigo Reis (RA: 406204771)
 */
public enum Estado {

    Inicial,
    ID,
    nint,
    nreal,  
    op,
    nstring
}
