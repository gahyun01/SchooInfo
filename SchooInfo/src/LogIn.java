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
	JLabel label = new JLabel("로그인", JLabel.CENTER);
	JPanel pnl = new JPanel();
//	JPanel pnl2 = new JPanel();

//	JLabel lgbn = new JLabel("구 분", JLabel.CENTER);
	JLabel lNo = new JLabel("교번/학번", JLabel.CENTER);
	JLabel lPwd = new JLabel("비밀번호", JLabel.CENTER);

	JButton bReq = new JButton("회원가입");
	JButton bLog = new JButton("로그인");
	
//	ButtonGroup bg = new ButtonGroup();
//	JRadioButton rb = new JRadioButton("교직원", true);
//	JRadioButton rb2 = new JRadioButton("학 생", false);

	JTextField tNo = new JTextField("", 10);	// 아이디
	TextField tPwd = new TextField("", 8);		// 비밀번호

	String msg = "";
	String chkNo = "";
	int error = 0;
	
	
	public LogIn() {
		//	로그인 화면 띄움
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

		if(str.equals("로그인")) {
			// 로그인 정보확인
			String no = tNo.getText();
			String pwd = tPwd.getText();
			boolean ok = false;

			System.out.println("1 chkNo = " + chkNo + " no = " +  no + " error = " + error);
			
			if((error >= 5) && chkNo.equals(no)) {
				JOptionPane.showMessageDialog(this,  "비밀번호 5회 이상 오류입니다.");
				return;
			}
			
			if(chkNo.equals(no) == false) {	// 새로 입력한 아이디라면
				chkNo = no;	//	비번5회 오류 확인할 아이디
				error = 0;
			}
			
			System.out.println("2 chkNo = " + chkNo + " no = " +  no + " error = " + error);
				
			ok = checkPwd(no, pwd);
			if(!ok) {
				//비번 재입력 요청
				JOptionPane.showMessageDialog(this,  msg);
			}
			else {
				//	교직원인지 학생인지 확인
				char first = no.charAt(0);
				if(first == '9') // 교직원 
					new StManager(no);
				else 	// 학생
					new StManager2(no);

				//	로그인 화면 닫음 
				dispose();
			}
		}
		else if (str.equals("회원가입")) {	//	회원가입 버튼
			dispose();
			new MemberJoin("00000");
		}
	}
	
	public boolean checkPwd(String inNo, String inPwd) {
		String fName = "D:\\mhwon\\src\\person\\" + inNo + ".txt";
		
		String regNo = null;
		String regPwd = null;

		//	로그인 정보확인
		File f = new File(fName);
	
		// 해당 파일(및 폴더)가 존재하는지 확인해서 있으면 비번 비교함
		if(f.exists() == false) {	// 해당 아이디가 없을 경우
			msg = "해당 아이디는 등록된 아이디가 아닙니다.";
			tNo.setText("");	//	비번 클리어
			tPwd.setText("");	//	비번 클리어
			return false;
		} 

		// 해당 아이디가 있을 경우
		try {
			BufferedReader lbr = new BufferedReader(new FileReader(fName));
			lbr.readLine();			// 구분값 가져와서 버림
			regNo = lbr.readLine();	//	교번 학번 가져옴
			regPwd = lbr.readLine();//	비번 가져옴
			System.out.println(" regNo = " + regNo + " regPwd = "+ regPwd);
			lbr.close();
			
			if(regNo.equals(inNo) && (regPwd.equals(inPwd)))	// 아이디 비번이 맞으면
				return true;
			else {	//	교번 학번에 대한 파일은 있으므로 가입된 회원이지만 비번이 다를 경우
				error++;	//	비번오류 횟수 증가시킴
				msg = "비밀번호 오류입니다. [" + error + "회]";
				tPwd.setText("");	//	비번 클리어

				return false;
			}
		} catch (Exception e) {
			System.out.println("checkPwd 오류");
			System.out.println(e + " => 오류");
			msg = "Exception 오류입니다.";
			return false;
		} 
	}
}

