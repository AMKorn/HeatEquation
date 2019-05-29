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

    Vector[] Matrix;

    public Matrix(Vector[] mat) {
        Matrix = mat;
    }

    public Matrix(double[][] matrix) {
        Vector[] tempMatrix = new Vector[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            tempMatrix[i] = new Vector(matrix[i]);
        }
        Matrix = tempMatrix;
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
