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

//	2. 회원가입 화면
public class MemberJoin extends JFrame implements ActionListener {
	JLabel label = new JLabel("회원가입", JLabel.CENTER);
	JPanel pnl = new JPanel();
	JButton btn = new JButton("저장");
	JButton btn2 = new JButton("로그인");
	JButton btn3 = new JButton("초기화");
	JButton btn4 = new JButton("홈으로");

	JPanel pnl1 = new JPanel();	//	마지막 버튼 추가
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();

	JPanel pnl4 = new JPanel();
	JPanel pnl5 = new JPanel();
	JPanel pnl6 = new JPanel();

	ButtonGroup bg = new ButtonGroup();
	JRadioButton rb = new JRadioButton("교직원", true);
	JRadioButton rb2 = new JRadioButton("학 생", false);

	ButtonGroup bg2 = new ButtonGroup();
	JRadioButton rb21 = new JRadioButton("남 성", true);
	JRadioButton rb22 = new JRadioButton("여 성", false);

	JTextField tNo = new JTextField("", 10);	// 아이디
	TextField tPwd = new TextField("", 8);		// 비밀번호
	JTextField tName = new JTextField("", 10);			// 이름
	JTextField tBirth = new JTextField("", 10);			// 생년월일
	JTextField tPhone = new JTextField();				// 연락처
	JTextField tAddr = new JTextField();				// 주소

	String gubun;	//	1. 교직원, 2. 학생
	String name, pwd, addr;
	String birth;	//	생일 1999-00-00
	String gender;	//	성별 : 1- 남성, 2- 여성
	String phone;	//	010-0000-0000
	PersonInfo person = new PersonInfo();

	
	final String[] LABELS = {"구 분", "아이디", "비밀번호", "이 름", "생년월일", "성 별", "연락처", "주 소"};
	String myNo;	// 교직원일 때의 교직원 번호

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
		pnl1.add(btn);	//	저장버튼
		pnl1.add(btn2);	//	로그인버튼
		pnl1.add(btn3);	//	초기화버튼
		pnl1.add(btn4);	//	홈으로버튼

		label.setForeground(Color.BLUE);
		Font fnt = new Font("Serif", Font.BOLD, 20);
		label.setFont(fnt);
		
		//	"구 분", "아이디", "비밀번호", "이 름", "생년월일", "성 별", "연락처", "주 소" Label setting
		for (String str : LABELS) {
			pnl2.add(new JLabel(str, JLabel.RIGHT));
			
		}

		pnl3.add(pnl4);	//	구분
		pnl4.setLayout(new FlowLayout(FlowLayout.LEFT));
		bg.add(rb);
		bg.add(rb2);
		pnl4.add(rb);
		pnl4.add(rb2);
		
		pnl3.add(tNo);			//	아이디 - 정보 받아서 옴
		tNo.setEnabled(false);

		pnl3.add(tPwd);		//	비밀번호
		tPwd.setEchoChar('*');
		
		pnl3.add(tName);	// 이름
		pnl3.add(tBirth);	//	생년월일
		
		pnl3.add(pnl6);	//	성별
		pnl6.setLayout(new FlowLayout(FlowLayout.LEFT));
		bg2.add(rb21);
		bg2.add(rb22);
		pnl6.add(rb21);
		pnl6.add(rb22);

		pnl3.add(tPhone);	//	연락처
		pnl3.add(tAddr);	//	주소
		
		btn.addActionListener(this);	//	저장
		btn2.addActionListener(this);	//	로그인
		btn3.addActionListener(this);	//	초기화
		btn4.addActionListener(this);	//	홈으로

		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		myNo = no;
		//	신규입력이면 - 교직원인지 학생인지 상관없이 신규입력이면 홈으로 버튼 비활성화
		if(myNo.equals("00000")) 
			btn4.setEnabled(false);
		else	//	신규입력이 아니면 로그인 버튼 비활성화
			btn2.setEnabled(false);
			
		System.out.println("회원가입의 myNo = " + myNo);
	}


	public void actionPerformed(ActionEvent e) {
		// 저장버튼에 따라 메소드 호출
//		String gubun;	//	1. 교지원, 2. 학생
//		String no; 		//	교번(90001), 학번(10001) : 학번 교번은 저장할 때 생성해서 전달해줌
//		String pwd; 	//	영문숫자 혼합해서 8자리
//		String name, addr;
//		String birth;	//	생일 1999-00-00, 
//		String gender;	//	성별 : 1- 남성, 2- 여성
//		String phone;	//	010-0000-0000

		String str = e.getActionCommand();
		String rtnNo = null;
		
		if(str.equals("저장")) {
			//	구분값 가져오기
			if(rb.isSelected())   
				person.gubun = "1";	// 교직원
			else
				person.gubun = "2";	// 학생

			//	비번과 이름은 반드시 넣어야 함
			if(tPwd.getText().equals("")) {
				JOptionPane.showMessageDialog(this,  "비밀번호를 넣어주세요");
				return;
			}
				
			person.pwd = tPwd.getText();

			if(tName.getText().equals("")) {
				JOptionPane.showMessageDialog(this,  "이름을 넣어주세요");
				return;
			}
			person.name = tName.getText();
			person.birth = tBirth.getText();

			//	성별 가져오기
			if(rb21.isSelected())   
				person.gender = "1";	// 남성
			else
				person.gender = "2";	// 여성
			
			person.phone = tPhone.getText();
			person.addr = tAddr.getText();
			
			rtnNo = person.insertPerson();
			tNo.setText(rtnNo);	// 교번, 학번 셋팅해줌
			
			//	학생 최초 정보입력이면 myNo에 부여받은 학생번호 저장, 교직원이면 기존에 넘어온 myNo 유지
			if(person.gubun.equals("2") && myNo.equals("00000"))
				myNo = rtnNo;
			
			JOptionPane.showMessageDialog(this,  "저장되었습니다.");
		}
		else if(str.equals("로그인")) {
			System.out.println("로그인 누름 : " + myNo);
			dispose();
			new LogIn();
		}
		else if(str.equals("초기화")) {
			rb.setSelected(true);	//	구분 : 교직원으로 셋팅
			tNo.setText("");		// 아이디
			tPwd.setText("");		// 비밀번호
			tName.setText("");		// 이름
			tBirth.setText("");		// 생년월일
			rb21.setSelected(true);	//	성별 : 남성으로 셋팅
			tPhone.setText("");		// 연락처
			tAddr.setText("");		// 주소
		}
		else if(str.equals("홈으로")) {
			System.out.println("홈으로 누름 : " + myNo);
			dispose();
			new StManager(myNo);
		}
	}
}
