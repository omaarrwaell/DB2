import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class DBApp {

	Map<String, Object> tables = new HashMap<>();

	public void init() {

	}

	public void createTable(String strTableName, String strClusteringKeyColumn,
			Hashtable<String, String> htblColNameType, Hashtable<String, String> htblColNameMin,
			Hashtable<String, String> htblColNameMax) throws Exception {
		if (!tables.containsKey(strTableName)) {
			
//			File dbFile = new File("E:\\DB\\db.ser");
//			dbFile.createNewFile();

			Table table = new Table(strTableName, strClusteringKeyColumn, htblColNameType, htblColNameMin,
					htblColNameMax);
			serializeObject(table, "E:\\DB\\db.class");

//			// Create a directory for the table's pages
//			File tableDirectory = new File(dbFile + strTableName + "/");
//			tableDirectory.mkdir();
			tables.put(strTableName, table);
		} else {
			throw new Exception("Table " + strTableName + " already exists.");
		}

	}

	// Helper method to serialize an object to disk
	private void serializeObject(Object obj, String file) throws Exception {
		try {
			Table table = (Table) obj;
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(table);
			objectOut.close();
			fileOut.close();
			System.out.println("finished");
		} catch (IOException e) {
			throw new Exception("Error serializing object to file " + file);
		}
	}

	private void deserializeObject(String filepath) throws Exception {
		try {
			Table table;
			FileInputStream fileIn = new FileInputStream(filepath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			table = (Table) in.readObject();
			in.close();
			fileIn.close();
			System.out.println(table);
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
	}

	public void insertIntoTable(String strTableName, Hashtable<String, Object> htblColNameValue) throws DBAppException {

	}

	public static void main(String[] args) throws Exception {
		String strTableName = "Student"; 
		DBApp dbApp = new DBApp();
		Hashtable htblColNameType = new Hashtable( ); 
		htblColNameType.put("id", "java.lang.Integer"); 
		htblColNameType.put("name", "java.lang.String"); 
		htblColNameType.put("gpa", "java.lang.double"); 
		
		Hashtable htblColNameMin= new Hashtable();
		htblColNameMin.put("id", "1");
		htblColNameMin.put("name", "aaaaa");
		htblColNameMin.put("gpa", "0");
		
		Hashtable htblColNameMax= new Hashtable();
		htblColNameMax.put("id", "9999");
		htblColNameMax.put("name", "zzzzz");
		htblColNameMax.put("gpa", "4");
		
		
		
		dbApp.createTable( strTableName, "id", htblColNameType ,htblColNameMin,htblColNameMax); 
		

//		dbApp.deserializeObject("E:\\DB\\db.ser");
	}

}