//Team XY - William Xiang and Joel Ye
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
    } // O(1)


    //constructor intializes an a*a matrix
    public Matrix( int a ) {
	matrix = new Object[a][a];
    } // O(1)


    //return size of this matrix, where size is 1 dimension
    private int size() {
	return matrix.length;
    } // O(1)


    //return the item at the specified row & column   
    private Object get( int r, int c ) {
	return matrix[r][c];
    } // O(1)


    //return true if this matrix is empty, false otherwise
    private boolean isEmpty( int r, int c ) {
	return get(r,c) == null;
    } // O(1)


    //overwrite item at specified row and column with newVal
    //return old value
    private Object set( int r, int c, Object newVal ) {
	Object temp = get(r,c);
	matrix[r][c] = newVal;
	return temp;
    } // O(1)

    public Object[] getRow (int r){
	return matrix[r];
    } // O(1)

    public Object[] setRow(int r, Object[] newRow){
	Object[] temp = getRow(r);
	matrix[r] = newRow;
	return temp;
    } // O(1)

    public Object[] getCol(int c){
	Object[] ret = new Object[size()];
	for (int i = 0; i < size(); i++)
	    ret[i] = get(i,c); //append column values to ret
	return ret;
    } // O(n)

    public Object[] setCol(int c, Object[] newCol){
	Object[] ret = new Object[size()];
	//using getCol and then changing would results in n runtime
	//as opposed to 2n runtime (we think)
	for (int i = 0; i < size(); i++){
	    ret[i] = get(i,c);
	    set(i,c,newCol[i]); //append column values to ret after set
	}
	return ret;
    } // O(n)
    
    public boolean isFull(){
	for(int r = 0; r < size(); r++)
	    for(int c = 0; c < size(); c++)
		if (isEmpty(r,c))
		    return false; //if one index is empty, the list isn't full
	return true; //return true if none of the indexes are empty
    } // O(n^2)

    public void transpose(){
	for (int r = 0; r < size(); r++)
	    for (int c = 0; c < size(); c++)
		if (r > c) //only swaps the first upper-left diagonal "half" of the array
		    set(r,c,set(c,r,get(r,c)));
    } // O(n^2)

    public boolean contains( Object o ){
	for (int r = 0; r < size(); r++)
	    for (int c = 0; c < size(); c++)
		if (o.equals(get(r,c)))
		    return true;
	return false;
    } // O(n^2)
    
    //return String representation of this matrix
    // (make it look like a matrix)
    public String toString() {
	String ret = "";
	for (int i = 0; i < size(); i++){
	    for (int j = 0; j < size(); j++)
		ret += get(i,j) + "\t";
	    ret += "\n";
	}
	return ret;
    } // O(n^2)


    //override inherited equals method
    //criteria for equality: matrices have identical dimensions,
    // and identical values in each slot
    public boolean equals( Object rightSide ) {
	boolean ret = this == rightSide;
	if (!ret && rightSide instanceof Matrix && ((Matrix)rightSide).size() == size()){
	    for (int i = 0; i < size(); i++)
		for (int j = 0; j < size(); j++)    
		    if (!(get(i,j) == null && ((Matrix)rightSide).get(i,j) == null)  
			&& (get(i,j) == null
			    ||!get(i,j).equals(((Matrix)rightSide).get(i,j))))
			return false;
	    return true;
	}
	return ret;
    } // O(n^2)


    //swap two columns of this matrix 
    //(1,1) is top left corner of matrix
    //row values increase going down
    //column value increase L-to-R
    public void swapColumns( int c1, int c2  ) {		
	for (int i = 0; i < this.size(); i++)
	    set(i,c1,set(i,c2,get(i,c1)));
    } // O(n)


    //swap two rows of this matrix 
    //(1,1) is top left corner of matrix
    //row values increase going down
    //column value increase L-to-R
    public void swapRows( int r1, int r2  ) {
	setRow(r1,setRow(r2,getRow(r1)));
    } // O(1)


    //main method for testing
    public static void main( String[] args ) {
	// creds to emma for test cases (taken from Piazza)
        Matrix george = new Matrix(4);
	Matrix ginny = new Matrix();
	for (int i=0; i<4;i++) {
	    for (int j=0; j<4; j++) {
		george.set(i,j,i-j+5);
	    }
	}
	System.out.println("george:\n" + george.toString() +
			   "\nginny:\n" + ginny.toString());

	//quick test for isFull()=======
	System.out.println("george.isFull(): " + george.isFull() +
			   "\nginny.isFull(): " + ginny.isFull());

	//===========GET/SETrows=========
	Object[] newRow = new Object[4];
	for (int i=0; i<4; i++) newRow[i] = i+1;
	System.out.println("\ngeorge.getRow(2):\t");
	for (int i=0; i<4; i++) System.out.print((george.get(2,i) + ","));
	System.out.println("\nAFTER george.setRow(2, [1,2,3,4]):\t");
	george.setRow(2,newRow);
	for (int i=0; i<4; i++) System.out.print((george.get(2,i) + ","));

	//===========GET/SET cols=========
	Object[] newCol = new Object[4];
	for (int i=0; i<4; i++) newCol[i] = 20-i;
	System.out.println("\n\ngeorge.getCol(2):\t");
	for (int i=0; i<4; i++) System.out.print((george.get(i,2)) + ",");
	System.out.println("\nAFTER george.setCol(r, [20,19,18,17] )");
	george.setCol(2, newCol);
	for (int i=0; i<4; i++) System.out.print((george.get(i,2) + ","));
	System.out.println("\n\ngeorge:\n" + george.toString());

	//===========transpose() + contains=========
	george.transpose();
	System.out.println("AFTER TRANSPOSAL:\n" + george.toString());

	System.out.println("george.contains(1): " + george.contains(1) +
			   "\ngeorge.contains(50): " + george.contains(50));
    }
}
