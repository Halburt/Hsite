package com.halburt.hsite.jwt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.halburt.hsite.jwt.TokenMgr;
import com.halburt.hsite.jwt.config.Constant;
import com.halburt.hsite.jwt.model.CommonResult;
import com.halburt.hsite.jwt.model.SubjectModel;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7134187416259332571L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SubjectModel sub = new SubjectModel(1254, "admin");
		String token = TokenMgr.createJWT(Constant.JWT_ID, TokenMgr.generalSubject(sub), Constant.JWT_TTL);
		CommonResult commonResult = new CommonResult(Constant.RESCODE_SUCCESS, null, "成功", token);
		
		PrintWriter printWriter = response.getWriter();
		printWriter.print(commonResult.general());
		printWriter.flush();
		printWriter.close();
	}

}
