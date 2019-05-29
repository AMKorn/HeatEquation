/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectores;

import static java.util.stream.IntStream.range;

/**
 *
 * @author Andreas
 */
public class Matrix {

    public Vector[] Matrix;
    public int lengthI;
    public int lengthJ;

    public Matrix(){
        
    }
    
    public Matrix(Vector[] mat) {
        Matrix = mat;
    }

    public Matrix(double[][] matrix) {
        this.lengthI = matrix.length;
        Vector[] tempMatrix = new Vector[lengthI];
        for (int i = 0; i < lengthI; i++) {
            lengthJ = matrix[i].length;
            tempMatrix[i] = new Vector(matrix[i]);
        }
        Matrix = tempMatrix;
    }
    
    public Matrix matrixMul(Matrix A, Matrix B) throws AlgebraException {
        Matrix result = new Matrix(new double[A.lengthI][B.lengthJ]);
        Vector aux = new Vector(new double[B.lengthI]);
 
        for (int j = 0; j < B.lengthJ; j++) {
 
            for (int k = 0; k < B.lengthI; k++)
                aux.setValue(k, B.getValue(k,j));
 
            for (int i = 0; i < A.lengthI; i++)
                result.setValue(i, j, A.Matrix[i].dotProduct(aux));
        }
        return result;
    }
    
    static Matrix pivotize(Matrix m) throws AlgebraException {
        if(m.lengthI != m.lengthJ){
            throw new AlgebraException("La matriz no es cuadrada.");
        }
        int n = m.lengthI;
        double[][] id = range(0, n).mapToObj(j -> range(0, n)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);
 
        for (int i = 0; i < n; i++) {
            double maxm = m.getValue(i,i);
            int row = i;
            for (int j = i; j < n; j++)
                if (m.getValue(j,i) > maxm) {
                    maxm = m.getValue(j,i);
                    row = j;
                }
 
            if (i != row) {
                double[] tmp = id[i];
                id[i] = id[row];
                id[row] = tmp;
            }
        }
        return new Matrix(id);
    }
    
    public Matrix[] lu(Matrix A) throws AlgebraException {
            int n = A.lengthI;
            double[][] L = new double[n][n];
            double[][] U = new double[n][n];
            Matrix P = pivotize(A);
            Matrix A2 = matrixMul(P, A);
            
            for (int j = 0; j < n; j++) {
                L[j][j] = 1;
                for (int i = 0; i < j + 1; i++) {
                    double s1 = 0;
                    for (int k = 0; k < i; k++) {
                        s1 += U[k][j] * L[i][k];
                    }
                    U[i][j] = A2.getValue(i,j) - s1;
                }
                for (int i = j; i < n; i++) {
                    double s2 = 0;
                    for (int k = 0; k < j; k++) {
                        s2 += U[k][j] * L[i][k];
                    }
                    L[i][j] = (A2.getValue(i,j) - s2) / U[j][j];
                }
            }
            return new Matrix[]{new Matrix(L), new Matrix(U), P};
    }
    
    public double getValue(int i, int j){
        return Matrix[i].Vector[j];
    }
    
    public void setValue(int i, int j, double value){
        Matrix[i].Vector[j] = value;
    }
    
    public Vector getRow(int i){
        return Matrix[i];
    }
    
    public void setRow(int i, Vector vec){
        Matrix[i] = vec;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < Matrix.length; i++) {
            s += Matrix[i];
            if (i + 1 != Matrix.length) {
                s += "\n";
            }
        }
        return s;
    }
}
