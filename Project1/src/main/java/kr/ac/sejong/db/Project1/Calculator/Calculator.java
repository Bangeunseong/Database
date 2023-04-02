package kr.ac.sejong.db.Project1.Calculator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Calculator extends JFrame implements ActionListener {
	//-------------------------------------------------------
	//Field for main_container
	Container c; CardLayout card_Layout;
	
	//Field for menubar
	JMenuBar jMenuBar; JMenu jMenu; JMenuItem basicItem, matrixItem;
	
	//Field for arithmatic calculator
	JPanel main_Panel_A;
	JPanel formula_Panel; JTextArea jArea_A;
	JPanel arithmatic_Panel; JPanel operand_Panel; JPanel function_Panel; JPanel operator_Panel;
	
	private List<JButton> jbutton_Operands = new ArrayList<>();
	private List<JButton> jbutton_Operators = new ArrayList<>();
	private List<JButton> jbutton_functions = new ArrayList<>();
	private List<String> operators = new ArrayList<>();
	private List<String> functions = new ArrayList<>();
	
	//Field for matrix calculator
	JPanel matrix_Panel;
	JTextArea jArea_M;
	JPanel main_Panel_M; GridLayout main_PanelLayout;
	JPanel matrix_A, matrix_B, function_Panel_M; GridLayout matrixlayout_A, matrixlayout_B;
	
	private List<JTextField> jTextFields_A = new ArrayList<>();
	private List<JTextField> jTextFields_B = new ArrayList<>();
	private List<JButton> jbutton_fucntions_M = new ArrayList<>();
	private List<String> functions_M = new ArrayList<>();
	
	//--------------------------------------------------------
	//MenuBar Setting
	void setMenuBar() {
		jMenuBar = new JMenuBar();
		jMenu = new JMenu("Select");
		
		basicItem = new JMenuItem("Basic"); matrixItem = new JMenuItem("Matrix");
		basicItem.addActionListener(new SelectActionListener());
		matrixItem.addActionListener(new SelectActionListener());
		
		jMenu.add(basicItem); jMenu.add(matrixItem);
		jMenuBar.add(jMenu);
		setJMenuBar(jMenuBar);
	}
	
	//MenuBar eventlistener
	class SelectActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JMenuItem jMenuItem = (JMenuItem)e.getSource();
			if(jMenuItem.getText().equals("Basic")) {
				setSize(370,500);
				card_Layout.show(c, "Basic");
			}
			else if(jMenuItem.getText().equals("Matrix")) {
				setSize(370,350);
				card_Layout.show(c, "Matrix");
			}
			else {
				
			}
		}
	}
	//----------------------------------------------------------
	
	//----------------------------------------------------------
	//Basic calculator button setting
	void setArithmaticCalculator() {
		//Arithmatic calculation panel
		arithmatic_Panel = new JPanel();
		arithmatic_Panel.setLayout(new BorderLayout());
		
		//Formula_panel textarea
		formula_Panel = new JPanel();
		formula_Panel.setBackground(Color.LIGHT_GRAY);
		formula_Panel.setLayout(new BorderLayout());
		
		jArea_A = new JTextArea(8,300);
		jArea_A.setEditable(false);
		formula_Panel.add(jArea_A, BorderLayout.CENTER);
		
		//Operand, Operator, Function panel merged to main panel
		main_Panel_A = new JPanel();
		main_Panel_A.setBorder(new TitledBorder(new LineBorder(Color.black), "Click Buttons to create formula!"));
		main_Panel_A.setLayout(new BorderLayout(3,3));
		
		//Operand_panel buttons
		operand_Panel = new JPanel();
		operand_Panel.setBackground(Color.LIGHT_GRAY);
		operand_Panel.setLayout(new GridLayout(4,3,3,3));
		
		for(int i = 1; i <= 10; i++) {jbutton_Operands.add(new JButton(String.valueOf(i%10)));}
		jbutton_Operands.add(new JButton("00")); jbutton_Operands.add(new JButton("."));
		jbutton_Operands.stream().forEach(s->{
			s.addActionListener(this);
			s.setFont(new Font("Dialog", 0, 14));
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
			s.setFont(new Font("Dialog", 0, 12));
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
			s.setFont(new Font("Dialog", 0, 14));
			operator_Panel.add(s);
		});
		JButton resultButton = new JButton("=");
		resultButton.setFont(new Font("Dialog", 0, 14));
		resultButton.addActionListener(this);
		operator_Panel.add(resultButton);
		
		//Merge all panels to main panel
		main_Panel_A.add(operand_Panel,BorderLayout.CENTER);
		main_Panel_A.add(operator_Panel,BorderLayout.EAST);
		main_Panel_A.add(function_Panel,BorderLayout.NORTH);
		
		arithmatic_Panel.add(formula_Panel, BorderLayout.NORTH);
		arithmatic_Panel.add(main_Panel_A, BorderLayout.CENTER);
	}
	
	//Basic calculator event listener
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
		jArea_A.setText(BasicArithmatic.mainExp.toString());
	}
	//-----------------------------------------------------------
	
	//-----------------------------------------------------------
	//Matrix Calculator button setting
	void setMatrixCalculator() {
		//--------------------------------------------------------
		//Setting MatrixA
		matrix_A = new JPanel();
		matrix_A.setBorder(new TitledBorder(new LineBorder(Color.black),"Matrix_A"));
		
		matrixlayout_A = new GridLayout(Matrix.rowA, Matrix.colA, 5, 5);
		matrix_A.setLayout(matrixlayout_A);
		
		for(int i = 0; i < Matrix.rowA * Matrix.colA; i++) {
			jTextFields_A.add(new JTextField(50));
			jTextFields_A.get(i).setFont(new Font("Dialog", 0, 12));
			if(Matrix.mainMatrix.containsKey(i)) {
				jTextFields_A.get(i).setText(String.valueOf(Matrix.mainMatrix.get(i)));
			}
		}
		jTextFields_A.stream().forEach(data->matrix_A.add(data));
		
		//Setting MatrixB
		matrix_B = new JPanel();
		matrix_B.setBorder(new TitledBorder(new LineBorder(Color.black),"Matrix_B"));
		
		matrixlayout_B = new GridLayout(Matrix.rowB, Matrix.colB, 5, 5);
		matrix_B.setLayout(matrixlayout_B);
		
		for(int i = 0; i < Matrix.rowB * Matrix.colB; i++) {
			jTextFields_B.add(new JTextField(50));
			jTextFields_B.get(i).setFont(new Font("Dialog", 0, 12));
			if(Matrix.subMatrix.containsKey(i)) {
				jTextFields_B.get(i).setText(String.valueOf(Matrix.subMatrix.get(i)));
			}
		}
		jTextFields_B.stream().forEach(data->matrix_B.add(data));
		//-------------------------------------------------------------
		
		//-------------------------------------------------------------
		//Setting Function_Panel of Matrix
		function_Panel_M = new JPanel();
		function_Panel_M.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		function_Panel_M.setBorder(new TitledBorder(new LineBorder(Color.black), "Function"));
		
		functions_M.addAll(Arrays.asList("A + B", "A - B", "A x B"));
		functions_M.stream().forEach(data->{
			jbutton_fucntions_M.add(new JButton(data));
		});
		jbutton_fucntions_M.stream().forEach(data->{
			
			data.addActionListener(null);
			data.setFont(new Font("Dialog", 0, 14));
			function_Panel_M.add(data);
		});
		
		//Setting main_Panel_M by adding MatrixA, MatrixB, Function_Panel_M
		main_PanelLayout = new GridLayout(1,3,3,3);
		main_Panel_M = new JPanel();
		main_Panel_M.setLayout(main_PanelLayout);
		main_Panel_M.add(matrix_A); main_Panel_M.add(function_Panel_M); main_Panel_M.add(matrix_B);
		
		//Setting matrix_Panel by adding main_Panel_M
		matrix_Panel = new JPanel();
		matrix_Panel.setLayout(new BorderLayout(3,3));
		matrix_Panel.add(main_Panel_M,BorderLayout.CENTER);
	}
	
	//-----------------------------------------------------------
	//Constructor
	public Calculator() {
		setTitle("Calculator"); setSize(370,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		card_Layout = new CardLayout(5,5);
		
		setArithmaticCalculator();
		setMatrixCalculator();
		setMenuBar();
		
		c = getContentPane(); c.setLayout(card_Layout);
		c.add(arithmatic_Panel, "Basic");
		c.add(matrix_Panel, "Matrix");
		
		setVisible(true);
	}
	//-----------------------------------------------------------
	
	//-----------------------------------------------------------
	//Main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculator();
	}
	
}
