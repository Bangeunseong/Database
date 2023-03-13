package kr.ac.sejong.db.Project1.oop2;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JavaSwingProgram extends JFrame {
	
	public JavaSwingProgram() {
		
		Container c = getContentPane();
		
		JButton b = new JButton("Click me");
		b.addActionListener((event)->{
			System.out.println("Hi");
			System.out.println(event);
		});
		c.add(b);
		
		setTitle("Hello Java Swing");
		setSize(300,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JavaSwingProgram();
	}

}
