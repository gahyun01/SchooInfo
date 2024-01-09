import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextField;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//	2. ȸ������ ȭ��
public class MemberJoin extends JFrame implements ActionListener {
	JLabel label = new JLabel("ȸ������", JLabel.CENTER);
	JPanel pnl = new JPanel();
	JButton btn = new JButton("����");
	JButton btn2 = new JButton("�α���");
	JButton btn3 = new JButton("�ʱ�ȭ");
	JButton btn4 = new JButton("Ȩ����");

	JPanel pnl1 = new JPanel();	//	������ ��ư �߰�
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();

	JPanel pnl4 = new JPanel();
	JPanel pnl5 = new JPanel();
	JPanel pnl6 = new JPanel();

	ButtonGroup bg = new ButtonGroup();
	JRadioButton rb = new JRadioButton("������", true);
	JRadioButton rb2 = new JRadioButton("�� ��", false);

	ButtonGroup bg2 = new ButtonGroup();
	JRadioButton rb21 = new JRadioButton("�� ��", true);
	JRadioButton rb22 = new JRadioButton("�� ��", false);

	JTextField tNo = new JTextField("", 10);	// ���̵�
	TextField tPwd = new TextField("", 8);		// ��й�ȣ
	JTextField tName = new JTextField("", 10);			// �̸�
	JTextField tBirth = new JTextField("", 10);			// �������
	JTextField tPhone = new JTextField();				// ����ó
	JTextField tAddr = new JTextField();				// �ּ�

	String gubun;	//	1. ������, 2. �л�
	String name, pwd, addr;
	String birth;	//	���� 1999-00-00
	String gender;	//	���� : 1- ����, 2- ����
	String phone;	//	010-0000-0000
	PersonInfo person = new PersonInfo();

	
	final String[] LABELS = {"�� ��", "���̵�", "��й�ȣ", "�� ��", "�������", "�� ��", "����ó", "�� ��"};
	String myNo;	// �������� ���� ������ ��ȣ

	public MemberJoin(String no) {
		setLayout(new BorderLayout(10, 30));
		pnl.setLayout(new BorderLayout(10, 30));
		pnl2.setLayout(new GridLayout(8, 1));
		pnl3.setLayout(new GridLayout(8, 1));
		
		add(label, BorderLayout.NORTH);
		add(pnl, BorderLayout.CENTER);
		add(pnl1, BorderLayout.SOUTH );

		pnl.add(pnl2, BorderLayout.WEST);
		pnl.add(pnl3, BorderLayout.CENTER);

		pnl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnl1.add(btn);	//	�����ư
		pnl1.add(btn2);	//	�α��ι�ư
		pnl1.add(btn3);	//	�ʱ�ȭ��ư
		pnl1.add(btn4);	//	Ȩ���ι�ư

		label.setForeground(Color.BLUE);
		Font fnt = new Font("Serif", Font.BOLD, 20);
		label.setFont(fnt);
		
		//	"�� ��", "���̵�", "��й�ȣ", "�� ��", "�������", "�� ��", "����ó", "�� ��" Label setting
		for (String str : LABELS) {
			pnl2.add(new JLabel(str, JLabel.RIGHT));
			
		}

		pnl3.add(pnl4);	//	����
		pnl4.setLayout(new FlowLayout(FlowLayout.LEFT));
		bg.add(rb);
		bg.add(rb2);
		pnl4.add(rb);
		pnl4.add(rb2);
		
		pnl3.add(tNo);			//	���̵� - ���� �޾Ƽ� ��
		tNo.setEnabled(false);

		pnl3.add(tPwd);		//	��й�ȣ
		tPwd.setEchoChar('*');
		
		pnl3.add(tName);	// �̸�
		pnl3.add(tBirth);	//	�������
		
		pnl3.add(pnl6);	//	����
		pnl6.setLayout(new FlowLayout(FlowLayout.LEFT));
		bg2.add(rb21);
		bg2.add(rb22);
		pnl6.add(rb21);
		pnl6.add(rb22);

		pnl3.add(tPhone);	//	����ó
		pnl3.add(tAddr);	//	�ּ�
		
		btn.addActionListener(this);	//	����
		btn2.addActionListener(this);	//	�α���
		btn3.addActionListener(this);	//	�ʱ�ȭ
		btn4.addActionListener(this);	//	Ȩ����

		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		myNo = no;
		//	�ű��Է��̸� - ���������� �л����� ������� �ű��Է��̸� Ȩ���� ��ư ��Ȱ��ȭ
		if(myNo.equals("00000")) 
			btn4.setEnabled(false);
		else	//	�ű��Է��� �ƴϸ� �α��� ��ư ��Ȱ��ȭ
			btn2.setEnabled(false);
			
		System.out.println("ȸ�������� myNo = " + myNo);
	}


	public void actionPerformed(ActionEvent e) {
		// �����ư�� ���� �޼ҵ� ȣ��
//		String gubun;	//	1. ������, 2. �л�
//		String no; 		//	����(90001), �й�(10001) : �й� ������ ������ �� �����ؼ� ��������
//		String pwd; 	//	�������� ȥ���ؼ� 8�ڸ�
//		String name, addr;
//		String birth;	//	���� 1999-00-00, 
//		String gender;	//	���� : 1- ����, 2- ����
//		String phone;	//	010-0000-0000

		String str = e.getActionCommand();
		String rtnNo = null;
		
		if(str.equals("����")) {
			//	���а� ��������
			if(rb.isSelected())   
				person.gubun = "1";	// ������
			else
				person.gubun = "2";	// �л�

			//	����� �̸��� �ݵ�� �־�� ��
			if(tPwd.getText().equals("")) {
				JOptionPane.showMessageDialog(this,  "��й�ȣ�� �־��ּ���");
				return;
			}
				
			person.pwd = tPwd.getText();

			if(tName.getText().equals("")) {
				JOptionPane.showMessageDialog(this,  "�̸��� �־��ּ���");
				return;
			}
			person.name = tName.getText();
			person.birth = tBirth.getText();

			//	���� ��������
			if(rb21.isSelected())   
				person.gender = "1";	// ����
			else
				person.gender = "2";	// ����
			
			person.phone = tPhone.getText();
			person.addr = tAddr.getText();
			
			rtnNo = person.insertPerson();
			tNo.setText(rtnNo);	// ����, �й� ��������
			
			//	�л� ���� �����Է��̸� myNo�� �ο����� �л���ȣ ����, �������̸� ������ �Ѿ�� myNo ����
			if(person.gubun.equals("2") && myNo.equals("00000"))
				myNo = rtnNo;
			
			JOptionPane.showMessageDialog(this,  "����Ǿ����ϴ�.");
		}
		else if(str.equals("�α���")) {
			System.out.println("�α��� ���� : " + myNo);
			dispose();
			new LogIn();
		}
		else if(str.equals("�ʱ�ȭ")) {
			rb.setSelected(true);	//	���� : ���������� ����
			tNo.setText("");		// ���̵�
			tPwd.setText("");		// ��й�ȣ
			tName.setText("");		// �̸�
			tBirth.setText("");		// �������
			rb21.setSelected(true);	//	���� : �������� ����
			tPhone.setText("");		// ����ó
			tAddr.setText("");		// �ּ�
		}
		else if(str.equals("Ȩ����")) {
			System.out.println("Ȩ���� ���� : " + myNo);
			dispose();
			new StManager(myNo);
		}
	}
}
