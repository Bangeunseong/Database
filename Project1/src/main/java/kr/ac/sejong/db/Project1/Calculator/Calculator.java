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

public class Calculator extends JFrame implements ActionListener {
	Container c; 
	JPanel formula_Panel; JTextArea jArea;
	JPanel main_Panel; JPanel operand_Panel; JPanel function_Panel; JPanel operator_Panel;
	
	private List<JButton> jbutton_Operands = new ArrayList<>();
	private List<JButton> jbutton_Operators = new ArrayList<>();
	private List<JButton> jbutton_functions = new ArrayList<>();
	private List<String> operators = new ArrayList<>();
	private List<String> functions = new ArrayList<>();
	
	void setButton() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		//Formula_panel textarea
		formula_Panel = new JPanel();
		formula_Panel.setBackground(Color.LIGHT_GRAY);
		formula_Panel.setLayout(new BorderLayout());
		
		jArea = new JTextArea(5,40);
		jArea.setEditable(false);
		formula_Panel.add(jArea, BorderLayout.CENTER);
		
		//Operand, Operator, Function panel merged to main panel
		main_Panel = new JPanel();
		main_Panel.setBorder(new TitledBorder(new LineBorder(Color.black), "Click Buttons to create formula!"));
		main_Panel.setLayout(new BorderLayout(3,3));
		
		//Operand_panel buttons
		operand_Panel = new JPanel();
		operand_Panel.setBackground(Color.LIGHT_GRAY);
		operand_Panel.setLayout(new GridLayout(4,3,3,3));
		
		for(int i = 1; i <= 10; i++) {jbutton_Operands.add(new JButton(String.valueOf(i%10)));}
		jbutton_Operands.add(new JButton("00")); jbutton_Operands.add(new JButton("."));
		jbutton_Operands.stream().forEach(s->{
			s.addActionListener(this);
			s.setFont(new Font("Dialog", 0, 15));
			operand_Panel.add(s);
		});
		
		//Function_panel buttons
		function_Panel = new JPanel();
		function_Panel.setBackground(Color.LIGHT_GRAY);
		function_Panel.setLayout(new GridLayout(1,6,3,3));
		
		functions.addAll(Arrays.asList("C", "CE", "M+", "M-", "MR", "MC"));
		functions.stream().forEach(data->{
			jbutton_functions.add(new JButton(data));
		});
		jbutton_functions.stream().forEach(s->{
			s.addActionListener(this);
			s.setFont(new Font("Dialog", 0, 15));
			function_Panel.add(s);
		});
		
		//Operator_panel buttons
		operator_Panel = new JPanel();
		operator_Panel.setBackground(Color.LIGHT_GRAY);
		operator_Panel.setLayout(new GridLayout(4,2,3,3));
		
		operators.addAll(Arrays.asList("(", ")","+", "-", "*", "/", "%"));
		operators.stream().forEach(data->{
			jbutton_Operators.add(new JButton(data));
		});
		jbutton_Operators.stream().forEach(s->{
			s.addActionListener(this);
			s.setFont(new Font("Dialog", 0, 15));
			operator_Panel.add(s);
		});
		JButton resultButton = new JButton("=");
		resultButton.setFont(new Font("Dialog", 0, 15));
		resultButton.addActionListener(this);
		operator_Panel.add(resultButton);
		
		//Merge all panels to main panel
		main_Panel.add(operand_Panel,BorderLayout.CENTER);
		main_Panel.add(operator_Panel,BorderLayout.EAST);
		main_Panel.add(function_Panel,BorderLayout.NORTH);
		
		c.add(formula_Panel, BorderLayout.NORTH);
		c.add(main_Panel, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton op_Button = (JButton)e.getSource();
		if(jbutton_Operands.stream().anyMatch(data -> data.getText().equals(op_Button.getText()))) {
			BasicArithmatic.mainExp.append(op_Button.getText());
		}
		else if(operators.stream().anyMatch(data -> data.equals(op_Button.getText()))) {
			BasicArithmatic.mainExp.append(op_Button.getText());
		}
		else if(op_Button.getText().equals("C")) {
			if(BasicArithmatic.mainExp.length() > 0)
				BasicArithmatic.mainExp.delete(0, BasicArithmatic.mainExp.length());
		}
		else if(op_Button.getText().equals("CE")) {
			if(BasicArithmatic.mainExp.length() > 0)
				BasicArithmatic.mainExp.deleteCharAt(BasicArithmatic.mainExp.length() - 1);
		}
		else if(op_Button.getText().equals("M+")) {
			BasicArithmatic.mainMemory += BasicArithmatic.Calculate(BasicArithmatic.convert());
			BasicArithmatic.mainExp.delete(0, BasicArithmatic.mainExp.length());
		}
		else if(op_Button.getText().equals("M-")) {
			BasicArithmatic.mainMemory -= BasicArithmatic.Calculate(BasicArithmatic.convert());
			BasicArithmatic.mainExp.delete(0, BasicArithmatic.mainExp.length());
		}
		else if(op_Button.getText().equals("MR")) {
			if(BasicArithmatic.mainMemory >= 0) BasicArithmatic.mainExp.append("+" + String.valueOf(BasicArithmatic.mainMemory));
			else BasicArithmatic.mainExp.append(String.valueOf(BasicArithmatic.mainMemory));
		}
		else if(op_Button.getText().equals("MC")) {
			BasicArithmatic.mainMemory = 0;
		}
		else {
			BasicArithmatic.mainMemory = BasicArithmatic.Calculate(BasicArithmatic.convert());
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
