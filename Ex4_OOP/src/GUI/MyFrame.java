package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
* this class represents the GUI
* @author Yoni 
**/

public class MyFrame {
	

	/**
	 * empty constructor
	**/
	
	public MyFrame() {
		Gui_algo gui_algo = new Gui_algo(); 
	    gui_algo.test();
	}



	public static void main(String[] args) {
		new MyFrame();
	}
}
