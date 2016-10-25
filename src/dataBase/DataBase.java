package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase {

	static private Connection con;

	public DataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:Mysql://localhost:3306/tsumm", "root", "12345");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 createTableUser();

	}

	public void createTableUser() {
		try {
			Statement st = con.createStatement();
			st.execute("CREATE  TABLE IF NOT EXISTS `tsumm`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`userName` VARCHAR(45) NULL ,`pass` VARCHAR(45) NULL ,PRIMARY KEY (`id`) );");
			System.out.println("User created!");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void insertUser(String userName, String pass) {

		try {
			Statement st = con.createStatement();
			st.execute(" insert into `tsumm`.`users` (userName,pass) values('"
					+ userName + "','" + pass  + "');");
			System.out.println("user inserted!");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
	public void insertComment(String id, String com) {

		try {
			Statement st = con.createStatement();
			st.execute(" insert into `tsumm`.`comment` (newsId,com) values("
					+ id + ",'" + com  + "');");
			System.out.println("comment inserted!");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}


	public ResultSet select(String table, String cond) {

		try {
			Statement st = con.createStatement();
			System.out.println("select * from `tsumm`.`" + table + "` where "
					+ cond + " ;");
			System.out.println("badesh");
			ResultSet rs = st.executeQuery("select * from `tsumm`.`" + table
					+ "` where " + cond + " ;");

			System.out.println("select :D!");
			return rs;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

}
