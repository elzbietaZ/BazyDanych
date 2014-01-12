

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

/**
 * Servlet implementation class ParsingTest
 */
@WebServlet("/ParsingTest")
public class ParsingTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParsingTest() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	//	writeExample();
		tableToXml("U¿ytkownicy");
	/*	List<Change> changes=new ArrayList<Change>();
		changes.add(new Change(new Date(10,10,20),"Wina",20));
		changes.add(new Change(new Date(10,10,20),"U¿ytkownicy",1));
		changesToXml(changes);
		*/

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void writeExample() throws IOException{
		try{
			
			  
			  FileWriter fileWriter = new FileWriter(getServletContext().getRealPath("/") + File.separator + "WEB-INF" + File.separator + "test_file.txt"); 
			  BufferedWriter out = new BufferedWriter(fileWriter);
			  out.write("<Hello Java>");
			  out.close();
			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
 }
	 private void tableToXml(String tableName){
		 
		 Database db;
		 Table table;
		  try {
		   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
		   table = db.getTable(tableName);
		   FileWriter fileWriter = new FileWriter(getServletContext().getRealPath("/") + File.separator + "WEB-INF" + File.separator + tableName+"_xml.txt"); 
		   BufferedWriter out = new BufferedWriter(fileWriter);
		   out.write("<"+tableName+">");
		   out.newLine();
		   int rowCount=table.getRowCount();
		   int columnsCount=table.getColumnCount();
           Cursor cursor = CursorBuilder.createCursor(table);
           cursor.moveToNextRow();
		   for(int i=0; i<rowCount;i++){
			   out.write("<entry>");
			   out.newLine();
			   
				   Set<Entry<String, Object>> set=cursor.getCurrentRow().entrySet();
				   Iterator iter=set.iterator();
				   for(int j=0; j<columnsCount; j++){
				   Entry<String, Object> e=(Entry<String, Object>) iter.next();
				   String key=e.getKey();
				   Object value=e.getValue();
				   out.write("<"+key+">");
				   if(value==null){
					   out.write("null");
				   }
				   else{ 
					   out.write(value.getClass().getSimpleName()+"  ");
					   out.write(value.toString());
					   }
				   out.write("</"+key+">");
				   out.newLine();
			   }
			   out.write("</entry>");
			   out.newLine();
			   cursor.moveToNextRow();
		   }
		   
		   out.write("</"+tableName+">");
		   out.close();
		   db.close();
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		  }
		 
	 }
	 
	 
 private void changesToXml(List<Change> changes) throws IOException{
		 
		 Database db;
		 Table table;
		 FileWriter fileWriter = new FileWriter(getServletContext().getRealPath("/") + File.separator + "WEB-INF" + File.separator + "changes_xml.txt"); 
		 BufferedWriter out = new BufferedWriter(fileWriter);
		 out.write("<changes>");
		 out.newLine();
		  try {
		   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
		   for(int i=0; i<changes.size();i++){
			   Change changedRow=changes.get(i);
			   String tableName=changedRow.getTableName();
			   int FieldId=changedRow.getFieldId();
			   out.write("<"+tableName+">");
			   out.newLine();
			   table = db.getTable(tableName);
			   int columnsCount=table.getColumnCount();
			   String idName=table.getColumns().get(0).getName();
			   Row row=CursorBuilder.findRow(table, Collections.singletonMap(idName, FieldId));

			   Set<Entry<String, Object>> set=row.entrySet();
			   Iterator iter=set.iterator();
			   for(int j=0; j<columnsCount; j++){
			   Entry<String, Object> e=(Entry<String, Object>) iter.next();
			   String key=e.getKey();
			   Object value=e.getValue();
			   out.write("<"+key+">");
			   if(value==null){
				   out.write("null");
			   }
			   else{ 
				   out.write(value.toString());
				   }
			   out.write("</"+key+">");
			   out.newLine();
		   }		   
			   out.write("</"+tableName+">");
			   out.newLine();
			   
			   
		   }
		   out.write("</changes>");
		   out.close();
		   db.close();
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		  }
		 
	 }


}
