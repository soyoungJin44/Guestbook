package com.javaex.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.javaex.Vo.Phonebook3Vo;

public class Phonebook3Dao {

	// 필드
	// 필드

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/phoneBook_DB";
	private String id = "phonebook";
	private String pw = "phonebook";

	List<Phonebook3Vo> personList = new ArrayList<Phonebook3Vo>();

	// 생성자

	// 메서드 gs

	// 메서드 일반

	// 1.DB연결 메서드

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 2.자원정리 메서드
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// ***********************//////////////////////

	// 리스트 출력 만들기

	public List<Phonebook3Vo> getPersonList() {

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 실행

			String query = "";

			query += " select  person_id ";
			query += " ,name ";
			query += " ,password ";
			query += " ,content ";
			query += " ,reg_date ";
			query += " from guestbook ";

			// 바인딩

			pstmt = conn.prepareStatement(query);

			// 실행

			rs = pstmt.executeQuery();

			// 4. 결과처리

			while (rs.next()) {
				int id = rs.getInt("person_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String date = rs.getString("reg_date");

				Phonebook3Vo personVo = new Phonebook3Vo(id, name, password, content, date);
				System.out.println(personVo);
//				System.out.println("========");

				personList.add(personVo);

			}

		} catch (SQLException e) {

			System.out.println("error" + e);

		}

		this.close();
		return personList;

	}

	/////////// 등록용(insert)

	public int insertPerson(Phonebook3Vo personVo) {

		this.getConnection();
		int count = -1;

		try {

			// 3. SQL문 준비 / 바인딩 실행

			String query = "";
			
			query += " insert into guestbook(name,password, content) ";
			query += " values( ?, ?,? ) ";

			// 바인딩
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getPassword());
			pstmt.setString(3, personVo.getContent());
			
			// 실행
			
			count = pstmt.executeUpdate();
			
			// 4. 결과처리

		} catch (SQLException e) {

			System.out.println("error" + e);

		}
		
		this.close();
		return count;
		

	}
	
	
	/////////////////// delete
	public boolean deletePerson(int no, String password) {
		
		this.getConnection();
		int count = -1;
		boolean success = false;
		
		try {

			// 3. SQL문 준비 / 바인딩 실행

			String query = "";
			
			query += " delete from guestbook ";
			query += " where person_id = ? ";
			query += " and password = ? ";
			

			// 바인딩
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			pstmt.setString(2,password);
			
			
			// 실행
			
			count = pstmt.executeUpdate();
			System.out.println("삭제완료용");
			
			if(count > 0) {
				success = true;
			}
			
			// 4. 결과처리
			
			System.out.println(count);

		} catch (SQLException e) {

			System.out.println("error" + e);

		}
		
		this.close();
		return success;
		
	}

}
