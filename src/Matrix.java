@SuppressWarnings("static-access")

public abstract class Matrix {

	protected static int[][] data;
	protected static int n, m;
	
	//empty constructor
	public Matrix (int n, int m) {
		this.n = n;
		this.m = m;
		this.data = new int[n][m];
	}
	
	//constructor
	public Matrix (int[][] a) {
		n = a.length;
		m = a[0].length;
		this.data = new int[n][m];
		for (int i = 0; i<n ; i++) {
			for (int j = 0; j<m; j++) {
				data[i][j] = a[i][j];
			}
		}
	}
	
    // return n-by-n identity matrix I
    public static int[][] identity(int n) {
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++)
            a[i][i] = 1;
        return a;
    }

    // return x . y
    public static int dot(int[] x, int[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        int sum = 0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    // return B = A^T
    public static int[][] transpose(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] b = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                b[j][i] = a[i][j];
        return b;
    }

    // return c = a + b
    public static int[][] add(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[0].length;
        int[][] c = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }

    // return c = a - b
    public static int[][] subtract(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[0].length;
        int[][] c = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] - b[i][j];
        return c;
    }

    // return c = a * b
    public static int[][] multiply(int[][] a, int[][] b) {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        int[][] c = new int[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    // matrix-vector multiplication (y = A * x)
    public static int[] multiply(int[][] a, int[] x) {
        int m = a.length;
        int n = a[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        int[] y = new int[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += a[i][j] * x[j];
        return y;
    }


    // vector-matrix multiplication (y = x^T A)
    public static int[] multiply(int[] x, int[][] a) {
        int m = a.length;
        int n = a[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        int[] y = new int[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += a[i][j] * x[i];
        return y;
    }
    
    // returns B = A^n
    public static int[][] power(int[][] a, int n){
    	int[][] b = identity (a.length);
    	for (int k = 0; k<n ; k++) {
    		b = multiply(b,a);
    	}
    	return b;    	
    }
    
    //prints the matrix
    public static void show() {
    	for (int i = 0; i<n; i++) {
    		System.out.print("[");
    		for (int j = 0; j<m; j++) {
    			System.out.print(" " + data[i][j]);
    		}
    		System.out.println("]");
    	}
    }
}