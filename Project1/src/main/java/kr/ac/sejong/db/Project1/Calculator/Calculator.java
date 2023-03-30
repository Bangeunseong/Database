package kr.ac.sejong.db.Project1.Calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Calculator extends JFrame {
	Container c; 
	JPanel operand_Panel;
	JPanel formula_Panel; JTextArea jArea;
	private List<JButton> jbutton_operands = new ArrayList<>();
	JButton[] jbutton_Operators = new JButton[10];
	
	void setButton() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		operand_Panel = new JPanel();
		operand_Panel.setLayout(new GridLayout(3,3));
		
		formula_Panel = new JPanel();
		formula_Panel.setBackground(Color.LIGHT_GRAY);
		formula_Panel.setLayout(new FlowLayout());
		
		jArea = new JTextArea(5,30);
		formula_Panel.add(jArea);
		
		for(int i = 1; i < 10; i++) {
			jbutton_operands.add(new JButton(String.valueOf(i)));
		}
		jbutton_operands.stream().forEach(s->operand_Panel.add(s));
		c.add(formula_Panel, BorderLayout.NORTH);
		c.add(operand_Panel, BorderLayout.CENTER);
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
