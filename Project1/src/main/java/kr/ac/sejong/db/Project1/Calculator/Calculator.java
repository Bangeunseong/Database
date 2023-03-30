package kr.ac.sejong.db.Project1.Calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

public class Calculator extends JFrame implements ActionListener {
	Container c; 
	JPanel operand_Panel;
	JPanel formula_Panel; JTextArea jArea;
	private List<JButton> jbutton_Operands = new ArrayList<>();
	private List<JButton> jbutton_Operators = new ArrayList<>();
	private List<String> operators = new ArrayList<>();
	
	void setButton() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		formula_Panel = new JPanel();
		formula_Panel.setBackground(Color.LIGHT_GRAY);
		formula_Panel.setLayout(new BorderLayout());
		
		jArea = new JTextArea(5,40);
		jArea.setEditable(false);
		formula_Panel.add(jArea, BorderLayout.CENTER);
		
		operand_Panel = new JPanel();
		operand_Panel.setBorder(new TitledBorder(new LineBorder(Color.black), "Click Buttons to create formula!"));
		operand_Panel.setBackground(Color.LIGHT_GRAY);
		operand_Panel.setLayout(new GridLayout(5,4,3,3));
		
		JButton cancel_Insertion = new JButton("CE");
		cancel_Insertion.setFont(new Font("Dialog", 0, 15)); cancel_Insertion.addActionListener(this);
		operand_Panel.add(cancel_Insertion);
		
		for(int i = 1; i <= 10; i++) {jbutton_Operands.add(new JButton(String.valueOf(i%10)));}
		jbutton_Operands.stream().forEach(s->{
			s.addActionListener(this);
			s.setFont(new Font("Dialog", 0, 15));
			operand_Panel.add(s);
		});
		
		operators.addAll(Arrays.asList("+", "-", "*", "/", "%","(", ")", ".", "="));
		operators.stream().forEach(data->{
			jbutton_Operators.add(new JButton(data));
		});
		jbutton_Operators.stream().forEach(s->{
			s.addActionListener(this);
			s.setFont(new Font("Dialog", 0, 15));
			operand_Panel.add(s);
		});
		
		c.add(formula_Panel, BorderLayout.NORTH);
		c.add(operand_Panel, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton op_Button = (JButton)e.getSource();
		if(!op_Button.getText().equals("CE") && !op_Button.getText().equals("=")) {
			BasicArithmatic.mainExp.append(op_Button.getText());
		}
		else if(op_Button.getText().equals("CE")) {
			BasicArithmatic.mainExp.deleteCharAt(BasicArithmatic.mainExp.length() - 1);
		}
		else {
			BasicArithmatic.Calculate(BasicArithmatic.convert());
			BasicArithmatic.mainExp.delete(0, BasicArithmatic.mainExp.length());
			BasicArithmatic.mainExp.append(BasicArithmatic.mainMemory);
		}
		jArea.setText(BasicArithmatic.mainExp.toString());
	}
	
	public Calculator() {
		setTitle("Calculator");
		setSize(300,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setButton();
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculator();
	}	
}
