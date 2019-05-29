/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectores;

/**
 *
 * @author Andreas
 */
public class Matrix {

    public Vector[] Matrix;
    public int lengthI;
    public int lengthJ;

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
    
    static Matrix matrixMul(Matrix A, Matrix B) throws AlgebraException {
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
    
//    static Matrix matrixMul(Matrix A, Matrix B) {
//        double[][] result = new double[A.lengthI][B.lengthJ];
//        
//        for(int j = 0; j < A.lengthJ; j++){
//            double[] prod = new double[];
//            double suma;
//            
//            for(int i = 0; i < A.lengthI; i++){
//                suma = A.getValue(i, j)+B.getValue(j, i);
//                prod += suma;
//            }
//        }
//        return new Matrix(result);
//    }
    
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
