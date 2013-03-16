package osProjektas;

import java.awt.Component;
import java.awt.Container;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LoadCustomFile extends JFileChooser {
    
	LoadCustomFile() {
        super();
    }
    
    public void customizeAppearance() {
        UIManager.put( "FileChooser.openDialogTitleText" , "Pasirinkti" );
        UIManager.put( "FileChooser.lookInLabelText", "Iškoti" );
        UIManager.put( "FileChooser.openButtonText", "Atverti" );
        UIManager.put( "FileChooser.directoryOpenButtonText", "Atverti" );
        UIManager.put( "FileChooser.directoryOpenButtonToolTipText", "Atverti" );
        UIManager.put( "FileChooser.cancelButtonText", "Atsisakyti" );
        UIManager.put( "FileChooser.fileNameLabelText", "Failo pavadinimas" );
        UIManager.put( "FileChooser.filesOfTypeLabelText", "Failo tipas" );
        UIManager.put( "FileChooser.openButtonToolTipText", "Atverti pasirinktą" );
        UIManager.put( "FileChooser.cancelButtonToolTipText", "Atsisakyti" );
        UIManager.put( "FileChooser.fileNameHeaderText", "Failo pavadinimas" );
        UIManager.put( "FileChooser.upFolderToolTipText", "Pakilti aukštyn lygiu" );
        UIManager.put( "FileChooser.filterLabelText", "TypeFiles" );
        SwingUtilities.updateComponentTreeUI( this );
        removeButton( this, "FileChooser.detailsViewIcon" );
        removeButton( this, "FileChooser.listViewIcon" );
        removeButton( this, "FileChooser.newFolderIcon" );
        changeButtonToolTipText( this, "FileChooser.homeFolderIcon", "Grįžti į pradinį aplankalą" );
        
        setAcceptAllFileFilterUsed( false );
        setFileFilter( new FileChooserFilter( "", "Visi failai" ) );
        setFileFilter( new FileChooserFilter( "txt", "*.txt" ) );
    }
    
    public void removeButton( Container c, String iconName ) {
        int len = c.getComponentCount();
        int i = 0;
        while ( i < len ) {
            Component comp = c.getComponent( i );
            if (comp instanceof JButton) {
                JButton b = ( JButton )comp;
                Icon icon = b.getIcon();
                if ( icon != null && icon == UIManager.getIcon( iconName ) ) {
                    c.remove( b );
                    len--;
                }
            } else if ( comp instanceof JToggleButton ) {
                JToggleButton b = ( JToggleButton )comp;
                Icon icon = b.getIcon();
                if ( icon != null && icon == UIManager.getIcon( iconName ) ) {
                    c.remove( b );
                    len--;
                }
            } else if (comp instanceof Container) {
                removeButton( ( Container ) comp, iconName );
            }
        i++;
        }
    }
    
    public void changeButtonToolTipText( Container c, String iconName, String toolTipText ) {
        int len = c.getComponentCount();
        int i = 0;
        while ( i < len ) {
            Component comp = c.getComponent( i );
            if (comp instanceof JButton) {
                JButton b = ( JButton )comp;
                Icon icon = b.getIcon();
                if ( icon != null && icon == UIManager.getIcon( iconName ) ) {
                    b.setToolTipText( toolTipText );
                }
            } else if ( comp instanceof JToggleButton ) {
                JToggleButton b = ( JToggleButton )comp;
                Icon icon = b.getIcon();
                if ( icon != null && icon == UIManager.getIcon( iconName ) ) {
                    b.setToolTipText( toolTipText );
                }
            } else if (comp instanceof Container) {
                changeButtonToolTipText( ( Container ) comp, iconName, toolTipText );
            }
        i++;
        }
    }
}