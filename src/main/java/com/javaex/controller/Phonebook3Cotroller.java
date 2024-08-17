package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.Dao.Phonebook3Dao;
import com.javaex.Vo.Phonebook3Vo;


@WebServlet("/gbc")
public class Phonebook3Cotroller extends HttpServlet {
	
	//필드
	private static final long serialVersionUID = 1L;
       
	//생성자
	
	//메서드 gs
	
	//메서드 일반
	
	// < controller 접수받는일> //
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//action 꺼내서 담기
		String action = request.getParameter("action");
		System.out.println(action);
		
		////////////////////// 리스트 ////////////////
		if("list".equals(action)) {
			System.out.println("list 확인용");
			
			Phonebook3Dao personDao = new Phonebook3Dao();
			List<Phonebook3Vo> personList = personDao.getPersonList();
			System.out.println(personList);
			
			//request에 list 주소 넣어주기
			request.setAttribute("personList", personList);
			
			// 포워드 ㄱ
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/listForm.jsp");
			rd.forward(request, response);
			
		//////////////////// 등록 //////////////
		}else if("insert".equals(action)) {
			System.out.println("insert문 확인");
			
			// 입력받은 파라미터 꺼내기 >> Dao로 값 보내주기
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			Phonebook3Vo personVo = new Phonebook3Vo(name, password, content);
			System.out.println(personVo);
			
			// Dao 메모리에 올려주기
			Phonebook3Dao phonebook3Dao = new Phonebook3Dao();
			
			phonebook3Dao.insertPerson(personVo);
			
			response.sendRedirect("http://localhost:8088/phonebook3/gbc?action=list");
			
		}else if("deleteform".equals(action)) {
			System.out.println("delete!!!form !!확인용");
			
			//바로 포워드 ㄱㄱ

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
			
		}else if("delete".equals(action)) {
			
			System.out.println("딜리트용");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			Phonebook3Dao phonebook3Dao = new Phonebook3Dao();
			
			boolean success = phonebook3Dao.deletePerson(no, password);
			
			
			if(success) {
				
				//리다이렉트 해주기
				response.sendRedirect("http://localhost:8088/phonebook3/gbc?action=list");
			}else {
				System.out.println("비번틀림");
				
				/// 여길 오또카지~오또카지~~
			}
			
			
			
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
