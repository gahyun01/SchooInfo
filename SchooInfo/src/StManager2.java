
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class StManager2 extends JFrame implements ActionListener {

	// 6번 화면 만들기

	JPanel p = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JLabel lab = new JLabel("학생종합정보시스템");
	JButton btn1 = new JButton("마이페이지");
	JButton btn2 = new JButton("나의성적조회");
	JButton btn3 = new JButton("종 료");

	Font f1 = new Font("HY견고딕", Font.BOLD, 40);
	Font f2 = new Font("맑은 고딕 (본문)", Font.BOLD, 15);
	
	String myNo;

	public StManager2(String no) {

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

		setSize(640, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);

		myNo = no;
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		if(str.equals("마이페이지")) 
			new MyPage(myNo);
		else if(str.equals("나의성적조회"))
			new PrintScore2(myNo);
		else if(str.equals("종 료"))
			System.exit(0);
		
		dispose();
	}
}
