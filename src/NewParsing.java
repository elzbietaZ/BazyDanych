

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;

/**
 * Servlet implementation class NewParsing
 */
@WebServlet("/NewParsing")
public class NewParsing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewParsing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String s="<register><username>usernameExample</username><email>email</email><password>passwordExample</password></register>";
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println(tableToXml("U¿ytkownicy"));
		
		 
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	private void parsingResister(){
		
		
	}
	
 private String tableToXml(String tableName) throws IOException{
		 
		 Database db;
		 Table table;
		 Document doc = new Document();
		  try {
		   db = DatabaseBuilder.open(new File(getServletContext().getRealPath("/")+"my.mdb"));
		   table = db.getTable(tableName);
		   
		   Element root=new Element(tableName);
		   doc.setRootElement(root);
		  
		   int rowCount=table.getRowCount();
		   int columnsCount=table.getColumnCount();
           Cursor cursor = CursorBuilder.createCursor(table);
           cursor.moveToNextRow();
		   for(int i=0; i<rowCount;i++){
			   Element entry=new Element("entry");
			   
				   Set<Entry<String, Object>> set=cursor.getCurrentRow().entrySet();
				   Iterator iter=set.iterator();
				   for(int j=0; j<columnsCount; j++){
				   Entry<String, Object> e=(Entry<String, Object>) iter.next();
				   String key=e.getKey();
				   Object value=e.getValue();
				   
				   if(value==null){	
					   entry.addContent(new Element(key).setText("¿ê¹"));
				   }
				   else{ 
					   entry.addContent(new Element(key).setText(value.toString()));
					   }
			   }

			   cursor.moveToNextRow();
			   doc.getRootElement().addContent(entry);
		   }

		   db.close();
		  } catch (IOException e1) {
		   // TODO Auto-generated catch block
		   e1.printStackTrace();
		  }
		  
		  String xml = new XMLOutputter().outputString(doc);
		  XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		  File fileDir = new File("C:\\Users\\Public\\text.txt");
		  
	//		Writer out = new BufferedWriter(new OutputStreamWriter(
	//			new FileOutputStream(fileDir), "UTF8"));
		
		  FileOutputStream fileOutput = new FileOutputStream(fileDir);

		  xmlOutputter.output(doc, fileOutput);
//	      xmlOutputter.output(doc, System.out);
		  
		  
		  return xml;
	 }
 
  private String example() throws IOException{

Element login = new Element("login");
Document doc = new Document(login);
			login.addContent(new Element("loginname").setText("exm1"));
			login.addContent(new Element("password").setText("exm2"));
			
			String xml = new XMLOutputter().outputString(doc);
			 XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.output(doc, System.out);
			xml=xmlOutputter.outputString(doc);
			
			return xml;
  }
  

  
	

}
