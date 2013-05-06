package osProjektas;

import java.io.File;

public class FileChooserFilter extends javax.swing.filechooser.FileFilter {
    
    private String extension;
    private String description;
    
    FileChooserFilter( String extension, String description ) {
        this.extension = extension;
        this.description = description;
    }
    
    @Override
    public boolean accept( File f ) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith( extension );
    }
    
    @Override
    public String getDescription() {
        return description;
    }
}