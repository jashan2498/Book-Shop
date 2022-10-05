import java.sql.*;
import javax.swing.*;
public class DBLayer{
	public static boolean executeq(String sql)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bookshop2018","root","");
			Statement s=con.createStatement();
			s.execute(sql);
				return true;
			}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"exception occured:	"+ex);
			return false;
		}
	}
	public static ResultSet getResult(String sql)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bookshop2018","root","");
			Statement s=con.createStatement();
			return s.executeQuery(sql);	
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"exception aa gyi:	"+ex);
			return null;
		}
	}
	public static String getScalar(String sql)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bookshop2018","root","");
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(sql);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"exception aa gyi:	"+ex);
			return null;
		}
	}
}