/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unibh.computacao.tclf.afd;

/**
 *
 * @author Rodrigo Reis
 */
public class Transition {
    
    private State origin;
    private State destination;
    
    public Transition(State origin, State destination){
        this.origin = origin;
        this.destination = destination;
    }

    public State getOrigin() {
        return origin;
    }

    public void setOrigin(State origin) {
        this.origin = origin;
    }

    public State getDestination() {
        return destination;
    }

    public void setDestination(State destination) {
        this.destination = destination;
    }            
}
