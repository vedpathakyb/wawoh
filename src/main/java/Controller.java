
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

@WebServlet("/Controller")
public class Controller extends HttpServlet
{
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		System.out.println("Inside Controller Servlet");
		int i= Integer.parseInt(req.getParameter("id"));
		String n=req.getParameter("name");
		String INSERT_USERS_SQL = "INSERT INTO Emp " + 	"(id, name) VALUES " + "(?, ?);";
		System.out.println(n);
		pw.println(n);
		try
		{

			Emp e=new Emp();
			e.setName(n);
			e.setId(i);
			Class.forName("org.postgresql.Driver");
			Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo","postgres","postgres");
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1,e.getId());
			preparedStatement.setString(2,e.getName());
			//preparedStatement.executeUpdate("INSERT INTO Emp VALUES(e.getId(),e.getName())");
			preparedStatement.executeUpdate();
			System.out.println("Data Inserted succesfully");
			con.close();
			preparedStatement.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}

