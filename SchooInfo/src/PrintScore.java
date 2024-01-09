import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.*;
import javax.swing.*;

public class PrintScore extends JFrame implements ActionListener {
	private String no;
	// 7. ������ȸȭ��

//	String path = "F:\\Academy_CloudJava\\SchooInfo\\src\\score";
	String path = "D:\\mhwon\\src\\score\\";
	String path2 = "D:\\mhwon\\src\\person\\";

	List<String> korScore = new ArrayList<String>();
	List<String> engScore = new ArrayList<String>();
	List<String> mathScore = new ArrayList<String>();

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JPanel p7 = new JPanel();
	JPanel p8 = new JPanel();

	JButton btn1 = new JButton("�ݱ�");

	Font f1 = new Font("HY�߰��", Font.BOLD, 40);
	Font f2 = new Font("���� ��� (����)", Font.BOLD, 15);

	// �й��� ���� ���������� �о���� �޼ҵ�
	public void ReadInformation(String no) {
//		try (BufferedReader br = new BufferedReader(
//				new FileReader("F:\\Academy_CloudJava\\SchooInfo\\src\\score\\" + no + ".txt"))) {
		
// 	***********
// 	�ش� �й��� ���������� ���� ��� ������ ���� ���������� �ִ��� Ȯ�� �� ������ " "�� ��� �����ϴ� ���� �߰�
//	�������� BufferedReader br�� close()�ϴ� ���� �߰�
		System.out.println(path + no + ".txt");
		File f = new File(path + no + ".txt");
		if(f.exists() == false) {
			for(int i = 0 ; i < 6 ; i++) {
				korScore.add(" ");
				engScore.add(" ");
				mathScore.add(" ");
			}
		} else {
		//	 ***********
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(path + no + ".txt"));
				String line;
				int cLine = 0;
	
				while ((line = br.readLine()) != null) {
					cLine++;
	
					if (cLine % 3 == 1) {
						korScore.add(line);
	
					} else if (cLine % 3 == 2) {
						engScore.add(line);
					} else if (cLine % 3 == 0) {
						mathScore.add(line);
					}
				}
				
	//			�������� BufferedReader br�� close()�ϴ� ���� �߰�
				br.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// �ش� ����/�й��� �̸��� �������� �޼ҵ�
	public String getName(String no) {
//		String fName = "F:\\Academy_CloudJava\\SchooInfo\\src\\person\\" + no + ".txt";
		String fName = path2 + no + ".txt";
		String name = "";
		
		try {
			BufferedReader lbr = new BufferedReader(new FileReader(fName));

			// 3�� readLine()�ؼ� �̸��� ������ => 1:����, 2:���̵�, 3: �н�����, 4:�̸�
			for (int i = 0; i < 3; i++)
				lbr.readLine();

			name = lbr.readLine();
			lbr.close();
		} catch (Exception e) {
			System.out.println("getName ���� : " + e);
		}
		return name;
	}

	// ������ȸȭ�� ������
	public PrintScore(String no) {
		this.no = no;
		String name = getName(no);

		JLabel Ti = new JLabel("�������� ������ȸ");
		p1.setLayout(new FlowLayout());
		p1.add(Ti);
		Ti.setFont(f1);
		JTextField Id = new JTextField("  " + no + "  ");
		JTextField Name = new JTextField("  " + name + "  ");

		p2.setLayout(new FlowLayout());
		p2.add(new JLabel("���̵�"));
		p2.add(Id);
		Id.setBackground(Color.LIGHT_GRAY);
		Id.setForeground(Color.black);
		Id.setBorder(BorderFactory.createLineBorder(Color.black));
		Id.setFont(f2);
		Id.setEditable(false);
		p2.add(new JLabel("        �̸�"));
		p2.add(Name);
		Name.setBackground(Color.LIGHT_GRAY);
		Name.setForeground(Color.black);
		Name.setBorder(BorderFactory.createLineBorder(Color.black));
		Name.setFont(f2);
		Name.setEditable(false);

		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("(�г� / �б�)"));

		p4.setLayout(new GridLayout(1, 0));
		p4.add(new JLabel("        �����"));
		p4.add(new JLabel("        1 / 1"));
		p4.add(new JLabel("        1 / 2"));
		p4.add(new JLabel("        2 / 1"));
		p4.add(new JLabel("        2 / 2"));
		p4.add(new JLabel("        3 / 1"));
		p4.add(new JLabel("        3 / 2"));

		p5.setLayout(new GridLayout(1, 0));
		p5.add(new JLabel("        ����"));

		ReadInformation(no);

		p5.add(new JLabel("        " + korScore.get(0)));
		p5.add(new JLabel("        " + korScore.get(1)));
		p5.add(new JLabel("        " + korScore.get(2)));
		p5.add(new JLabel("        " + korScore.get(3)));
		p5.add(new JLabel("        " + korScore.get(4)));
		p5.add(new JLabel("        " + korScore.get(5)));

		p6.setLayout(new GridLayout(1, 0));
		p6.add(new JLabel("        ����"));
		p6.add(new JLabel("        " + engScore.get(0)));
		p6.add(new JLabel("        " + engScore.get(1)));
		p6.add(new JLabel("        " + engScore.get(2)));
		p6.add(new JLabel("        " + engScore.get(3)));
		p6.add(new JLabel("        " + engScore.get(4)));
		p6.add(new JLabel("        " + engScore.get(5)));

		p7.setLayout(new GridLayout(1, 0));
		p7.add(new JLabel("        ����"));
		p7.add(new JLabel("        " + mathScore.get(0)));
		p7.add(new JLabel("        " + mathScore.get(1)));
		p7.add(new JLabel("        " + mathScore.get(2)));
		p7.add(new JLabel("        " + mathScore.get(3)));
		p7.add(new JLabel("        " + mathScore.get(4)));
		p7.add(new JLabel("        " + mathScore.get(5)));

		p8.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p8.add(btn1, "East");
		btn1.addActionListener(this);
		btn1.setBackground(Color.WHITE);

		setLayout(new GridLayout(8, 0));
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		add(p7);
		add(p8);

//	**********************
//	7. ���� ������ȸ ���� 7. ��ȣ ����
		setTitle("���� ������ȸ");
		setSize(700, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}