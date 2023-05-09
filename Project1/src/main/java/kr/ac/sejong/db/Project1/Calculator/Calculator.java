package kr.ac.sejong.db.Project1.Calculator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;

public final class Calculator extends JFrame {
	//-------------------------------------------------------
	//Field for main_container
	Container c; CardLayout card_Layout;
	
	//Field for menubar
	JMenuBar jMenuBar; JMenu jMenu; JMenuItem basicItem, matrixItem, bitlogicItem, unitItem;
	
	//Field for arithmatic calculator
	JPanel arithmetic_Panel;
	JTextArea jArea_A;
	JPanel main_Panel_A; JPanel operand_Panel; JPanel function_Panel; JPanel operator_Panel;
	
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
	
	JPanel rowcol_Config, config_Panel_A, config_Panel_B;
	private List<String> matrixConfig_Phrase = Arrays.asList("row+","row-","col+","col-");
	private List<JButton> matrixConfig_A = new ArrayList<>();
	private List<JButton> matrixConfig_B = new ArrayList<>();
	
	private List<JTextField> jTextFields_A = new ArrayList<>();
	private List<JTextField> jTextFields_B = new ArrayList<>();
	private List<JButton> jbutton_fucntions_M = new ArrayList<>();
	private List<String> functions_M = new ArrayList<>();
	public Map<Integer, Double> result = null;
	
	StringBuffer mainExp_M = new StringBuffer("--------------------Results--------------------\n");
	
	//Field for logic calculator
	JPanel bitlogic_Panel;
	JTextArea jArea_L;
	JPanel main_Panel_L; JPanel operand_Panel_L; JPanel operator_Panel_L; JPanel function_Panel_L;
	
	private List<String> functions_L = new ArrayList<>();
	private List<String> operators_L = new ArrayList<>();
	private List<JButton> jbutton_Operators_L = new ArrayList<>();
	private List<JButton> jbutton_functions_L = new ArrayList<>();
	private List<JButton> jbutton_Operands_L = new ArrayList<>();
	
	//Field for UnitConverter
	JPanel unitconverter_Panel;
	JPanel main_Panel_Unit;
	
	JPanel topicSelection_Panel;
	
	Label convertLabel;
	JPanel input_Panel;
	JPanel comboBoxA_Panel, comboBoxB_Panel;
	JTextField inputTextField_A, inputTextField_B;
	JComboBox<String> comboBoxA, comboBoxB;
	
	JPanel textfields_Panel = new JPanel(new GridLayout(5,3,5,2));
	
	private int cnt = 0;
	private List<String> stringTopic = Arrays.asList("Length","Area","Weight","Volume","Temp.","Pressure","Velocity","Data");
	private List<JButton> selectTopic = new ArrayList<>();
	private List<String> scaleOfLength_S = Arrays.asList("mm","cm","m","km","in","ft","yd","mile","자","간","정","리","해리");
	private List<String> scaleOfArea_S = Arrays.asList("m^2","a","ha","km^2","ft^2","yd^2","ac","평방자","평","단보","정보");
	private List<String> scaleOfWeight_S = Arrays.asList("mg","g","kg","t","kt","gr","oz","lb","돈","냥","근","관");
	private List<String> scaleOfVolume_S = Arrays.asList("cc","ml","dl","l","cm^3","m^3","in^3","ft^3","yd^3","gal","bbl","oz","홉","되","말");
	private List<String> scaleOfTemp_S = Arrays.asList("C","F","K","R");
	private List<String> scaleOfPressure_S = Arrays.asList("atm","Pa","hPa","kPa","MPa","dyne/cm^2","mb","bar","kgf/cm^2","psi","mmHg","inchHg","mmH2O","inchH2O");
	private List<String> scaleOfVelocity_S = Arrays.asList("m/s","m/h","km/s","km/h","in/s","in/h","ft/s","ft/h","mi/s","mi/h","kn","mach");
	private List<String> scaleOfDataCapacity_S = Arrays.asList("bit", "B", "KB", "MB","GB","TB","PB","EB");
	
	
	
	//--------------------------------------------------------
	
	//--------------------------------------------------------
	//MenuBar Setting
	void setMenuBar() {
		jMenuBar = new JMenuBar();
		jMenu = new JMenu("Select");
		
		basicItem = new JMenuItem("Basic"); matrixItem = new JMenuItem("Matrix"); bitlogicItem = new JMenuItem("BitLogic"); unitItem = new JMenuItem("Unit");
		basicItem.addActionListener(new SelectActionListener());
		matrixItem.addActionListener(new SelectActionListener());
		bitlogicItem.addActionListener(new SelectActionListener());
		unitItem.addActionListener(new SelectActionListener());
		
		jMenu.add(basicItem); jMenu.add(matrixItem); jMenu.add(bitlogicItem); jMenu.add(unitItem);
		jMenuBar.add(jMenu);
		setJMenuBar(jMenuBar);
	}
	
	//MenuBar eventlistener
	class SelectActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem jMenuItem = (JMenuItem)e.getSource();
			if(jMenuItem.getText().equals("Basic")) {
				setSize(370,500);
				card_Layout.show(c, "Basic");
			}
			else if(jMenuItem.getText().equals("Matrix")) {
				setSize(785,420);
				card_Layout.show(c, "Matrix");
			}
			else if(jMenuItem.getText().equals("BitLogic")) {
				setSize(450,500);
				card_Layout.show(c, "BitLogic");
			}
			else if(jMenuItem.getText().equals("Unit")){
				setSize(700,420);
				card_Layout.show(c, "Unit");
			}
		}
	}
	//----------------------------------------------------------
	
	//----------------------------------------------------------
	//Basic calculator button setting
	void setArithmaticCalculator() {
		//Base panel
		arithmetic_Panel = new JPanel();
		arithmetic_Panel.setLayout(new BorderLayout());
		
		//Formula_panel textarea
		jArea_A = new JTextArea(4,300);
		jArea_A.setEditable(false);
		jArea_A.setFont(new Font("Dialog", 1, 20));
		
		//Operand, Operator, Function panel merged to main panel
		main_Panel_A = new JPanel();
		main_Panel_A.setBorder(new TitledBorder(new LineBorder(Color.black), "Click Buttons to create formula!"));
		main_Panel_A.setLayout(new BorderLayout(3,3));
		
		//Operand_panel buttons
		operand_Panel = new JPanel();
		operand_Panel.setLayout(new GridLayout(4,3,3,3));
		
		for(int i = 1; i <= 10; i++) {jbutton_Operands.add(new JButton(String.valueOf(i%10)));}
		jbutton_Operands.add(new JButton("."));
		jbutton_Operands.stream().forEach(s->{
			s.addActionListener(new SelectAction_BasicListener());
			s.setBackground(Color.ORANGE);
			s.setFont(new Font("Dialog", 1, 14));
			operand_Panel.add(s);
		});
		JButton resultButton = new JButton("=");
		resultButton.setFont(new Font("Dialog", 1, 14));
		resultButton.addActionListener(new SelectAction_BasicListener());
		resultButton.setBackground(Color.ORANGE);
		operand_Panel.add(resultButton);
		
		//Function_panel buttons
		function_Panel = new JPanel();
		function_Panel.setLayout(new GridLayout(1,6,3,3));
		
		functions.addAll(Arrays.asList("C", "CE", "M+", "M-", "MR", "MC"));
		functions.stream().forEach(data->{
			jbutton_functions.add(new JButton(data));
		});
		jbutton_functions.stream().forEach(s->{
			s.addActionListener(new SelectAction_BasicListener());
			s.setBackground(Color.ORANGE);
			s.setFont(new Font("Dialog", 1, 12));
			function_Panel.add(s);
		});
		
		//Operator_panel buttons
		operator_Panel = new JPanel();
		operator_Panel.setLayout(new GridLayout(4,2,3,3));
		
		operators.addAll(Arrays.asList("(", ")","+", "-", "*", "/", "%", "^"));
		operators.stream().forEach(data->{
			jbutton_Operators.add(new JButton(data));
		});
		jbutton_Operators.stream().forEach(s->{
			s.addActionListener(new SelectAction_BasicListener());
			s.setBackground(Color.ORANGE);
			s.setFont(new Font("Dialog", 1, 14));
			operator_Panel.add(s);
		});
		
		//Merge all panels to main panel
		main_Panel_A.add(operand_Panel,BorderLayout.CENTER);
		main_Panel_A.add(operator_Panel,BorderLayout.EAST);
		main_Panel_A.add(function_Panel,BorderLayout.NORTH);
		
		//Add all component_panel to Base_Panel(arithmatic_Panel)
		arithmetic_Panel.add(jArea_A, BorderLayout.NORTH);
		arithmetic_Panel.add(main_Panel_A, BorderLayout.CENTER);
	}
	
	//Basic calculator event listener
	class SelectAction_BasicListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
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
				BasicArithmatic.mainExp.append(String.valueOf(BasicArithmatic.mainMemory));
			}
			else if(op_Button.getText().equals("MC")) {
				BasicArithmatic.mainMemory = 0;
			}
			else {
				try {
					BasicArithmatic.mainMemory = BasicArithmatic.Calculate(BasicArithmatic.convert());
					BasicArithmatic.mainExp.delete(0, BasicArithmatic.mainExp.length());
					BasicArithmatic.mainExp.append(BasicArithmatic.mainMemory);
				}
				catch(EmptyStackException exp_stack) {
					exp_stack.printStackTrace();
					JOptionPane.showMessageDialog(null, "Wrong type of equation!");
				}
			}
			jArea_A.setText(BasicArithmatic.mainExp.toString());
		}
	}
	//-----------------------------------------------------------
	
	//-----------------------------------------------------------
	//Matrix Calculator button setting
	void setMatrixCalculator() {
		//--------------------------------------------------------
		//Setting result TextArea
		jArea_M = new JTextArea(mainExp_M.toString(),8,370);
		jArea_M.setEditable(false);
		
		//Setting MatrixA
		matrix_A = new JPanel();
		matrix_A.setBorder(new TitledBorder(new LineBorder(Color.black),"Matrix_A"));
		
		matrixlayout_A = new GridLayout(Matrix.rowA, Matrix.colA, 5, 5);
		matrix_A.setLayout(matrixlayout_A);
		
		for(int i = 0; i < Matrix.rowA * Matrix.colA; i++) {
			jTextFields_A.add(new JTextField(50));
			jTextFields_A.get(i).setFont(new Font("Dialog", 1, 12));
			jTextFields_A.get(i).setName(String.valueOf(i));
			jTextFields_A.get(i).setHorizontalAlignment(JTextField.RIGHT);
		}
		jTextFields_A.stream().forEach(data->matrix_A.add(data));
		
		//Setting MatrixB
		matrix_B = new JPanel();
		matrix_B.setBorder(new TitledBorder(new LineBorder(Color.black),"Matrix_B"));
		
		matrixlayout_B = new GridLayout(Matrix.rowB, Matrix.colB, 5, 5);
		matrix_B.setLayout(matrixlayout_B);
		
		for(int i = 0; i < Matrix.rowB * Matrix.colB; i++) {
			jTextFields_B.add(new JTextField(50));
			jTextFields_B.get(i).setFont(new Font("Dialog", 1, 12));
			jTextFields_B.get(i).setName(String.valueOf(i));
			jTextFields_B.get(i).setHorizontalAlignment(JTextField.RIGHT);
		}
		jTextFields_B.stream().forEach(data->matrix_B.add(data));
		
		//Setting Function_Panel of Matrix
		function_Panel_M = new JPanel();
		function_Panel_M.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		function_Panel_M.setBorder(new TitledBorder(new LineBorder(Color.black), "Function"));
		
		functions_M.addAll(Arrays.asList("A + B", "A - B", "A x B"));
		functions_M.stream().forEach(data->{
			jbutton_fucntions_M.add(new JButton(data));
		});
		
		class SelectAction_MatrixListener implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton jButton_M = (JButton)e.getSource();
				for(JTextField dataA : jTextFields_A) {
					try {
						Matrix.mainMatrix.put(Integer.parseInt(dataA.getName()), Double.parseDouble(dataA.getText()));
					}
					catch(NumberFormatException exp_null) {
						Matrix.mainMatrix.put(Integer.parseInt(dataA.getName()), (double)0);
					}
				}
				for(JTextField dataB : jTextFields_B) {
					try {
						Matrix.subMatrix.put(Integer.parseInt(dataB.getName()), Double.parseDouble(dataB.getText()));
					}
					catch(NumberFormatException exp_null) {
						Matrix.subMatrix.put(Integer.parseInt(dataB.getName()), (double)0);
					}
				}
				
				if(jButton_M.getText().equals("A + B")) {
					try {
						Matrix.matrix_Add();
						mainExp_M.append("-------------------\"A + B\"-------------------");
						for(int i = 0; i < Matrix.rowA * Matrix.colB; i++) {
							if(i % Matrix.colB == 0) mainExp_M.append("\n");
							mainExp_M.append(Matrix.result.get(i).toString() + "\t");
						}
						mainExp_M.append("\n\n");
					}
					catch(ArithmeticException exp_M) {
						exp_M.printStackTrace();
						JOptionPane.showMessageDialog(null, exp_M.getMessage());
					}
				}
				else if(jButton_M.getText().equals("A - B")) {
					try {
						Matrix.matrix_Sub();
						mainExp_M.append("-------------------\"A - B\"-------------------");
						for(int i = 0; i < Matrix.rowA * Matrix.colB; i++) {
							if(i % Matrix.colB == 0) mainExp_M.append("\n");
							mainExp_M.append(Matrix.result.get(i).toString() + "\t");
						}
						mainExp_M.append("\n\n");
					}
					catch(ArithmeticException exp_M) {
						exp_M.printStackTrace();
						JOptionPane.showMessageDialog(null, exp_M.getMessage());
					}
				}
				else if(jButton_M.getText().equals("A x B")) {
					try {
						Matrix.matrix_Mul();
						mainExp_M.append("-------------------\"A x B\"-------------------");
						for(int i = 0; i < Matrix.rowA * Matrix.colB; i++) {
							if(i % Matrix.colB == 0) mainExp_M.append("\n");
							mainExp_M.append(Matrix.result.get(i).toString() + "\t");
						}
						mainExp_M.append("\n\n");
					}
					catch(ArithmeticException exp_M) {
						exp_M.printStackTrace();
						JOptionPane.showMessageDialog(null, exp_M.getMessage());
					}
				}
				jArea_M.setText(mainExp_M.toString());
			}
		}
		jbutton_fucntions_M.stream().forEach(data->{
			data.addActionListener(new SelectAction_MatrixListener());
			data.setFont(new Font("Dialog", 1, 14));
			function_Panel_M.add(data);
		});
		
		//Setting rowcol_Config Panel contains jbuttons of row and col of matrix A, B
		class SelectAction_MatrixConfigListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton actionButton = (JButton)e.getSource();
				if(actionButton.getName().equals("ConfigA")) {
					if(actionButton.getText().equals("row+")) Matrix.rowA++;
					else if(actionButton.getText().equals("row-") && Matrix.rowA > 1) Matrix.rowA--;
					else if(actionButton.getText().equals("col+")) Matrix.colA++;
					else if(actionButton.getText().equals("col-") && Matrix.colA > 1) Matrix.colA--;
					Matrix.mainMatrix.clear();
					jTextFields_A.clear();
					matrix_A.removeAll();
					matrixlayout_A.setRows(Matrix.rowA); matrixlayout_A.setColumns(Matrix.colA);
					for(int i = 0; i < Matrix.rowA * Matrix.colA; i++) {
						jTextFields_A.add(new JTextField(50));
						jTextFields_A.get(i).setFont(new Font("Dialog", 1, 12));
						jTextFields_A.get(i).setName(String.valueOf(i));
						jTextFields_A.get(i).setHorizontalAlignment(JTextField.RIGHT);
					}
					jTextFields_A.stream().forEach(data->matrix_A.add(data));
					matrix_A.revalidate();
					matrix_A.repaint();
				}
				else {
					if(actionButton.getText().equals("row+")) Matrix.rowB++;
					else if(actionButton.getText().equals("row-") && Matrix.rowB > 1) Matrix.rowB--;
					else if(actionButton.getText().equals("col+")) Matrix.colB++;
					else if(actionButton.getText().equals("col-") && Matrix.colB > 1) Matrix.colB--;
					Matrix.subMatrix.clear();
					jTextFields_B.clear();
					matrix_B.removeAll();
					matrixlayout_B.setRows(Matrix.rowB); matrixlayout_B.setColumns(Matrix.colB);
					for(int i = 0; i < Matrix.rowB * Matrix.colB; i++) {
						jTextFields_B.add(new JTextField(50));
						jTextFields_B.get(i).setFont(new Font("Dialog", 1, 12));
						jTextFields_B.get(i).setName(String.valueOf(i));
						jTextFields_B.get(i).setHorizontalAlignment(JTextField.RIGHT);
					}
					jTextFields_B.stream().forEach(data->matrix_B.add(data));
					matrix_B.revalidate();
					matrix_B.repaint();
				}
			}
		}
		matrixConfig_Phrase.stream().forEach(data->{
			JButton tmp1 = new JButton(data), tmp2 = new JButton(data);
			tmp1.setName("ConfigA"); tmp1.addActionListener(new SelectAction_MatrixConfigListener());
			tmp2.setName("ConfigB"); tmp2.addActionListener(new SelectAction_MatrixConfigListener());
			tmp1.setFont(new Font("Dialog",1,12)); tmp2.setFont(new Font("Dialog",1,12));
			matrixConfig_A.add(tmp1);
			matrixConfig_B.add(tmp2);
		});
		
		rowcol_Config = new JPanel(new GridLayout(1,2));
		config_Panel_A = new JPanel(new FlowLayout(FlowLayout.LEFT,3,0));
		config_Panel_B = new JPanel(new FlowLayout(FlowLayout.RIGHT,3,0));
		matrixConfig_A.stream().forEach(data->config_Panel_A.add(data));
		matrixConfig_B.stream().forEach(data->config_Panel_B.add(data));
		rowcol_Config.add(config_Panel_A); rowcol_Config.add(config_Panel_B);
		
		//Setting main_Panel_M by adding MatrixA, MatrixB, Function_Panel_M
		main_PanelLayout = new GridLayout(1,3,3,3);
		main_Panel_M = new JPanel();
		main_Panel_M.setLayout(main_PanelLayout);
		main_Panel_M.add(matrix_A); main_Panel_M.add(function_Panel_M); main_Panel_M.add(matrix_B);
		
		//Setting matrix_Panel by adding main_Panel_M
		matrix_Panel = new JPanel();
		matrix_Panel.setLayout(new BorderLayout(3,3));
		matrix_Panel.add(main_Panel_M,BorderLayout.CENTER);
		matrix_Panel.add(rowcol_Config, BorderLayout.NORTH);
		matrix_Panel.add(new JScrollPane(jArea_M), BorderLayout.SOUTH);
	}
	//-----------------------------------------------------------
	
	//-----------------------------------------------------------
	//Setting Bitlogic Calculator
	void setBitLogicCalculator() {
		bitlogic_Panel = new JPanel(new BorderLayout(3,3));
		
		//JTextArea -> Display formula that user created 
		jArea_L = new JTextArea(4,300); jArea_L.setEditable(false);
		jArea_L.setFont(new Font("Dialog", 1, 20));
		
		//Main_Panel -> operand_Panel + operator_Panel + function_Panel
		main_Panel_L = new JPanel(new BorderLayout(3,3));
		main_Panel_L.setBorder(new TitledBorder(new LineBorder(Color.black), "Click buttons to create formula"));
		
		//ActionListener for operands, operators, functions
		class SelectAction_BitlogicListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton)e.getSource();
				if(jbutton_Operands_L.stream().anyMatch(data->data.getText().equals(button.getText()))) {
					BitLogic.mainExp_L.append(button.getText());
				}
				else if(jbutton_Operators_L.stream().anyMatch(data->data.getText().equals(button.getText()))) {
					BitLogic.mainExp_L.append(button.getText());
				}
				else if(button.getText().equals("C")) {
					if(BitLogic.mainExp_L.length() > 0)
						BitLogic.mainExp_L.delete(0, BitLogic.mainExp_L.length());
				}
				else if(button.getText().equals("CE")) {
					if(BitLogic.mainExp_L.length() > 0)
						BitLogic.mainExp_L.deleteCharAt(BitLogic.mainExp_L.length() - 1);
				}
				else if(button.getText().equals("MCOPY")) {
					BitLogic.mainExp_L.append(String.valueOf(BasicArithmatic.mainMemory));
				}
				else if(button.getText().equals("MCLEAR")) {
					BitLogic.mainMemory_L = false;
				}
				else {
					try {
						BitLogic.mainMemory_L = BitLogic.Calculate(BitLogic.convert_BitLogic());
						if(BitLogic.mainMemory_L == true)
							BitLogic.mainExp_L.replace(0, BitLogic.mainExp_L.length(), "T");
						else BitLogic.mainExp_L.replace(0, BitLogic.mainExp_L.length(), "F");
					}
					catch(EmptyStackException exp_LS) {
						exp_LS.printStackTrace();
						JOptionPane.showMessageDialog(null, exp_LS.getMessage());
					}
					catch(Exception exp_L) {
						exp_L.printStackTrace();
						JOptionPane.showMessageDialog(null, exp_L.getMessage());
					}
				}
				jArea_L.setText(BitLogic.mainExp_L.toString());
			}
		}
		
		//Setting operand_Panel of BitLogic calculator
		operand_Panel_L = new JPanel(new GridLayout(4,3,3,3));
		for(int i = 1; i < 11; i++) {jbutton_Operands_L.add(new JButton(String.valueOf(i % 10)));}
		jbutton_Operands_L.add(new JButton("T")); jbutton_Operands_L.add(new JButton("F"));
		jbutton_Operands_L.stream().forEach(data->{
			data.addActionListener(new SelectAction_BitlogicListener());
			data.setBackground(Color.ORANGE);
			data.setFont(new Font("Dialog", 1, 12));
			operand_Panel_L.add(data);
		});
		
		//Setting operator_Panel of BitLogic Calculator
		operator_Panel_L = new JPanel(new GridLayout(4,4,3,3));
		operators_L.addAll(Arrays.asList("(", ")", "+", "-", "<<" ,">>", "&", "|", "^", "<", "<=", ">", ">=", "!=", "and", "or"));
		operators_L.stream().forEach(data->{
			jbutton_Operators_L.add(new JButton(data));
		});
		jbutton_Operators_L.stream().forEach(data->{
			data.addActionListener(new SelectAction_BitlogicListener());
			data.setBackground(Color.ORANGE);
			data.setFont(new Font("Dialog", 1, 12));
			operator_Panel_L.add(data);
		});
		
		//Setting function_Panel of BitLogic Calculator
		function_Panel_L = new JPanel(new GridLayout(1,5,3,3));
		functions_L.addAll(Arrays.asList("C","CE","MCOPY","MCLEAR"));
		functions_L.stream().forEach(data->{
			jbutton_functions_L.add(new JButton(data));
		});
		jbutton_functions_L.stream().forEach(data->{
			data.addActionListener(new SelectAction_BitlogicListener());
			data.setBackground(Color.ORANGE);
			data.setFont(new Font("Dialog",1,12));
			function_Panel_L.add(data);
		});
		JButton result_LButton = new JButton("=");
		result_LButton.addActionListener(new SelectAction_BitlogicListener());
		result_LButton.setBackground(Color.ORANGE);
		result_LButton.setFont(new Font("Dialog", 1, 12));
		function_Panel_L.add(result_LButton);
		
		//Setting main_Panel for Bitlogic Calculator
		main_Panel_L.add(operand_Panel_L, BorderLayout.CENTER);
		main_Panel_L.add(operator_Panel_L,BorderLayout.EAST);
		main_Panel_L.add(function_Panel_L, BorderLayout.NORTH);
		
		//Setting bitlogic_Panel for Bitlogic Calculator
		bitlogic_Panel.add(jArea_L, BorderLayout.NORTH);
		bitlogic_Panel.add(main_Panel_L, BorderLayout.CENTER);
	}
	//-----------------------------------------------------------
	
	//-----------------------------------------------------------
	//Setting Unit Converter
	void setUnitConverter() {
		//----------------------------------------------------------------------
		//Setting ComboBoxes
		class SelectAction_ComboBoxItemListener implements ItemListener{
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(scaleOfLength_S.contains(comboBoxA.getSelectedItem())) {
						cnt = scaleOfLength_S.size();
						inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfLength, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
					}
					else if(scaleOfArea_S.contains(comboBoxA.getSelectedItem())) {
						cnt = scaleOfArea_S.size();
						inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfArea, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
					}
					else if(scaleOfPressure_S.contains(comboBoxA.getSelectedItem())) {
						cnt = scaleOfPressure_S.size();
						inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfPressure, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
					}
					else if(scaleOfWeight_S.contains(comboBoxA.getSelectedItem())) {
						cnt = scaleOfWeight_S.size();
						inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfWeight, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
					}
					else if(scaleOfVolume_S.contains(comboBoxA.getSelectedItem())) {
						cnt = scaleOfVolume_S.size();
						inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVolume, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
					}
					else if(scaleOfVelocity_S.contains(comboBoxA.getSelectedItem())) {
						cnt = scaleOfVelocity_S.size();						
						inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVelocity, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
					}
					else if(scaleOfTemp_S.contains(comboBoxA.getSelectedItem())) {
						cnt = scaleOfTemp_S.size();
						inputTextField_B.setText(String.valueOf(UnitConverter.convertOfTemp(comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
					}
					else if(scaleOfDataCapacity_S.contains(comboBoxA.getSelectedItem())) {
						cnt = scaleOfDataCapacity_S.size();
						inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfDataCapacity, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
					}
					
					textfields_Panel.removeAll();
					for(int i = 0; i < cnt; i++) {
						JTextField tmp = new JTextField();
						tmp.setEditable(false);
						tmp.setFont(new Font("Dialog", 1, 12));
						tmp.setHorizontalAlignment(JTextField.CENTER);
						
						if(scaleOfLength_S.contains(comboBoxA.getSelectedItem())) {
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfLength_S.get(i)));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfLength, comboBoxA.getSelectedIndex(), i)) + scaleOfLength_S.get(i));
						}
						else if(scaleOfArea_S.contains(comboBoxA.getSelectedItem())) {
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfArea_S.get(i)));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfArea, comboBoxA.getSelectedIndex(), i)) + scaleOfArea_S.get(i));
						}
						else if(scaleOfWeight_S.contains(comboBoxA.getSelectedItem())) {
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfWeight_S.get(i)));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfWeight, comboBoxA.getSelectedIndex(), i)) + scaleOfWeight_S.get(i));
						}
						else if(scaleOfVolume_S.contains(comboBoxA.getSelectedItem())) {
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfVolume_S.get(i)));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVolume, comboBoxA.getSelectedIndex(), i)) + scaleOfVolume_S.get(i));
						}
						else if(scaleOfVelocity_S.contains(comboBoxA.getSelectedItem())) {
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfVelocity_S.get(i)));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVelocity, comboBoxA.getSelectedIndex(), i)) + scaleOfVelocity_S.get(i));
						}
						else if(scaleOfTemp_S.contains(comboBoxA.getSelectedItem())) {
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfTemp_S.get(i)));
							tmp.setText(String.valueOf(UnitConverter.convertOfTemp(comboBoxA.getSelectedIndex(), i)) + scaleOfTemp_S.get(i));
						}
						else if(scaleOfPressure_S.contains(comboBoxA.getSelectedItem())) {
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfPressure_S.get(i)));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfPressure, comboBoxA.getSelectedIndex(), i)) + scaleOfPressure_S.get(i));
						}
						else if(scaleOfDataCapacity_S.contains(comboBoxA.getSelectedItem())) {
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfDataCapacity_S.get(i)));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfDataCapacity, comboBoxA.getSelectedIndex(), i)) + scaleOfDataCapacity_S.get(i));
						}
						textfields_Panel.add(tmp);
					}
							
					inputTextField_B.revalidate();
					inputTextField_B.repaint();
					textfields_Panel.revalidate();
					textfields_Panel.repaint();
				}
			}
		}		
		comboBoxA = new JComboBox<>(); comboBoxB = new JComboBox<>();
		comboBoxA.setModel(new DefaultComboBoxModel<>()); comboBoxB.setModel(new DefaultComboBoxModel<>());
		
		//Setting inputPanel
		input_Panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
		input_Panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK),"Input"));
		
		//Setting inputPanel
		inputTextField_A = new JTextField(15); 
		inputTextField_A.setFont(new Font("Dialog",1,12));
		inputTextField_A.setText(String.valueOf(0));
		inputTextField_A.setHorizontalAlignment(JTextField.RIGHT);
		inputTextField_A.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!((Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_ENTER || c == '.' || c == '-')) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		inputTextField_A.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UnitConverter.mainMemory_Unit = Double.valueOf(e.getActionCommand());
				textfields_Panel.removeAll();
				for(int i = 0; i < cnt; i++) {
					JTextField tmp = new JTextField();
					tmp.setEditable(false);
					tmp.setFont(new Font("Dialog",1,12));
					tmp.setHorizontalAlignment(JTextField.CENTER);
					if(scaleOfLength_S.contains(comboBoxA.getSelectedItem())) {
						tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfLength_S.get(i)));
						tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfLength, comboBoxA.getSelectedIndex(), i)) + scaleOfLength_S.get(i));
					}
					else if(scaleOfArea_S.contains(comboBoxA.getSelectedItem())) {
						tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfArea_S.get(i)));
						tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfArea, comboBoxA.getSelectedIndex(), i)) + scaleOfArea_S.get(i));
					}
					else if(scaleOfWeight_S.contains(comboBoxA.getSelectedItem())) {
						tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfWeight_S.get(i)));
						tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfWeight, comboBoxA.getSelectedIndex(), i)) + scaleOfWeight_S.get(i));
					}
					else if(scaleOfVolume_S.contains(comboBoxA.getSelectedItem())) {
						tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfVolume_S.get(i)));
						tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVolume, comboBoxA.getSelectedIndex(), i)) + scaleOfVolume_S.get(i));
					}
					else if(scaleOfVelocity_S.contains(comboBoxA.getSelectedItem())) {
						tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfVelocity_S.get(i)));
						tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVelocity, comboBoxA.getSelectedIndex(), i)) + scaleOfVelocity_S.get(i));
					}
					else if(scaleOfTemp_S.contains(comboBoxA.getSelectedItem())) {
						tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfTemp_S.get(i)));
						tmp.setText(String.valueOf(UnitConverter.convertOfTemp(comboBoxA.getSelectedIndex(), i)) + scaleOfTemp_S.get(i));
					}
					else if(scaleOfPressure_S.contains(comboBoxA.getSelectedItem())) {
						tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfPressure_S.get(i)));
						tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfPressure, comboBoxA.getSelectedIndex(), i)) + scaleOfPressure_S.get(i));
					}
					else if(scaleOfDataCapacity_S.contains(comboBoxA.getSelectedItem())) {
						tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfDataCapacity_S.get(i)));
						tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfDataCapacity, comboBoxA.getSelectedIndex(), i)) + scaleOfDataCapacity_S.get(i));
					}
					textfields_Panel.add(tmp);
				}
				if(scaleOfLength_S.contains(comboBoxA.getSelectedItem()))
					inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfLength, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
				else if(scaleOfArea_S.contains(comboBoxA.getSelectedItem()))
					inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfArea, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
				else if(scaleOfPressure_S.contains(comboBoxA.getSelectedItem()))
					inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfPressure, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
				else if(scaleOfWeight_S.contains(comboBoxA.getSelectedItem()))
					inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfWeight, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
				else if(scaleOfVolume_S.contains(comboBoxA.getSelectedItem()))
					inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVolume, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
				else if(scaleOfVelocity_S.contains(comboBoxA.getSelectedItem()))
					inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVelocity, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
				else if(scaleOfTemp_S.contains(comboBoxA.getSelectedItem()))
					inputTextField_B.setText(String.valueOf(UnitConverter.convertOfTemp(comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
				else if(scaleOfDataCapacity_S.contains(comboBoxA.getSelectedItem()))
					inputTextField_B.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfDataCapacity, comboBoxA.getSelectedIndex(), comboBoxB.getSelectedIndex())));
				
				inputTextField_B.revalidate();
				inputTextField_B.repaint();
				textfields_Panel.revalidate();
				textfields_Panel.repaint();
			}
		});
		
		inputTextField_B = new JTextField(15);
		inputTextField_B.setFont(new Font("Dialog",1,12));
		inputTextField_B.setEditable(false);
		inputTextField_B.setHorizontalAlignment(JTextField.RIGHT);
			
		//Setting merged panels -> combobox + jtextfield
		comboBoxA_Panel = new JPanel(new BorderLayout()); comboBoxB_Panel = new JPanel(new BorderLayout());
		comboBoxA_Panel.add(inputTextField_A,BorderLayout.CENTER); comboBoxA_Panel.add(comboBoxA,BorderLayout.EAST);
		comboBoxB_Panel.add(inputTextField_B,BorderLayout.CENTER); comboBoxB_Panel.add(comboBoxB,BorderLayout.EAST);
		
		//Setting Label
		convertLabel = new Label("=>");
		convertLabel.setFont(new Font("Dialog",1,12));
		convertLabel.setAlignment(Label.CENTER);
		
		//Adding components in input_Panel -> comboBoxA + convertlabel + comboBoxB
		input_Panel.add(comboBoxA_Panel); input_Panel.add(convertLabel); input_Panel.add(comboBoxB_Panel);		
		
		//Buttons for selection of topic
		topicSelection_Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topicSelection_Panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK),"Click button to change topic!"));
		
		stringTopic.stream().forEach(data->{
			selectTopic.add(new JButton(data));
		});
		selectTopic.stream().forEach(data->{
			data.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton)e.getSource();
					
					comboBoxA.removeAllItems(); comboBoxB.removeAllItems();
					comboBoxA.removeItemListener(comboBoxA.getItemListeners()[comboBoxA.getItemListeners().length - 1]);
					comboBoxB.removeItemListener(comboBoxB.getItemListeners()[comboBoxB.getItemListeners().length - 1]);
					textfields_Panel.removeAll();
					if(button.getText().equals("Length")) {
						cnt = scaleOfLength_S.size();
						scaleOfLength_S.stream().forEach(data->{comboBoxA.addItem(data); comboBoxB.addItem(data);});
						comboBoxA.setSelectedIndex(0); comboBoxB.setSelectedIndex(0);
						inputTextField_B.setText(String.valueOf(UnitConverter.mainMemory_Unit));
						for(int i = 0; i < scaleOfLength_S.size();i++) {
							JTextField tmp = new JTextField();
							tmp.setEditable(false);
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfLength_S.get(i)));
							tmp.setHorizontalAlignment(JTextField.CENTER);
							tmp.setFont(new Font("Dialog",1,12));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfLength, comboBoxA.getSelectedIndex(), i)) + scaleOfLength_S.get(i));
							textfields_Panel.add(tmp);
						}
					}
					else if(button.getText().equals("Area")) {
						cnt = scaleOfArea_S.size();
						scaleOfArea_S.stream().forEach(data->{comboBoxA.addItem(data); comboBoxB.addItem(data);});
						comboBoxA.setSelectedIndex(0); comboBoxB.setSelectedIndex(0);
						inputTextField_B.setText(String.valueOf(UnitConverter.mainMemory_Unit));
						for(int i = 0;i < scaleOfArea_S.size();i++) {
							JTextField tmp = new JTextField();
							tmp.setEditable(false);
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfArea_S.get(i)));
							tmp.setHorizontalAlignment(JTextField.CENTER);
							tmp.setFont(new Font("Dialog",1,12));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfArea, comboBoxA.getSelectedIndex(), i)) + scaleOfArea_S.get(i));
							textfields_Panel.add(tmp);
						}
					}
					else if(button.getText().equals("Weight")) {
						cnt = scaleOfWeight_S.size();
						scaleOfWeight_S.stream().forEach(data->{comboBoxA.addItem(data); comboBoxB.addItem(data);});
						comboBoxA.setSelectedIndex(0); comboBoxB.setSelectedIndex(0);
						inputTextField_B.setText(String.valueOf(UnitConverter.mainMemory_Unit));
						for(int i = 0;i < scaleOfWeight_S.size();i++) {
							JTextField tmp = new JTextField();
							tmp.setEditable(false);
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfWeight_S.get(i)));
							tmp.setHorizontalAlignment(JTextField.CENTER);
							tmp.setFont(new Font("Dialog",1,12));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfWeight, comboBoxA.getSelectedIndex(), i)) + scaleOfWeight_S.get(i));
							textfields_Panel.add(tmp);
						}
					}
					else if(button.getText().equals("Volume")) {
						cnt = scaleOfVolume_S.size();
						scaleOfVolume_S.stream().forEach(data->{comboBoxA.addItem(data); comboBoxB.addItem(data);});
						comboBoxA.setSelectedIndex(0); comboBoxB.setSelectedIndex(0);
						inputTextField_B.setText(String.valueOf(UnitConverter.mainMemory_Unit));
						for(int i = 0;i < scaleOfVolume_S.size();i++) {
							JTextField tmp = new JTextField();
							tmp.setEditable(false);
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfVolume_S.get(i)));
							tmp.setHorizontalAlignment(JTextField.CENTER);
							tmp.setFont(new Font("Dialog",1,12));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVolume, comboBoxA.getSelectedIndex(), i)) + scaleOfVolume_S.get(i));
							textfields_Panel.add(tmp);
						}
					}
					else if(button.getText().equals("Temp.")) {
						cnt = scaleOfTemp_S.size();
						scaleOfTemp_S.stream().forEach(data->{comboBoxA.addItem(data); comboBoxB.addItem(data);});
						comboBoxA.setSelectedIndex(0); comboBoxB.setSelectedIndex(0);
						inputTextField_B.setText(String.valueOf(UnitConverter.mainMemory_Unit));
						for(int i = 0;i < scaleOfTemp_S.size();i++) {
							JTextField tmp = new JTextField();
							tmp.setEditable(false);
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfTemp_S.get(i)));
							tmp.setHorizontalAlignment(JTextField.CENTER);
							tmp.setFont(new Font("Dialog",1,12));
							tmp.setText(String.valueOf(UnitConverter.convertOfTemp(comboBoxA.getSelectedIndex(), i)) + scaleOfTemp_S.get(i));
							textfields_Panel.add(tmp);
						}
					}
					else if(button.getText().equals("Pressure")) {
						cnt = scaleOfPressure_S.size();
						scaleOfPressure_S.stream().forEach(data->{comboBoxA.addItem(data); comboBoxB.addItem(data);});
						comboBoxA.setSelectedIndex(0); comboBoxB.setSelectedIndex(0);
						inputTextField_B.setText(String.valueOf(UnitConverter.mainMemory_Unit));
						for(int i = 0;i < scaleOfPressure_S.size();i++) {
							JTextField tmp = new JTextField();
							tmp.setEditable(false);
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfPressure_S.get(i)));
							tmp.setHorizontalAlignment(JTextField.CENTER);
							tmp.setFont(new Font("Dialog",1,12));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfPressure, comboBoxA.getSelectedIndex(), i)) + scaleOfPressure_S.get(i));
							textfields_Panel.add(tmp);
						}
					}
					else if(button.getText().equals("Velocity")) {
						cnt = scaleOfVelocity_S.size();
						scaleOfVelocity_S.stream().forEach(data->{comboBoxA.addItem(data); comboBoxB.addItem(data);});
						comboBoxA.setSelectedIndex(0); comboBoxB.setSelectedIndex(0);
						inputTextField_B.setText(String.valueOf(UnitConverter.mainMemory_Unit));
						for(int i = 0;i < scaleOfVelocity_S.size();i++) {
							JTextField tmp = new JTextField();
							tmp.setEditable(false);
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfVelocity_S.get(i)));
							tmp.setHorizontalAlignment(JTextField.CENTER);
							tmp.setFont(new Font("Dialog",1,12));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfVelocity, comboBoxA.getSelectedIndex(), i)) + scaleOfVelocity_S.get(i));
							textfields_Panel.add(tmp);
						}
					}
					else if(button.getText().equals("Data")) {
						cnt = scaleOfDataCapacity_S.size();
						scaleOfDataCapacity_S.stream().forEach(data->{comboBoxA.addItem(data); comboBoxB.addItem(data);});
						comboBoxA.setSelectedIndex(0); comboBoxB.setSelectedIndex(0);
						inputTextField_B.setText(String.valueOf(UnitConverter.mainMemory_Unit));
						for(int i = 0;i < scaleOfDataCapacity_S.size();i++) {
							JTextField tmp = new JTextField();
							tmp.setEditable(false);
							tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK), scaleOfDataCapacity_S.get(i)));
							tmp.setHorizontalAlignment(JTextField.CENTER);
							tmp.setFont(new Font("Dialog",1,12));
							tmp.setText(String.valueOf(UnitConverter.convertOfUnit(UnitConverter.scaleOfDataCapacity, comboBoxA.getSelectedIndex(), i)) + scaleOfDataCapacity_S.get(i));
							textfields_Panel.add(tmp);
						}
					}
					comboBoxA.addItemListener(new SelectAction_ComboBoxItemListener()); comboBoxB.addItemListener(new SelectAction_ComboBoxItemListener());
					
					inputTextField_B.revalidate();
					inputTextField_B.repaint();
					textfields_Panel.revalidate();
					textfields_Panel.repaint();
				}
			});
			data.setBackground(Color.getHSBColor(0.4f, 0.3f, 1f));
			data.setFont(new Font("Dialog",1,12));
			topicSelection_Panel.add(data);
		});
		//----------------------------------------------------------------------
		
		//------------------------------------------------------------------------
		//Setting Mainpanel, unitconverterpanel
		selectTopic.get(0).doClick();
		main_Panel_Unit = new JPanel(new BorderLayout());
		main_Panel_Unit.add(topicSelection_Panel, BorderLayout.NORTH);
		main_Panel_Unit.add(input_Panel, BorderLayout.CENTER);
		
		unitconverter_Panel = new JPanel(new BorderLayout());
		unitconverter_Panel.add(main_Panel_Unit,BorderLayout.NORTH);
		unitconverter_Panel.add(textfields_Panel,BorderLayout.CENTER);
		
		//------------------------------------------------------------------------
	}
	
	//-----------------------------------------------------------
	//Constructor
	public Calculator() {
		setTitle("Calculator"); setSize(370,500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		card_Layout = new CardLayout(5,5);
		
		setArithmaticCalculator();
		setMatrixCalculator();
		setBitLogicCalculator();
		setUnitConverter();
		setMenuBar();
		
		c = getContentPane(); c.setLayout(card_Layout);
		c.setBackground(Color.LIGHT_GRAY);
		c.add(arithmetic_Panel, "Basic");
		c.add(matrix_Panel, "Matrix");
		c.add(bitlogic_Panel, "BitLogic");
		c.add(unitconverter_Panel, "Unit");
		
		setVisible(true);
	}
	//-----------------------------------------------------------
	
	//-----------------------------------------------------------
	//Main
	public static void main(String[] args) {
		new Calculator();
		
	}
	
}
