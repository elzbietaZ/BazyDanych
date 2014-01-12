

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;



import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

/**
 * Servlet implementation class Rejestracja
 */
@WebServlet("/Rejestracja")
public class Rejestracja extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rejestracja() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		pw.println("");
		String s=registration("ela222","ela","ela@gmail.com");
		pw.println(s);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private String registration(String login, String password, String email){
		String register="error"; // zwraca ok - jeœli uda³o siê zarejestrowaæ u¿ytkownika, badLogin - kiedy istnieje ju¿ u¿ytkownik o takim loginie i error - jeœli coœ pójdzie nie tak;
		Date d=new Date();
		Database db;
		Table table;
		  try {
		   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
		   table = db.getTable("U¿ytkownicy");
		   Row row=CursorBuilder.findRow(table, Collections.singletonMap("Login", login));
		   if(row!=null){
		   register="badLogin";
		   }
		   else{
			 //  Object [] params={"",login,email, password};
			   Object [] params={"",login,email, password,"","","",null,d,"","","","","","U","N",""};
			   table.addRow(params);
			   register="ok";
		   }
		   db.close();
		  		   
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		   return register;
		  }
		return register;
		
	}
	


}
