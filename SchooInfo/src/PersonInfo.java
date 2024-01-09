import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class PersonInfo {
	String gubun;	//	1. ������, 2. �л�
	String no; 		//	����(90001), �й�(10001~)
	String name, addr;
	String pwd; 	//	�������� ȥ���ؼ� 8�ڸ�
	String birth;	//	���� 1999-00-00, 
	String gender;	//	���� : 1- ����, 2- ����
	String phone;	//	010-0000-0000
	
	String fName = null;
	String path = "D:\\mhwon\\src\\person\\";
	BufferedWriter bw;
	final String Last_S = "D:\\mhwon\\src\\person\\lastS.txt";	
	final String Last_T = "D:\\mhwon\\src\\person\\lastT.txt";	

	// 	�������� : ���θ��� �ϳ��� ���������� ������
	//	���ϸ��� ��ȣ.txt�� ����
	public String insertPerson() {
		//	���ο� ����/�й� ����
		no = getNewNo(gubun);

		fName = path + no + ".txt";

		boolean yes = checkNo(no);	// true : �����ϴ� ����, false : �������� �ʴ� ����
		if(yes) {
			System.out.println("�̹� �����ϴ� �й��Դϴ�.");
			return "";
		}

		writePerson(fName);
		
		return no;
	}
	
	//	�ߺ�Ȯ�� : �ش� ���� �й��� ������ true ����, ������ false ����
	public boolean checkNo(String no) {
		File f = new File(fName + no + ".txt");
		if(f.exists()) {	// �ش� ����(�� ����)�� �����ϴ��� Ȯ��
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
		
		if(gubun.equals("1"))	//	������ -> lastT.txt�� �о 90000 �ø��� ��ȣ ����
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
			System.out.println("getNewNo ����");
			System.out.println(e + " => ����");
		} finally {
			try {
				lbr.close();
				lbw.close();
			}catch (Exception e) {
				System.out.println(e + "finally  ����");
			}				
		}

		return String.valueOf(newNo);
	}
	
	public boolean updatePerson() {
		boolean ok = writePerson(path + no + ".txt");
		return ok;
	}
	
	public boolean writePerson(String fileName) {
//		String gubun;	//	1. ������, 2. �л�
//		String no; 		//	����(90001), �й�(10001) : �й� ������ ������ �� �����ؼ� ��������
//		String pwd; 	//	�������� ȥ���ؼ� 8�ڸ�
//		String name, addr;
//		String birth;	//	���� 1999-00-00, 
//		String gender;	//	���� : 1- ����, 2- ����
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
			System.out.println("writePerson => ����" + e);
			return false;
		}
		return true;
	}
}


