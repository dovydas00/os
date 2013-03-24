package osProjektas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class VMController {
    VM vm;
    VMView window;
    
    public VMController ( final VMView window, VM machine ) {
        this.window = window;
        this.vm = machine;
        
        window.exitButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                WindowEvent wev = new WindowEvent( window, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
            }
        });
    }
}