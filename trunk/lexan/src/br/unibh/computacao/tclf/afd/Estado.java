package br.unibh.computacao.tclf.afd;

/**
 * Enumera os possíveis tipos de token.
 *
 * @author RodrigoReis
 */
public enum Estado {

    Inicial,
    ID,
    nint,
    nreal,  
    op,
    nstring
}
