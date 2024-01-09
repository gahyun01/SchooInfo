import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

public class LogIn extends JFrame implements ActionListener{
	JLabel label = new JLabel("�α���", JLabel.CENTER);
	JPanel pnl = new JPanel();
//	JPanel pnl2 = new JPanel();

//	JLabel lgbn = new JLabel("�� ��", JLabel.CENTER);
	JLabel lNo = new JLabel("����/�й�", JLabel.CENTER);
	JLabel lPwd = new JLabel("��й�ȣ", JLabel.CENTER);

	JButton bReq = new JButton("ȸ������");
	JButton bLog = new JButton("�α���");
	
//	ButtonGroup bg = new ButtonGroup();
//	JRadioButton rb = new JRadioButton("������", true);
//	JRadioButton rb2 = new JRadioButton("�� ��", false);

	JTextField tNo = new JTextField("", 10);	// ���̵�
	TextField tPwd = new TextField("", 8);		// ��й�ȣ

	String msg = "";
	String chkNo = "";
	int error = 0;
	
	
	public LogIn() {
		//	�α��� ȭ�� ���
		setLayout(new BorderLayout(10, 30));
		add(label, "North" );
		label.setForeground(Color.BLUE);
		Font fnt = new Font("Serif", Font.BOLD, 20);
		label.setFont(fnt);

		add(pnl, "Center" );
		pnl.setLayout(new GridLayout(3, 2));
//		pnl.add(lgbn);
//		pnl.add(pnl2);

//		pnl2.setLayout(new FlowLayout(FlowLayout.LEFT));
//		bg.add(rb);
//		bg.add(rb2);
//		pnl2.add(rb);
//		pnl2.add(rb2);
		
		pnl.add(lNo);
		pnl.add(tNo);
		pnl.add(lPwd);
		pnl.add(tPwd);
		tPwd.setEchoChar('*');
		
		pnl.add(bReq);
		pnl.add(bLog);
		
		bReq.addActionListener(this);
		bLog.addActionListener(this);

		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		if(str.equals("�α���")) {
			// �α��� ����Ȯ��
			String no = tNo.getText();
			String pwd = tPwd.getText();
			boolean ok = false;

			System.out.println("1 chkNo = " + chkNo + " no = " +  no + " error = " + error);
			
			if((error >= 5) && chkNo.equals(no)) {
				JOptionPane.showMessageDialog(this,  "��й�ȣ 5ȸ �̻� �����Դϴ�.");
				return;
			}
			
			if(chkNo.equals(no) == false) {	// ���� �Է��� ���̵���
				chkNo = no;	//	���5ȸ ���� Ȯ���� ���̵�
				error = 0;
			}
			
			System.out.println("2 chkNo = " + chkNo + " no = " +  no + " error = " + error);
				
			ok = checkPwd(no, pwd);
			if(!ok) {
				//��� ���Է� ��û
				JOptionPane.showMessageDialog(this,  msg);
			}
			else {
				//	���������� �л����� Ȯ��
				char first = no.charAt(0);
				if(first == '9') // ������ 
					new StManager(no);
				else 	// �л�
					new StManager2(no);

				//	�α��� ȭ�� ���� 
				dispose();
			}
		}
		else if (str.equals("ȸ������")) {	//	ȸ������ ��ư
			dispose();
			new MemberJoin("00000");
		}
	}
	
	public boolean checkPwd(String inNo, String inPwd) {
		String fName = "D:\\mhwon\\src\\person\\" + inNo + ".txt";
		
		String regNo = null;
		String regPwd = null;

		//	�α��� ����Ȯ��
		File f = new File(fName);
	
		// �ش� ����(�� ����)�� �����ϴ��� Ȯ���ؼ� ������ ��� ����
		if(f.exists() == false) {	// �ش� ���̵� ���� ���
			msg = "�ش� ���̵�� ��ϵ� ���̵� �ƴմϴ�.";
			tNo.setText("");	//	��� Ŭ����
			tPwd.setText("");	//	��� Ŭ����
			return false;
		} 

		// �ش� ���̵� ���� ���
		try {
			BufferedReader lbr = new BufferedReader(new FileReader(fName));
			lbr.readLine();			// ���а� �����ͼ� ����
			regNo = lbr.readLine();	//	���� �й� ������
			regPwd = lbr.readLine();//	��� ������
			System.out.println(" regNo = " + regNo + " regPwd = "+ regPwd);
			lbr.close();
			
			if(regNo.equals(inNo) && (regPwd.equals(inPwd)))	// ���̵� ����� ������
				return true;
			else {	//	���� �й��� ���� ������ �����Ƿ� ���Ե� ȸ�������� ����� �ٸ� ���
				error++;	//	������� Ƚ�� ������Ŵ
				msg = "��й�ȣ �����Դϴ�. [" + error + "ȸ]";
				tPwd.setText("");	//	��� Ŭ����

				return false;
			}
		} catch (Exception e) {
			System.out.println("checkPwd ����");
			System.out.println(e + " => ����");
			msg = "Exception �����Դϴ�.";
			return false;
		} 
	}
}

