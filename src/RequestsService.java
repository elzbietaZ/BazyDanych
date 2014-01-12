

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.healthmarketscience.jackcess.query.Query;

/**
 * Servlet implementation class RequestsService
 */
@WebServlet("/RequestsService")
public class RequestsService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Change> changes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestsService() {
        super();
        // TODO Auto-generated constructor stub
    }

    // przyk³adowe dane
    Object [] kolor={"","bia³e"};
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    pw.println("");
	//	addRow("kolory", kolor);
	    Object [] params={"","user7","cos"};
		addRow("U¿ytkownicy",params);
	//	deleteRow("Wina",10);
	//	updateValues("Wina",12,"Cena",101);
	//	pw.println(isPasswordCorrect("ela","ela"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void addRow(String tableName, Object[] params){
		  Database db;
		  Table table;
		  try {
		   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
		   table = db.getTable(tableName);
		   table.addRow(params);
		   db.close();
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		  }
		    
		 }
	
	private void deleteRow(String tableName, int id){
		  Database db;
		  Table table;
		  try {
		   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
		   table = db.getTable(tableName);
		   String idName=table.getColumns().get(0).getName();
		   Row row=CursorBuilder.findRow(table, Collections.singletonMap(idName, id));
		   table.deleteRow(row);
		   db.close();
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		  }
		    
		 }
	
	/* kompletny random, przy takich samych parametrach raz dzia³a a raz nie, a czasem z opóŸnieniem jakby, nie ³apie dlaczego
	 * Czasem te¿ dzia³a, ale wywala b³¹d przy tym */
	private void updateValues(String tableName, int id, String columnName, Object newValue){
		 Database db;
		  Table table;
		  try {
		   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
		   table = db.getTable(tableName);
		   String idName=table.getColumns().get(0).getName();
		   Row row=CursorBuilder.findRow(table, Collections.singletonMap(idName, id));
		   row.put(columnName, newValue);
		   //row.clear();
		   table.updateRow(row);
		   table.getColumns();
		   db.close();
		  		   
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		  }
	}
	
	private boolean isPasswordCorrect(String login, String password){
		boolean correct=false;
		Database db;
		  Table table;
		  try {
		   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
		   table = db.getTable("U¿ytkownicy");
		   Row row=CursorBuilder.findRow(table, Collections.singletonMap("Login", login));
		   if(row!=null){
		   if(row.containsValue(password)) correct=true;	 }
		   db.close();
		  		   
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		  }
		  return correct;
	}
	
	private void compareChanges(Date date){
		List<Change> changestoDownoad=new ArrayList<Change>();
		 boolean check=true;
		 int i=changes.size()-1;
		 
		 while(check && i>=0){
			if(changes.get(i).getDate().compareTo(date)>0){
				changestoDownoad.add(changes.get(i));
			}
			else{
				check=false;
			}
		 }
		
		
	}
	
	
	
	
	
	
	

}
