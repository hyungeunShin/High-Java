package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.IMemberService;
import member.service.MemberServiceImpl;
import member.vo.MemberVO;

@WebServlet("/memberdetail.do")
public class Memberdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("memid");
		String update = request.getParameter("isupdate");
		
		System.out.println(update);
		
		IMemberService service = MemberServiceImpl.getInstance();
		MemberVO vo = service.getMember(id);
		
		request.setAttribute("member", vo);
		
		if(update != null) {
			request.getRequestDispatcher("/updatemember.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/memberdetail.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
