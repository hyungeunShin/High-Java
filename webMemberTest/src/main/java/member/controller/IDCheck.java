package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.service.IMemberService;
import member.service.MemberServiceImpl;

@WebServlet("/IDCheck.do")
public class IDCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		IMemberService service = MemberServiceImpl.getInstance();
		int cnt = service.checkID(id);
		
		String msg = "";
		if(cnt > 0) {
			msg = "ID중복";
		} else {
			msg = "사용가능한 ID";
		}
		
		Gson gson = new Gson();
		String res = gson.toJson(msg);
		response.getWriter().write(res);
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
