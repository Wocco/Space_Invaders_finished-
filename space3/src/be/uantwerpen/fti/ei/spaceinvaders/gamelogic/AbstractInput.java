package be.uantwerpen.fti.ei.spaceinvaders.gamelogic;

abstract public class AbstractInput {
    public enum Inputs{LEFT,RIGHT,SPACE,ESCAPE};
    public abstract boolean inputAvailable();
    public abstract AbstractInput.Inputs getInput();

}
