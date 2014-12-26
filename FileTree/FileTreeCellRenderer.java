package net.wirelabs.components;

import java.awt.Component;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;


public class FileTreeCellRenderer extends DefaultTreeCellRenderer {
	

	private ImageIcon defaulticon= new ImageIcon(getClass().getResource("/icons/mimetypes/unknown.png"));
	private ImageIcon directoryicon= new ImageIcon(getClass().getResource("/icons/places/gtk-directory.png"));
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		
		if (value instanceof File) {
			
			setIcon(defaulticon); 

			if (((File) value).isDirectory()) {
				setIcon(directoryicon); 
			} 
			
			setText(((File) value).getName());
			
		}
		return this;
	}

}