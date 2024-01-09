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
	// 7. 성적조회화면

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

	JButton btn1 = new JButton("닫기");

	Font f1 = new Font("HY견고딕", Font.BOLD, 40);
	Font f2 = new Font("맑은 고딕 (본문)", Font.BOLD, 15);

	// 학번을 통해 성적정보를 읽어오는 메소드
	public void ReadInformation(String no) {
//		try (BufferedReader br = new BufferedReader(
//				new FileReader("F:\\Academy_CloudJava\\SchooInfo\\src\\score\\" + no + ".txt"))) {
		
// 	***********
// 	해당 학번의 성적파일이 없을 경우 에러가 나서 성적파일이 있는지 확인 후 없으면 " "로 모두 셋팅하는 로직 추가
//	마지막에 BufferedReader br을 close()하는 로직 추가
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
				
	//			마지막에 BufferedReader br을 close()하는 로직 추가
				br.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 해당 교번/학번의 이름을 가져오는 메소드
	public String getName(String no) {
//		String fName = "F:\\Academy_CloudJava\\SchooInfo\\src\\person\\" + no + ".txt";
		String fName = path2 + no + ".txt";
		String name = "";
		
		try {
			BufferedReader lbr = new BufferedReader(new FileReader(fName));

			// 3번 readLine()해서 이름을 가져옴 => 1:구분, 2:아이디, 3: 패스워드, 4:이름
			for (int i = 0; i < 3; i++)
				lbr.readLine();

			name = lbr.readLine();
			lbr.close();
		} catch (Exception e) {
			System.out.println("getName 오류 : " + e);
		}
		return name;
	}

	// 성적조회화면 생성자
	public PrintScore(String no) {
		this.no = no;
		String name = getName(no);

		JLabel Ti = new JLabel("교직원용 성적조회");
		p1.setLayout(new FlowLayout());
		p1.add(Ti);
		Ti.setFont(f1);
		JTextField Id = new JTextField("  " + no + "  ");
		JTextField Name = new JTextField("  " + name + "  ");

		p2.setLayout(new FlowLayout());
		p2.add(new JLabel("아이디"));
		p2.add(Id);
		Id.setBackground(Color.LIGHT_GRAY);
		Id.setForeground(Color.black);
		Id.setBorder(BorderFactory.createLineBorder(Color.black));
		Id.setFont(f2);
		Id.setEditable(false);
		p2.add(new JLabel("        이름"));
		p2.add(Name);
		Name.setBackground(Color.LIGHT_GRAY);
		Name.setForeground(Color.black);
		Name.setBorder(BorderFactory.createLineBorder(Color.black));
		Name.setFont(f2);
		Name.setEditable(false);

		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(new JLabel("(학년 / 학기)"));

		p4.setLayout(new GridLayout(1, 0));
		p4.add(new JLabel("        과목명"));
		p4.add(new JLabel("        1 / 1"));
		p4.add(new JLabel("        1 / 2"));
		p4.add(new JLabel("        2 / 1"));
		p4.add(new JLabel("        2 / 2"));
		p4.add(new JLabel("        3 / 1"));
		p4.add(new JLabel("        3 / 2"));

		p5.setLayout(new GridLayout(1, 0));
		p5.add(new JLabel("        국어"));

		ReadInformation(no);

		p5.add(new JLabel("        " + korScore.get(0)));
		p5.add(new JLabel("        " + korScore.get(1)));
		p5.add(new JLabel("        " + korScore.get(2)));
		p5.add(new JLabel("        " + korScore.get(3)));
		p5.add(new JLabel("        " + korScore.get(4)));
		p5.add(new JLabel("        " + korScore.get(5)));

		p6.setLayout(new GridLayout(1, 0));
		p6.add(new JLabel("        영어"));
		p6.add(new JLabel("        " + engScore.get(0)));
		p6.add(new JLabel("        " + engScore.get(1)));
		p6.add(new JLabel("        " + engScore.get(2)));
		p6.add(new JLabel("        " + engScore.get(3)));
		p6.add(new JLabel("        " + engScore.get(4)));
		p6.add(new JLabel("        " + engScore.get(5)));

		p7.setLayout(new GridLayout(1, 0));
		p7.add(new JLabel("        수학"));
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
//	7. 나의 성적조회 에서 7. 번호 지움
		setTitle("나의 성적조회");
		setSize(700, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}