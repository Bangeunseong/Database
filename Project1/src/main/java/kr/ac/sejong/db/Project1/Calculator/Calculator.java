package kr.ac.sejong.db.Project1.Calculator;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calculator extends JFrame {
	Container c; JPanel pal;
	private List<JButton> jbutton_operands = new ArrayList<>();
	JButton[] jbutton_Operators = new JButton[10];
	
	void setButton() {
		c = getContentPane();
		pal = new JPanel();
		pal.setBackground(Color.LIGHT_GRAY);
		pal.setLayout(new GridLayout(3,3));
		
		for(int i = 1; i < 10; i++) {
			jbutton_operands.add(new JButton(String.valueOf(i)));
		}
		jbutton_operands.stream().forEach(s->pal.add(s));
		c.add(pal);
	}
	
	public Calculator() {
		setTitle("Calculator");
		setSize(400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setButton();
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculator();
	}

}