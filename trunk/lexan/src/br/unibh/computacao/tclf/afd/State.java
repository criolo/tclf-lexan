/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unibh.computacao.tclf.afd;

/**
 *
 * @author Rodrigo Reis
 */
public class State {
    
    private boolean startState;
    private boolean acceptState;
    
    public State(){
        
    }

    public boolean isStartState() {
        return startState;
    }

    public void setStartState(boolean startState) {
        this.startState = startState;
    }

    public boolean isAcceptState() {
        return acceptState;
    }

    public void setAcceptState(boolean acceptState) {
        this.acceptState = acceptState;
    }    
}
