package member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import member.service.IMemberService;
import member.service.MemberServiceImpl;
import member.vo.MemberVO;

@WebServlet("/insertMember.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10,
		maxFileSize = 1024 * 1024 * 30,
		maxRequestSize = 1024 * 1024 * 100)
public class InsertMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String uploadPath = "D:/D_Other/uploadFiles";

		File f = new File(uploadPath);

		if (!f.exists()) {
			f.mkdirs();
		}
		
		String id = request.getParameter("userid");
		String pw = request.getParameter("userpw");
		String name = request.getParameter("username");
		String tel = request.getParameter("usertel");
		String addr = request.getParameter("useraddr");
		
		MemberVO vo = new MemberVO();
		vo.setMem_id(id);
		vo.setMem_pass(pw);
		vo.setMem_name(name);
		vo.setMem_tel(tel);
		vo.setMem_addr(addr);
		
		// 파일데이터 처리
		String fileName = "";

		for (Part part : request.getParts()) {
			fileName = extractFileName(part);
			if (!"".equals(fileName)) { // 파일인지 검사
				vo.setMem_photo(fileName);
				
				try {
					part.write(uploadPath + File.separator + fileName); // 파일저장
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		IMemberService service = MemberServiceImpl.getInstance();
		service.insertMember(vo);
		
		response.sendRedirect(request.getContextPath() + "/memberList.do");
	}

	private String extractFileName(Part part) {
		String fileName = "";

		String headerValue = part.getHeader("content-disposition");

		String[] items = headerValue.split(";");
		for (String item : items) {
			if (item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}

		return fileName;
	}
}
