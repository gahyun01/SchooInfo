import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class PersonInfo {
	String gubun;	//	1. 교지원, 2. 학생
	String no; 		//	교번(90001), 학번(10001~)
	String name, addr;
	String pwd; 	//	영문숫자 혼합해서 8자리
	String birth;	//	생일 1999-00-00, 
	String gender;	//	성별 : 1- 남성, 2- 여성
	String phone;	//	010-0000-0000
	
	String fName = null;
	String path = "D:\\mhwon\\src\\person\\";
	BufferedWriter bw;
	final String Last_S = "D:\\mhwon\\src\\person\\lastS.txt";	
	final String Last_T = "D:\\mhwon\\src\\person\\lastT.txt";	

	// 	정보저장 : 개인마다 하나의 정보파일을 생성함
	//	파일명은 번호.txt로 생성
	public String insertPerson() {
		//	새로운 교번/학번 얻어옴
		no = getNewNo(gubun);

		fName = path + no + ".txt";

		boolean yes = checkNo(no);	// true : 존재하는 정보, false : 존재하지 않는 정보
		if(yes) {
			System.out.println("이미 존재하는 학번입니다.");
			return "";
		}

		writePerson(fName);
		
		return no;
	}
	
	//	중복확인 : 해당 교번 학번이 있으면 true 리턴, 없으면 false 리턴
	public boolean checkNo(String no) {
		File f = new File(fName + no + ".txt");
		if(f.exists()) {	// 해당 파일(및 폴더)가 존재하는지 확인
			return true;
		}
		else return false;
		
	}

	public String getNewNo(String gubun) {
		BufferedReader lbr = null;
		BufferedWriter lbw = null;
		String fName = null;
		String lastNo = null;
		int newNo = 0;
		
		if(gubun.equals("1"))	//	교직원 -> lastT.txt를 읽어서 90000 시리즈 번호 리턴
			fName = Last_T;
		else
			fName = Last_S;

		try {
			lbr = new BufferedReader(new FileReader(fName));
		
			lastNo = lbr.readLine();
			newNo = Integer.parseInt(lastNo);
			newNo++;
			lbr.close();

			lbw = new BufferedWriter(new FileWriter(fName));	
			lbw.write(newNo + "\n");
			lbw.close();

		} catch (Exception e) {
			System.out.println("getNewNo 오류");
			System.out.println(e + " => 오류");
		} finally {
			try {
				lbr.close();
				lbw.close();
			}catch (Exception e) {
				System.out.println(e + "finally  오류");
			}				
		}

		return String.valueOf(newNo);
	}
	
	public boolean updatePerson() {
		boolean ok = writePerson(path + no + ".txt");
		return ok;
	}
	
	public boolean writePerson(String fileName) {
//		String gubun;	//	1. 교지원, 2. 학생
//		String no; 		//	교번(90001), 학번(10001) : 학번 교번은 저장할 때 생성해서 전달해줌
//		String pwd; 	//	영문숫자 혼합해서 8자리
//		String name, addr;
//		String birth;	//	생일 1999-00-00, 
//		String gender;	//	성별 : 1- 남성, 2- 여성
//		String phone;	//	010-0000-0000
		try {
//			System.out.println("fileName : " + fileName);
			bw = new BufferedWriter(new FileWriter(fileName));	
			bw.write(gubun + "\n");
			bw.write(no + "\n");
			bw.write(pwd + "\n");
			bw.write(name + "\n");
			bw.write(birth + "\n");
			bw.write(gender + "\n");
			bw.write(phone + "\n");
			bw.write(addr + "\n");
			
			bw.close();
				
		} catch (Exception e) {
			System.out.println("writePerson => 오류" + e);
			return false;
		}
		return true;
	}
}


