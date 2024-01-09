import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;


public class PrintInfo extends JFrame implements ActionListener {
	JLabel label = new JLabel("�л�������ȸ", JLabel.CENTER);
	JLabel title = new JLabel();
	
	JPanel cPnl2 = new JPanel();	//	������ ����
	JPanel cPnl = new JPanel();	//	����
	JPanel bPnl = new JPanel();	//	�Ʒ� ��ư
	JPanel sPnl = new JPanel();	//	�� �Է�

	JButton btn = new JButton("��ȸ");
	JButton btn1 = new JButton("�л������Է�");
	JButton btn2 = new JButton("�л���������");
	JButton btn3 = new JButton("Ȩ����");

	JTextField tNo = new JTextField("", 10);	// ���̵�
	ButtonGroup bg = new ButtonGroup();
	JRadioButton rb = new JRadioButton("�� ü", true);
	JRadioButton rb2 = new JRadioButton("�� ��", false);
	
	JTextArea tArea = new JTextArea();
	JTextArea aTitle = new JTextArea();
	JScrollPane sPane = new JScrollPane(tArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	String prtNo, prtName, prtBirth, prtGender, prtPhone, prtScore, prtAddr;
	final static String MY_PATH = "D:\\mhwon\\src\\person\\";
	String myNo;
	
	public PrintInfo(String no) {
		setLayout(new BorderLayout(10, 30));
		add(label, BorderLayout.NORTH);
		add(cPnl, BorderLayout.CENTER);
		add(bPnl, BorderLayout.SOUTH );

		label.setForeground(Color.BLUE);
		Font fnt = new Font("Serif", Font.BOLD, 20);
		label.setFont(fnt);

		cPnl.setLayout(new BorderLayout(10, 20));
		bPnl.setLayout(new GridLayout(1, 3));

		cPnl.add(sPnl, BorderLayout.NORTH);
		cPnl.add(cPnl2, BorderLayout.CENTER);
		sPnl.setLayout(new FlowLayout());

		cPnl2.setLayout(new BorderLayout(10, 5));
		cPnl2.add(aTitle, BorderLayout.NORTH);
		cPnl2.add(sPane, BorderLayout.CENTER);
		
		aTitle.setText(" �й�\t �̸�\t  �������\t       ����\t               ����ó\t          ��ռ���\t\t�ּ�");
		aTitle.setEditable(false);
		fnt = new Font("Serif", Font.BOLD, 13);
		aTitle.setFont(fnt);
		
		bg.add(rb);
		bg.add(rb2);
		sPnl.add(rb);
		sPnl.add(rb2);
		sPnl.add(tNo);
		sPnl.add(btn);	//	��ȸ��ư
		tNo.setEditable(false);
		
		rb.addActionListener(this);
		rb2.addActionListener(this);

		bPnl.add(btn1);	//	�Է¹�ư
		bPnl.add(btn2);	//	������ư
		bPnl.add(btn3);	//	Ȩ���ι�ư

		btn.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);

		setSize(850, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		myNo = no;
	}

	public void actionPerformed(ActionEvent e) {
		String act = e.getActionCommand();
		
		if(act.equals("�� ��")) {
			tNo.setEditable(true);
		}
		else if(act.equals("�� ü")) {
			tNo.setText("");
			tNo.setEditable(false);
		}
		else if(act.equals("��ȸ")) {
			printPerson();
		}
		else if(act.equals("�л������Է�")) {
			dispose();
			new MemberJoin(myNo);
		}
		else if(act.equals("�л���������")) {
			delPerson();
		}
		else if(act.equals("Ȩ����")) {
			dispose();
			new StManager(myNo);
		}
	}
	
	public String getScore(String no) {
		String scPath = "D:\\mhwon\\src\\score\\";
		BufferedReader br; 
		int score = 0, total = 0;
		int avg = 0;
		int i = 0;

		File f = new File(scPath + no + ".txt");

		if(!f.exists()) {	// �ش� ����(�� ����)�� �����ϴ��� Ȯ��
			return " ";
		}

		try  {
			br = new BufferedReader(new FileReader(scPath + no + ".txt"));
			String line;

			while ((line = br.readLine()) != null) {
				i++;
				
				if(line.equals(" "))
					score = 0;
				else
					score = Integer.parseInt(line);
				
				total += score;
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			return " ";
		}
		
		avg = (int)(total / i);
		System.out.println(avg);
		return Integer.toString(avg);
	}
	
	public void printPerson() {
		String inNo = "";
		File[] files = null;
		String path = null;
		String fName = null;
		String gndr = "1";
		String text = "";

		
		if(rb.isSelected()) {  // ��ü
			path = "D:\\mhwon\\src\\person";
		}
		else {
			inNo = tNo.getText();
			path = "D:\\mhwon\\src\\person\\" + inNo + ".txt";

			if(inNo.length() == 0 ) {
				JOptionPane.showMessageDialog(this,  "�й��� �Է��ϼ���.");
				return;
			}
		}
		
		File f = new File(path);

		//	��ü ��ȸ�� ���
		if(f.isDirectory()) {
			files = f.listFiles();
		} else {
			files = new File[1];
			files[0] = new File(path);
			
			if(f.exists() == false) {	// �ش� ���̵� ���� ���
				JOptionPane.showMessageDialog(this,  "�ش� �й��� ��ϵ� �й��� �ƴմϴ�.");
				return;
			} 
		}
		
		for(File file : files) {
			//	�й�,  �̸�, �������, ����, ����ó, ��ռ���, �ּ�
			try {
				path = file.getAbsolutePath();
				fName = file.getName();
				char first = fName.charAt(0);
				if((first != '9') && (first != 'l')) {	// ������ �����̳� last ������ �ƴ� ���� �л�������
	
					BufferedReader lbr = new BufferedReader(new FileReader(path));
					lbr.readLine();				// 	���а� �����ͼ� ����
					prtNo = lbr.readLine();		//	���� �й� ������
					lbr.readLine();				//	�������
					prtName = lbr.readLine();	//	�̸� ������
					prtBirth = lbr.readLine();	//	������� ������ 
					gndr = lbr.readLine();	//	���� ������ 
					
					if(gndr.equals("1"))
						prtGender = "����";
					else
						prtGender = "����";
						
					prtPhone = lbr.readLine();	//	����ó ������ 
					
					prtScore = getScore(prtNo);
					prtAddr = lbr.readLine();	//	�ּ� ������ 
					
//					System.out.println("�л����� : " + prtNo + "\t"+ prtName + "\t"+ prtBirth + "\t"+ prtGender + "\t"+ prtPhone + "\t"+ prtScore + "\t"+ prtAddr);
					text = text + prtNo + "\t"+ prtName + "\t"+ prtBirth + "\t"+ prtGender + "\t"+ prtPhone + "\t"+ "\t"+ prtScore + "\t"+ prtAddr + "\n";
					tArea.setText(text);
					lbr.close();
				}
			} catch (Exception ex) {
				System.out.println(ex + " => ����");
			} 
		}
	}
	
	public void delPerson() {
		
		if(rb.isSelected()) {  // ��ü
			JOptionPane.showMessageDialog(this,  "������ �й��� ��������");
			return;
		}
		
		String inNo = tNo.getText();
		if(inNo.length() == 0 ) {
			JOptionPane.showMessageDialog(this,  "������ �й��� ��������.");
			return;
		}

		File f = new File(MY_PATH + inNo + ".txt");

		if(!f.exists()) {	// �ش� ����(�� ����)�� �����ϴ��� Ȯ��
			JOptionPane.showMessageDialog(this,  "�ش� �й��� ��ϵ� �й��� �ƴմϴ�.");
			return;
		}

		//	�л��������� Ȯ��
		String fName = f.getName();
		char first = fName.charAt(0);

		if((first != '9') && (first != 'l')) {	// ������ �����̳� last ������ �ƴ� ���� �л�������
			f.delete();
			JOptionPane.showMessageDialog(this,  "�����Ǿ����ϴ�.");
		}
		
		//	�˻��� ����Ʈ�� �־��ٸ� Ŭ����
		tArea.setText("");
	}
}
