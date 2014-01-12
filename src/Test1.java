

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;

/**
 * Servlet implementation class MyDatabaseResponser
 */
@WebServlet("/MyDatabaseResponser")
public class Test1 extends HttpServlet {
 private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test1() {
        super();
        // TODO Auto-generated constructor stub
    }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  Table table = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb")).getTable("Wina");



  PrintWriter pw = response.getWriter();
  pw.println("");
  pw.println(getServletContext().getRealPath("/")+"my.mdb");
  pw.println(table.getRowCount());
//  table.addRow(table.getRowCount(),"foo",21);
  addWine("wino3",10,"20","link","alzacja","Winnna",10,1);
 }
 
 private void addWine(String name, int price, String alc, String link, String region, String winemakerName, int idKupaz, int idColor){
	  Database db;
	  Table table;
	  try {
	   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
	   table = db.getTable("Wina");
	   table.addRow("",name, price, alc, link, region, winemakerName, idKupaz, idColor);
	   db.close();
	  } catch (IOException e1) {
	   // TODO Auto-generated catch block
	   e1.printStackTrace();
	  }}
 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
 }

}

