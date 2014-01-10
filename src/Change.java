import java.sql.Date;
import java.util.List;
import java.util.Map;


public class Change {


	private Date date;
	private String tableName;
	private int fieldId;
//	private Map<String, Object> fieldstoChange;
	
	
	public Change(Date date, String tableName, int fieldId){
		this.date=date;
		this.tableName=tableName;
		this.fieldId=fieldId;
	//	this.fieldstoChange=fieldstoChange;
		
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public int getFieldId() {
		return fieldId;
	}






	
}
