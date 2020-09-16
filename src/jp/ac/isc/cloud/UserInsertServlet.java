package jp.ac.isc.cloud;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/UserInsertServlet")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Connection users = null;
		try {
			//DB接続処理を呼び出す
			users = DBConnection.openConnection();
			String id = request.getParameter("insertId");
			String name = request.getParameter("insertName");
			String picture = request.getParameter("insertPicture");
			Statement state = users.createStatement();
			state.executeUpdate("INSERT INTO user_table VALUE('" + id + "','" + name + "','" + picture + "')");
			//DB切断処理を呼び出す
			DBConnection.closeConnection(users, state);
			response.sendRedirect("/select"); //UserSelectServletを呼び出す
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}