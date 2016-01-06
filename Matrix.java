//Team   - William Xiang and Joel Ye
//APCS1 pd10
//HW55 -- Don't Think You Are, Know You Are
//2016  - 01 - 06

/*====================================
  class Matrix -- models a square matrix

  TASK: Implement methods below.
        Categorize runtime of each. 
        Test in your main method.
  ====================================*/ 

public class Matrix {

    //constant for default matrix size
    private final static int DEFAULT_SIZE = 2;

    private Object[][] matrix;


    //default constructor intializes a DEFAULT_SIZE*DEFAULT_SIZE matrix
    public Matrix( ) {
	matrix = new Object[2][2];
    }


    //constructor intializes an a*a matrix
    public Matrix( int a ) {
	matrix = new Object[a][a];
    }


    //return size of this matrix, where size is 1 dimension
    private int size() {
	return matrix.length;
    }


    //return the item at the specified row & column   
    private Object get( int r, int c ) {
	return matrix[r][c];
    }


    //return true if this matrix is empty, false otherwise
    private boolean isEmpty( int r, int c ) {
	return get(r,c) == null;
    }


    //overwrite item at specified row and column with newVal
    //return old value
    private Object set( int r, int c, Object newVal ) {
	Object temp = get(r,c);
	matrix[r][c] = newVal;
	return temp;
    }

    public Object[] getRow (int r){
	return matrix[r];
    }

    public Object[] setRow(int r, Object[] newRow){
	Object[] temp = getRow(r);
	matrix[r] = newRow;
	return temp;
    }

    public Object[] getCol(int c){
	Object[] ret = new Object[size()];
	for (int i = 0; i < size(); i++)
	    ret[i] = get(i,c);
	return ret;
    }

    public Object[] setCol(int c, Object[] newCol){
	Object[] ret = new Object[size()];
	for (int i = 0; i < size(); i++){
	    ret[i] = get(i,c);
	    set(i,c,newCol[i]);
	}
	return ret;
    }
    
    public boolean isFull(){
	for(int r = 0; r < size(); r++)
	    for(int c = 0; c < size(); c++)
		if (isEmpty(r,c))
		    return false;
	return true;
    }

    public void transpose(){
	for (int r = 0; r < size(); r++)
	    for (int c = 0; c < size(); c++)
		if (r > c)
		    set(r,c,set(c,r,get(r,c)));
    }

    public boolean contains( Object o ){
	for (int r = 0; r < size(); r++)
	    for (int c = 0; c < size(); c++)
		if (o.equals(get(r,c)))
		    return true;
	return false;
    }
    
    //return String representation of this matrix
    // (make it look like a matrix)
    public String toString() {
		String ret = "";
		for (int i = 0; i < this.size(); i++){
			for (int j = 0; j < this.size(); j++)
				ret += this.get(i,j) + "\t";
			ret += "\n";
		}
		return ret;
    }


    //override inherited equals method
    //criteria for equality: matrices have identical dimensions,
    // and identical values in each slot
    public boolean equals( Object rightSide ) {
	boolean ret = this == rightSide;
	if (!ret && rightSide instanceof Matrix && ((Matrix)rightSide).size() == this.size()){
	    for (int i = 0; i < this.size(); i++)
		for (int j = 0; j < this.size(); j++)    
		if (!(this.get(i,j) == null && ((Matrix)rightSide).get(i,j) == null)  
			&& ((this.get(i,j) == null && ((Matrix)rightSide).get(i,j) != null)
			|| (this.get(i,j) != null && ((Matrix)rightSide).get(i,j) == null)
			||!this.get(i,j).equals(((Matrix)rightSide).get(i,j))))
			return false;
		return true;
	}
	return ret;
    }


    //swap two columns of this matrix 
    //(1,1) is top left corner of matrix
    //row values increase going down
    //column value increase L-to-R
    public void swapColumns( int c1, int c2  ) {		
	for (int i = 0; i < this.size(); i++)
		this.set(i,c1,this.set(i,c2,this.get(i,c1)));
    }


    //swap two rows of this matrix 
    //(1,1) is top left corner of matrix
    //row values increase going down
    //column value increase L-to-R
    public void swapRows( int r1, int r2  ) {
	
    }


    //main method for testing
    public static void main( String[] args ) {
	Matrix red = new Matrix(5);
	Matrix blue = new Matrix(5);
	red.set(0,3,2);
	red.set(2,4,1);
	System.out.println(red);
	red.swapRows(0,1);
	System.out.println(red);
    }

}//end class Matrix
