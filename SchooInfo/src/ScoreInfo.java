import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class ScoreInfo extends JFrame implements ActionListener {
	String name; // �̸�
	String gender; // ���� : 1- ����, 2- ����
	String phone; // 010-0000-0000
	String korScore;
	String engScore;
	String mathScore;
	List<String> score;

	String path = "D:\\mhwon\\src\\score\\";
//	String path = "F:\\Academy_CloudJava\\SchooInfo\\src\\score\\";
	BufferedReader br;

	// �������� : ���θ��� �ϳ��� ���������� ������
	// ���ϸ��� ��ȣ.txt�� ����
	JPanel p0 = new JPanel();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JPanel p7 = new JPanel();
	JPanel p8 = new JPanel();
	JPanel p9 = new JPanel();

	JTextField t1 = new JTextField(20);
	JTextField t2 = new JTextField(20);
	JTextField t3 = new JTextField(20);

	JTextField tNo = new JTextField(10);
	JTextField tName = new JTextField(10);
	JTextField tPhone = new JTextField(10);
	JTextField tGender = new JTextField(10);

	JButton btn1 = new JButton("��ȸ");
	JButton btn2 = new JButton("������ȸ");
	JButton btn3 = new JButton("����");
	JButton btn4 = new JButton("Ȩ����");

	String[] optionsToChoose = { "�����ϼ���          ", "1/1", "1/2", "2/1", "2/2", "3/1", "3/2" };
	JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
	int semester;

	String myNo;
	
	// �����Է� ȭ�����
	public ScoreInfo(String empNo) {
		p0.setLayout(new FlowLayout());
		p0.add(new JLabel("          �л�����           "));

		p1.setLayout(new FlowLayout());
		p1.add(new JLabel("  ��   ��"));
		
		p1.add(tNo);
		
		p1.add(btn1);

		p2.add(new JLabel("��  �� "));
		p2.add(tName);
		tName.setEnabled(false);

		
		p3.add(new JLabel("����ó"));
		p3.add(tPhone);
		tPhone.setEnabled(false);

		p4.add(new JLabel("��    ��"));
		p4.add(tGender);
		tGender.setEnabled(false);

		p5.add(new JLabel("��    ��"));
		p5.add(jComboBox);

		p6.add(new JLabel("��    ��"));
		p6.add(t1);

		p7.add(new JLabel("��    ��"));
		p7.add(t2);

		p8.add(new JLabel("��    ��"));
		p8.add(t3);

		p9.add(btn2);
		p9.add(btn3);
		p9.add(btn4);

		setLayout(new FlowLayout());
		add(p0);

		setLayout(new FlowLayout());
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		add(p7);
		add(p8);
		add(p9);

		btn1.addActionListener(this);	// ��ȸ ��ư
		btn2.addActionListener(this);
		btn3.addActionListener(this);	//	����
		btn4.addActionListener(this);	//	Ȩ����

		setSize(330, 470);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		myNo = empNo;

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// �����ư�� ���� �޼ҵ� ȣ��
		String no = tNo.getText();
		System.out.println("actionPerformed no = " + no);

		String str = e.getActionCommand();
		semester = jComboBox.getSelectedIndex();
		 if (str.equals("����")) {

			 //	�й��� �Է����� �ʾ��� ��
			 if(tNo.getText().equals("")) {
		         JOptionPane.showMessageDialog(this,  "������ �й��� �Է��ϼ���.");
				 return;
			 }
			 
			 insertScore(no);

			 if (semester == 1) {
	         score.set(0, t1.getText());
	         score.set(1, t2.getText());
	         score.set(2, t3.getText());
	       } else if (semester == 2) {
	         score.set(3, t1.getText());
	         score.set(4, t2.getText());
	         score.set(5, t3.getText());
	       } else if (semester == 3) {
	         score.set(6, t1.getText());
	         score.set(7, t2.getText());
	         score.set(8, t3.getText());
	       } else if (semester == 4) {
	         score.set(9, t1.getText());
	         score.set(10, t2.getText());
	         score.set(11, t3.getText());
	       } else if (semester == 5) {
	         score.set(12, t1.getText());
	         score.set(13, t2.getText());
	         score.set(14, t3.getText());
	       } else if (semester == 6) {
	         score.set(15, t1.getText());
	         score.set(16, t2.getText());
	         score.set(17, t3.getText());
	       }

	       try (BufferedWriter bw = new BufferedWriter(
	           new FileWriter(path + no + ".txt"))) {
	         System.out.println(path + no + ".txt");
	         for (String line : score) {
	           System.out.println("in for = " + line);
	           bw.write(line + "\n");
	         }
	         bw.close();
	         JOptionPane.showMessageDialog(this,  "����Ǿ����ϴ�.");
	         
	       } catch (IOException e1) {
	         e1.printStackTrace();
	       }
	     } // ����
		 else if(str.equals("��ȸ")) {
			 //	�й��� �Է����� �ʾ��� ��
			 if(tNo.getText().equals("")) {
		         JOptionPane.showMessageDialog(this,  "��ȸ�� �й��� �Է��ϼ���.");
				 return;
			 }
			 checkNo(no);
		 }
		 else if (str.equals("������ȸ")) {
			 //	�й��� �Է����� �ʾ��� ��
			 if(tNo.getText().equals("")) {
		         JOptionPane.showMessageDialog(this,  "��ȸ�� �й��� �Է��ϼ���.");
				 return;
			 }

			 new PrintScore(no);
		 }
		 else if (str.equals("Ȩ����")) {
			new StManager(myNo);
			dispose();
		}
	}
	
	public String insertScore(String no) {
	    boolean yes = checkNo(no); // true : �����ϴ� ����, false : �������� �ʴ� ����
        score = new ArrayList<String>(18);
	    File f = new File(path + no + ".txt");
	    
	    if((f.exists() == true) && (yes == true)){
	    	try (BufferedReader br = new BufferedReader(
	    			new FileReader(path + no + ".txt"))) {
	    		String line;
	    		int cLine = 0;

	    		while ((line = br.readLine()) != null) {
	    			cLine++;
	    			score.add(line);
	    		}
	    		br.close();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    } else {
	    	for (int i = 0; i < 18; i++) {
	        score.add(" ");
	    	}
	    }
	    return no;
	}
	
	// �ߺ�Ȯ�� : �ش� ���� �й��� ������ true ����, ������ false ����
	public boolean checkNo(String no) {
		String path2 = "D:\\mhwon\\src\\person\\" + no + ".txt";
		
		File f = new File(path2);
		System.out.println("path2 " + path2);

		if(f.exists() == false) {	// �ش� ���̵� ���� ���
			JOptionPane.showMessageDialog(this,  "�������� �ʴ� �й��Դϴ�.");
			return false;
		} 
			// �ش� ���̵� ���� ���
		try {
			BufferedReader lbr = new BufferedReader(new FileReader(path2));
			lbr.readLine();			// ���а� �����ͼ� ����
			lbr.readLine();			// �й� �����ͼ� ����
			lbr.readLine();			// ��� �����ͼ� ����
			tName.setText(lbr.readLine());	//	�̸� ������
			tPhone.setText(lbr.readLine());	//	����ó ������
			String gender = lbr.readLine();	//	���� ������ 1:����, 2:����
			
			if(gender.equals("1"))
				tGender.setText("����");
			else
				tGender.setText("����");

			lbr.close();
			return true;
		} catch (Exception e) {
			System.out.println("checkNo ���� : " + e);
			return false;
		} 
	}

}
