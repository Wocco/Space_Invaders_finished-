package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractInput;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class J2DInput extends AbstractInput {
    private LinkedList<AbstractInput.Inputs> keyInputs;
    public J2DInput(JFrame frame) {
        frame.addKeyListener(new KeyInputAdapter());
        keyInputs=new LinkedList<Inputs>();
    }

    @Override
    public boolean inputAvailable() {
        return keyInputs.size()>0;
    }

    @Override
    public Inputs getInput() {
        return keyInputs.poll();
    }
    class KeyInputAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int keycode =e.getKeyCode();
            switch (keycode){
                case KeyEvent.VK_LEFT:
                    keyInputs.add(AbstractInput.Inputs.LEFT);
                case KeyEvent.VK_RIGHT:
                    keyInputs.add(AbstractInput.Inputs.RIGHT);
                case KeyEvent.VK_SPACE:
                    keyInputs.add(AbstractInput.Inputs.SPACE);
                case KeyEvent.VK_ESCAPE:
                    keyInputs.add(AbstractInput.Inputs.ESCAPE);

            }
        }

    }

}
