package edu.Century.pa3;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Driver extends JComponent implements Accessible {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(500, 500));
		JFileChooser selector = new JFileChooser();
		frame.add(selector);
		int value = selector.showOpenDialog(null);
		if (value == selector.APPROVE_OPTION) {
			File fileRead = selector.getSelectedFile();
			SimpleParser test = new SimpleParser();
			test.checkFile(fileRead);
			System.out.println(test.toString());
		}
	}
}
