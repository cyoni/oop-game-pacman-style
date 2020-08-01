package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import GameObjects.Fruit;
import GameObjects.Game_object;
import algorithms.Point2D;


/**
* 
* @author Yoni 
**/

public class MyFrame {
	

	/**
	 * empty constructor
	 * @throws Exception 
	**/
	
	public MyFrame() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Gui_algo gui_algo = new Gui_algo(); 
//		gui_algo.startBackgroundAnimation();
	}



	public static void main(String[] args) throws Exception {
		new MyFrame();
	}
}
