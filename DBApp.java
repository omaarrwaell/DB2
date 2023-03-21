package DB2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.NonWritableChannelException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import com.sun.security.auth.NTNumericCredential;

public class DBApp {

	Map<String, Object> tables = new HashMap<>();
	String[] data = new String[100];

	public void init() {

	}

	public void createTable(String strTableName, String strClusteringKeyColumn,
			Hashtable<String, String> htblColNameType, Hashtable<String, String> htblColNameMin,
			Hashtable<String, String> htblColNameMax) throws Exception {
		if (!tables.containsKey(strTableName)) {

			Table table = new Table(strTableName, strClusteringKeyColumn, htblColNameType, htblColNameMin,
					htblColNameMax);
			serializeObject(table, "E:\\DB\\db.ser");

			createMetaData(table);

			// Create a directory for the table's pages
			File tableDirectory = new File("E:\\DB\\" + strTableName + "/");
			tableDirectory.mkdir();
			tables.put(strTableName, table);
		} else {
			throw new Exception("Table " + strTableName + " already exists.");
		}

	}

	public void createMetaData(Table table) throws IOException {
		File tablecsvFile = new File("E:\\DB\\" + table.getStrTableName() + ".csv");
		tablecsvFile.createNewFile();
		String columnName = "TableName, ColumnName, ColumnType, Clustering key, " + "Indexname , Index type , min ,max";
//		tablecsvFile.canWrite();
		FileWriter writer = new FileWriter(tablecsvFile);
		writer.append(columnName);
		writer.append("\n");
		Object[] columnNames = table.getHtblColNameType().keySet().toArray();

		Object[] columnTypes = table.getHtblColNameType().values().toArray();
		Object[] columnMin = table.getHtblColNameMin().values().toArray();
		Object[] columnMax = table.getHtblColNameMax().values().toArray();
		for (int i = columnNames.length - 1; i >= 0; i--) {
			String row = "";
			if (columnNames[i].equals(table.getStrClusteringKeyColumn())) {
				row = table.getStrTableName() + "," + columnNames[i] + "," + columnTypes[i] + "," + "true" + ","
						+ "Null" + ",Null" + "," + columnMin[i] + "," + columnMax[i];
			} else {
				row = table.getStrTableName() + "," + columnNames[i] + "," + columnTypes[i] + "," + "false" + ","
						+ "Null" + ",Null" + "," + columnMin[i] + "," + columnMax[i];

			}
			System.out.println(row);
			writer.append(row);
			writer.append("\n");
		}
		writer.close();
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

	private void serializePage(Object obj, String file) throws Exception {
		try {
			Page page = (Page) obj;
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(page);
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

	public void insertIntoTable(String strTableName, Hashtable<String, Object> htblColNameValue) throws Exception {
		// Table table =getTable(strTableName);
//		table.getHtblColNameType().containsKey(key)
		System.out.println(htblColNameValue.get("id"));

//		Object[] keys=htblColNameValue.keySet().toArray();
//		Object[] values = htblColNameValue.values().toArray();
//		

//		if(table.getListPages().size()==0) {
//			Page page = new Page();
//			
//		}

	}

	public void readCsv(String strTableName, Hashtable<String, Object> htblColNameValue) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("E:\\DB\\" + strTableName + ".csv"));
			String line;

			// Read headers from the CSV file
			line = reader.readLine();
			String[] headers = line.split(",");

			// Read data from the CSV file
			while ((line = reader.readLine()) != null) {

				data = line.split(",");
				if (data[0].equals(strTableName)) {
					
				

					if (!htblColNameValue.containsKey(data[1])) {
						
						}
					
					else {
						Object object = htblColNameValue.get(data[1]);
						if (!object.getClass().getName().equals(data[2])) {

						}
						
						}

					}

					// Process the data here...
					// For example, print the values of each column:
					for (int i = 0; i < data.length; i++) {
						System.out.println(headers[i] + ": " + data[i]);
					}
				}

			

			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred while reading the CSV file.");
			e.printStackTrace();
		}
	}

	public Table getTable(String strTableName) throws Exception {
		if (tables.containsKey(strTableName)) {
			return (Table) tables.get(strTableName);
		} else {
			throw new Exception();
		}
	}

	public static void main(String[] args) throws Exception {

		String strTableName = "Student";
		DBApp dbApp = new DBApp();
		Hashtable htblColNameType = new Hashtable();
		htblColNameType.put("id", "java.lang.Integer");
		htblColNameType.put("name", "java.lang.String");
		htblColNameType.put("gpa", "java.lang.double");

		Hashtable htblColNameMin = new Hashtable();
		htblColNameMin.put("id", "1");
		htblColNameMin.put("name", "aaaaa");
		htblColNameMin.put("gpa", "0");

		Hashtable htblColNameMax = new Hashtable();
		htblColNameMax.put("id", "9999");
		htblColNameMax.put("name", "zzzzz");
		htblColNameMax.put("gpa", "4");

		Hashtable htblColNameValue = new Hashtable();
		htblColNameValue.put("id", new Integer(2343432));
		htblColNameValue.put("name", new String("Ahmed Noor"));
		htblColNameValue.put("gpa", new Double(0.95));

		// dbApp.insertIntoTable( "Student" , htblColNameValue );
		Object num = 1234;
		System.out.println(num.getClass().getName());
		// dbApp.createTable( strTableName, "id", htblColNameType
		// ,htblColNameMin,htblColNameMax);
//		dbApp.readCsv("Student");
//		 for (int i = 0; i < dbApp.data.length; i++) {
//             System.out.println(dbApp.data[i]);
//         }

//		dbApp.deserializeObject("E:\\DB\\db.ser");
	}

}