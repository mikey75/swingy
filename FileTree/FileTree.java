package net.wirelabs.components;

import java.io.File;

import javax.swing.JTree;
//import java.io.FileFilter;

/**
 * A file browsing JTree with lazy model created dynamically 
 * from a filesystem as you interact with the tree
 * <p>
 * @author Michał Szwaczko 
 * 
 * 
 */

public class FileTree extends JTree {
	
	
	private static final String DEFAULT_FILEPATH = "/";
	
	public static final Boolean SHOW_ALL = false;
	public static final Boolean SHOW_DIRS_ONLY = true;
	
	//public boolean dirsOnly = false;
	
	public File root;
	public Boolean selectionMode;
	public FileTreeModel model; 
	public FileTreeCellRenderer renderer;
	
	// default constructor 
	
	public FileTree(File root,Boolean mode) {
			this.root = root; //new File(fileroot);
			this.selectionMode = mode;
			this.model = new FileTreeModel(root,mode);
			this.renderer = new FileTreeCellRenderer();
	
			setModel(model);
			setCellRenderer(renderer);
		}
		
		
	public FileTree(File fileroot) {
	
		this(fileroot,SHOW_ALL);
	}
	
	public FileTree(String fileroot) {
	
		this(new File(fileroot));
	}
	
	public FileTree() {
		
		this(new File(DEFAULT_FILEPATH),SHOW_ALL);
	}
	
	public FileTree(Boolean selectionMode) {
	
		this(new File(DEFAULT_FILEPATH),selectionMode);
	}
	
	
	
	public void reload(String newrootpath) {
		model = new FileTreeModel(new File(newrootpath),selectionMode);
		setModel(model);
	}
	
	public void reload(){
		reload(root.getPath());
	}
	
	public String getExpansionState(){

		StringBuilder sb = new StringBuilder();

		for ( int i = 0; i < this.getRowCount(); i++ ){

			if ( this.isExpanded(i) ){
				
				sb.append(i).append(",");

			}

		}

		return sb.toString();

	}
	public void setExpansionState(String s){

		String[] indexes = s.split(",");
		
		for ( String st : indexes ){
			if (!st.isEmpty()) {
			int row = Integer.parseInt(st);

			this.expandRow(row);
			}

		}

	}

	
	
	
		
}
