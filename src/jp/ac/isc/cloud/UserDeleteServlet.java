package jp.ac.isc.cloud;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(asyncSupported = true, urlPatterns = { "/UserDeleteSeervlet" })
public class UserDeleteServlet extends HttpServlet {
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
			String id = request.getParameter("deleteId");
			Statement state = users.createStatement();
			state.executeUpdate("DELETE FROM user_table WHERE id='" + id + "'");
			//DB切断処理を呼び出す
			DBConnection.closeConnection(users, state);
			response.sendRedirect("/select"); //UserSelectServletを呼び出す
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
