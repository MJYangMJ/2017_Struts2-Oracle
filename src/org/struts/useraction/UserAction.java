package org.struts.useraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.struts.bean.UserBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport  implements ModelDriven<UserBean> {

	private static Log log = LogFactory.getLog(UserAction.class);
    
	UserBean globalUser = new UserBean();
    @Override
    public UserBean getModel() {
        return globalUser;
    }
    
	private List<UserBean> userlist;
	private Map session;
	private String error_message;
	
	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}


    public List<UserBean> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserBean> userlist) {
        this.userlist = userlist;
    }


	/**
	 * 建立数据库连接
	 *
	 * @return
	 */
	private static Connection getConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "scott";
		String password = "admin";
		Connection conn = null;
		try {
			Class.forName(driver);
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(url, username, password);
			 log.info("connect success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * 用户名与密码匹配检查
	 *
	 * @return 结果标识 字符串 success 表示匹配成功 UserNotExist表示用户不存在 error 表示匹配失败
	 */
	private String userCheck() {
		log.info("user checking...");
		String name = null;
		String pass = null;
		Connection conn = getConn();
		String sql = "select * from users where username='" + globalUser.getUsername() + "'";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("username");
				pass = rs.getString("password");
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(globalUser.getUsername());
		if (name == null)// 验证用户名是否存在
			return "UserNotExist";
		else if (name.equals(globalUser.getUsername()) && pass.equals(globalUser.getPassword()))// 验证登录用户名与密码是否匹配
		{
			return "success";
		}
		else
			return "error";

	}

	private UserBean query(int id) {
		log.info("my query...");
		Connection conn = getConn();
		String sql = "select * from users where id='" + id + "'";
		PreparedStatement pstmt;
		UserBean user = new UserBean();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(id);
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				System.out.println("myquery: name: " + rs.getString("username")
						+ " \tpassword: " + rs.getString("password"));
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	private UserBean find_Id(String name,String pass) {
		log.info("my query to get id...");
		Connection conn = getConn();
		String sql = "select * from users where username='"+name+"' and password='"+pass+"'";
		PreparedStatement pstmt;

		UserBean user = new UserBean();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(name);
				user.setPassword(pass);
				System.out.println("myquery: id: "+user.getId()+" name: " + name
						+ " password: " + pass);
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	/**
	 * 查看所有用户
	 *
	 * @return List<UserBean> list
	 */
	private List<UserBean> findAllUser() {
		log.info("get all user info ...");
		List<UserBean> list = new ArrayList<UserBean>();
		UserBean user;
		Connection conn = getConn();
		String sql = "SELECT * FROM users ORDER BY id";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 将注册信息添加到数据库表 users
	 *
	 * @param username
	 * @param password
	 * @return
	 */

	private int insert(String username, String password) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into users(username,password) values('"+username+"','"+password+"')";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			log.info("try to insert username="+username+"  password="+password);
			System.out.println(sql);
			i = pstmt.executeUpdate();
			System.out.println("insert result: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}

	private static int myUpdate(int id, String newPass) {
		Connection conn = getConn();
		int i = 0;
		String sql = "update users set password='" + newPass + "' where id='"
				+ id + "'";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);

			i = pstmt.executeUpdate();
			System.out.println("update resutl: " + i);

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}

	/**
	 * login.action
	 *
	 * @return
	 * @throws Exception
	 */

	public String login() throws Exception {
		log.info("login.action...");
			if (userCheck().equals("success")) {
//			ActionContext.getContext().getSession()
//				.put("username", getUsername());
			globalUser.setId( find_Id(globalUser.getUsername(), globalUser.getPassword()).getId());
			return SUCCESS;
		} else if (userCheck().equals("UserNotExist"))
			return "UserNotExist";
		else
			return ERROR;

	}


	/**
	 * register.action
	 *
	 * @return
	 * @throws Exception
	 */

	public String register() throws Exception {
		String name = globalUser.getUsername();
		String pass = globalUser.getPassword();
		if (name.length()==0||pass.length()==0)
		{
			error_message = "name and password can't be null";

			return "error";
		}
		int i = insert(name, pass);
		if (i != 0)
			return "success";
		else{
			error_message = "form your name(<10) and password(<20)";
			return "error";
		}
	}

	public String show() throws Exception {
		log.info("show.action...");
//		log.info(id);
		if (globalUser.getUsername().equals("Yang"))
		{
			userlist = findAllUser();
			if (userlist.size() > 0)
				return "success";
			else{
				error_message = "userlist.size is null or you are not granted";
				return "error";
			}
		}
		else
			return "error";
	}

	public String update() throws Exception {
		log.info("id="+globalUser.getId());
		UserBean user=query(globalUser.getId());
		if (null!=user){
			globalUser.setUsername(user.getUsername());
			log.info("username="+globalUser.getUsername());
			myUpdate(globalUser.getId(),globalUser.getPassword());
			return "success";
		}
		else{
			error_message = "can't update now,try again later";
			return "error";
		}
	}

	public String delete() throws Exception {
		Connection conn = getConn();
		int i = 0;
		System.out.println("id=" + globalUser.getId());
		String sql = "delete users where id ='" + globalUser.getId() + "'";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);

			i = pstmt.executeUpdate();
			System.out.println("delete resutl: " + i);

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (i > 0)
			return "success";
		else{
			error_message = "can't delete now,try again later";

			return "error";
		}
	}


}
