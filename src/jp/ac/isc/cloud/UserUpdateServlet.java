package jp.ac.isc.cloud;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
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
			String id = request.getParameter("updateId");
			String name = request.getParameter("updateName");
			String picture = request.getParameter("updatePicture");
			Statement state = users.createStatement();

			if(name.length() != 0 && picture.length() != 0) {
				state.executeUpdate("UPDATE user_table SET name='" + name + "' ,picture='" + picture + "' WHERE id = '" + id + "'");
			}else if (name.length() != 0) {
				state.executeUpdate("UPDATE user_table SET name='" + name + "' WHERE id = '" + id + "'");
			}else if (picture.length() != 0) {
				state.executeUpdate("UPDATE user_table SET picture='" + picture + "' WHERE id = '" + id + "'");
			}
			//DB切断処理を呼び出す
			DBConnection.closeConnection(users, state);
			response.sendRedirect("/select"); //UserSelectServletを呼び出す
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
