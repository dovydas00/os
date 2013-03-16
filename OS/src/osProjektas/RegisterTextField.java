package osProjektas;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterTextField extends JTextField {
    
    JLabel label;
    
    RegisterTextField ( int length, String title ) {
        super( length );
        setEditable( false ) ;
        setBackground( Color.WHITE );
        setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        label = new JLabel( title, JLabel.LEFT );
    }
}