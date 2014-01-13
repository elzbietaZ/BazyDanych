

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

/**
 * Servlet implementation class Logowanie
 */
@WebServlet("/Logowanie")
public class Logowanie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logowanie() {
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
		String s="<login><username>ela</username><password>ela5</password></login>";
		try {
			pw.println(logowanie(s));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private String logowanie(String xml) throws JDOMException, IOException{
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(new StringReader(xml));
	     Element rootNode = doc.getRootElement();
	     String login=rootNode.getChildText("username");
	     String password=rootNode.getChildText("password");
		return isPasswordCorrect(login, password);
	}
	
	private String isPasswordCorrect(String login, String password){
		String correct="false";
		Database db;
		  Table table;
		  try {
		   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
		   table = db.getTable("U¿ytkownicy");
		   Row row=CursorBuilder.findRow(table, Collections.singletonMap("Login", login));
		   if(row!=null){
		   if(row.containsValue(password)) correct="true";	 }
		   else{
			   correct="badLogin"; // kiedy nie ma takiego u¿ytkownika w bazie
		   }
		   db.close();
		  		   
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		  }
		  return correct;
	}
}
