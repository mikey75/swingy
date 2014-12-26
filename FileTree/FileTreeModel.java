package net.wirelabs.components;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import javax.swing.tree.TreeModel;


	public class FileTreeModel implements TreeModel {
		
		FileFilter dirs = new FileFilter() {  
		    public boolean accept(File pathname) {  
		        return pathname.isDirectory();  
		    } 
		};
		FileFilter all = new FileFilter() {  
		    public boolean accept(File pathname) {  
		        return pathname.isFile() || pathname.isDirectory();  
		    } 
		};
//		
	    public File root;
	    private Boolean selectionMode;
	    
	    public FileTreeModel(File root) {
	        this(root,false);
	    }
	    public FileTreeModel(File root, Boolean selectionMode) {
	    	this.root = root;
	    	this.selectionMode = selectionMode;
	    }
		@Override
	    public void addTreeModelListener(javax.swing.event.TreeModelListener l) {
	        //do nothing
	    }

	    @Override
	    // pobierz n-te dziecko obiektu  
	    public Object getChild(Object parent, int index) {
	    	
	        File f = (File) parent;
	        return f.listFiles(selectionMode ? dirs: all)[index];
	    }

	    @Override
	    // pobierz ilosc obiektow-dzieci obiektu rodzica
	    // nie ma sensu jesli rodzic nie jest katalogiem/wezlem
	    public int getChildCount(Object parent) {
	        File f = (File) parent;
	        if (!f.isDirectory()  ) {
	            return 0;
	        } else {
	            return f.listFiles(selectionMode ? dirs: all).length;	
	        }
	    }
	    
	    @Override
	    public int getIndexOfChild(Object parent, Object child) {
	        File par = (File) parent;
	        File ch = (File) child;
	        return Arrays.asList(par.listFiles(selectionMode ? dirs: all)).indexOf(ch);
	    }

	    @Override
	    public Object getRoot() {
	        return root;
	    }
	    public void setRoot(Object root) {
	    	this.root = (File) root;
	    }
	    @Override
	    // obiekt jest lisciem drzewa (czyli nie mozna go rozwinac dalej) jesli: 
	    // nie jest katalogiem wogole
	    // jest katalogiem ale pustym 
	    // nie mozna go przeczytac (permissions)
	    public boolean isLeaf(Object node) {
	        File f = (File) node;
	        return !f.isDirectory() || !f.canRead() || f.listFiles().length == 0 ;
	    }

	    @Override
	    public void removeTreeModelListener(javax.swing.event.TreeModelListener l) {
	        //do nothing
	    }

	    @Override
	    public void valueForPathChanged(javax.swing.tree.TreePath path, Object newValue) {
	        //do nothing
	    }

	}