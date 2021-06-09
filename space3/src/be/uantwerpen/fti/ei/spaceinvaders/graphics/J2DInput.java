package be.uantwerpen.fti.ei.spaceinvaders.graphics;

import be.uantwerpen.fti.ei.spaceinvaders.gamelogic.AbstractInput;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class J2DInput extends AbstractInput {
    private LinkedList<AbstractInput.Inputs> keyInputs;

    /**
     * The constructor of J2D input
     * @param frame as an input it needs a Jframe where it should listen to
     */
    public J2DInput(JFrame frame) {
        frame.addKeyListener(new KeyInputAdapter());
        keyInputs=new LinkedList<Inputs>();
    }

    /**
     * If their is input available this method will return the true. This method implements InputAvailable from the AbstractInput.
     * @return true or false
     */
    @Override
    public boolean inputAvailable() {
        return keyInputs.size()>0;
    }

    /**
     * This method will poll the inputs. Implements a method from AbstractInput.
     * @return LinkedList from Abstract Inputs
     */
    @Override
    public Inputs getInput() {
        return keyInputs.poll();
    }

    /**
     * The KeyInputAdapter extends KeyAdapter and implements the Keypressed method from Keyadapter
     */
    class KeyInputAdapter extends KeyAdapter{
        /**
         * implements the Keypressed method from Keyadapter.
         * @param e Keyevent e if a key is pressed this checks which one
         */
        @Override
        public void keyPressed(KeyEvent e){
            int keycode =e.getKeyCode();
            switch (keycode){
                case KeyEvent.VK_LEFT:
                    keyInputs.add(AbstractInput.Inputs.L);
                    break;
                case KeyEvent.VK_RIGHT:
                    keyInputs.add(AbstractInput.Inputs.R);
                    break;
                case KeyEvent.VK_SPACE:
                    keyInputs.add(AbstractInput.Inputs.S);
                    break;
                case KeyEvent.VK_ESCAPE:
                    keyInputs.add(AbstractInput.Inputs.ESC);
                    break;

            }
        }

    }

}
