
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class StManager extends JFrame implements ActionListener {

	JPanel p = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JButton btn1 = new JButton("성적입력");
	JButton btn2 = new JButton("학생정보조회");
	JButton btn3 = new JButton("종 료");

	JLabel lab = new JLabel("교직원용 학생관리");

	Font f1 = new Font("HY견고딕", Font.BOLD, 40);
	Font f2 = new Font("맑은 고딕 (본문)", Font.BOLD, 15);
	
	String myNo;

	public StManager(String no) {

		// 3번 화면 만들기

		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(p3);
		p3.add(btn3);

		p4.setLayout(new GridLayout(2, 0));
		p4.add(new JLabel(" "));
		p4.add(p3);

		p2.setLayout(new FlowLayout());
		p2.add(lab);
		lab.setFont(f1);

		p.setLayout(new FlowLayout());
		p.add(btn1);
		btn1.setPreferredSize(new Dimension(200, 100));
		btn1.setFont(f2);
		p.add(new JLabel("	          "));
		p.add(btn2);
		btn2.setPreferredSize(new Dimension(200, 100));
		btn2.setFont(f2);

		setLayout(new GridLayout(3, 0));
		add(p2);
		add(p);
		add(p4);

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);

		setSize(640, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		myNo = no;

	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		if(str.equals("성적입력")) {
			new ScoreInfo(myNo);
		}
		else if(str.equals("학생정보조회"))
			new PrintInfo(myNo);
		else if(str.equals("종 료"))
			System.exit(0);
		
		dispose();

	}
}
