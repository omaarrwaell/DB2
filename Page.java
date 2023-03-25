package DB2;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;



public class Page implements Serializable {
	 static int  pageid =0;
    private static final long serialVersionUID = 1L; // Unique ID for serialization
    private Vector<Hashtable<String, Object>> tuples;
    int pagesize = 0 ;
    int maxPageSize = 5;


    public Page() {
    	this.tuples= new Vector<>();
        pageid++;
    }

    public void addTuple(Hashtable<String, Object> tuple) {
        tuples.add(tuple);
    }

    public void removeTuple(Hashtable<String, Object>  tuple) {
        tuples.remove(tuple);
    }

    public Vector<Hashtable<String, Object>> getTuples() {
        return tuples;
    }
}
