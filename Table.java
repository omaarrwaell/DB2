import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;



public class Table implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String strTableName;
	private String strClusteringKeyColumn;
	private Hashtable<String, String> htblColNameType;
	private Hashtable<String, String> htblColNameMin;
	private Hashtable<String, String> htblColNameMax;
	Vector<Page> listPages ;
	private int lastpageid;

	
	public String getStrTableName() {
		return strTableName;
	}
	public void setStrTableName(String strTableName) {
		this.strTableName = strTableName;
	}
	public String getStrClusteringKeyColumn() {
		return strClusteringKeyColumn;
	}
	public void setStrClusteringKeyColumn(String strClusteringKeyColumn) {
		this.strClusteringKeyColumn = strClusteringKeyColumn;
	}
	public Hashtable<String, String> getHtblColNameType() {
		return htblColNameType;
	}
	public void setHtblColNameType(Hashtable<String, String> htblColNameType) {
		this.htblColNameType = htblColNameType;
	}
	public Hashtable<String, String> getHtblColNameMin() {
		return htblColNameMin;
	}
	public void setHtblColNameMin(Hashtable<String, String> htblColNameMin) {
		this.htblColNameMin = htblColNameMin;
	}
	public Hashtable<String, String> getHtblColNameMax() {
		return htblColNameMax;
	}
	public void setHtblColNameMax(Hashtable<String, String> htblColNameMax) {
		this.htblColNameMax = htblColNameMax;
	}
	public Vector<Page> getListPages() {
		return listPages;
	}
	public void setListPages(Vector<Page> listPages) {
		this.listPages = listPages;
	}
	public int getLastpageid() {
		return lastpageid;
	}
	public void setLastpageid(int lastpageid) {
		this.lastpageid = lastpageid;
	}
	public int getlastpage() {
		return lastpageid;
	}
public Table(String strTableName, String strClusteringKeyColumn, Hashtable<String, String> htblColNameType,
			Hashtable<String, String> htblColNameMin, Hashtable<String, String> htblColNameMax) {
		super();
		this.strTableName = strTableName;
		this.strClusteringKeyColumn = strClusteringKeyColumn;
		this.htblColNameType = htblColNameType;
		this.htblColNameMin = htblColNameMin;
		this.htblColNameMax = htblColNameMax;
	}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return strTableName ;
}

}