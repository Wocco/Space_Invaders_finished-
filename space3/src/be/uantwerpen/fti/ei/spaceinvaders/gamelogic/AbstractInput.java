package be.uantwerpen.fti.ei.spaceinvaders.gamelogic;

abstract public class AbstractInput {
    public enum Inputs{L,R,S,ESC};
    public abstract boolean inputAvailable();
    public abstract AbstractInput.Inputs getInput();

}
