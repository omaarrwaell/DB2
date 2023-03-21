
import java.io.Serializable;
import java.util.Vector;



public class Page implements Serializable {
	 static int  pageid =0;
    private static final long serialVersionUID = 1L; // Unique ID for serialization
    private Vector<tuple> tuples;
    int pagesize = 0 ;
    int maxPageSize = 5;

    public Page() {
    	this.tuples= new Vector<>();
        pageid++;
    }

    public void addTuple(tuple tuple) {
        tuples.add(tuple);
    }

    public void removeTuple(tuple  tuple) {
        tuples.remove(tuple);
    }

    public Vector<tuple> getTuples() {
        return tuples;
    }
}
