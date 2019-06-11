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

    public Vector vec = new Vector();

    public Matrix() {

    }

    public double[][] matrixMul(double[][] A, double[][] B) throws AlgebraException {
        double[][] result = new double[A.length][B[0].length];
        double[] aux = new double[B.length];

        for (int j = 0; j < B[0].length; j++) {

            for (int k = 0; k < B.length; k++) {
                aux[k] = B[k][j];
            }

            for (int i = 0; i < A.length; i++) {
                result[i][j] = vec.dotProduct(A[i], aux);
            }
        }
        return result;
    }

    public double[][] extend(double[][] A, double[] b) throws AlgebraException {
        double[][] B = toMatrix(b);
        return extend(A, B);
    }
    
    public double[][] extend(double[][] A, double[][] B) throws AlgebraException {
        if (A.length != B.length) {
            throw new AlgebraException("Las matrices no tienen el mismo numero de filas");
        }
        double[][] C = new double[A.length][A[0].length + B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                C[i][j] = A[i][j];
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = A[0].length; j < C[0].length; j++) {
                C[i][j] = B[i][j - A[0].length];
            }
        }
        return C;
    }

    static double[][] pivotize(double[][] m) throws AlgebraException {
        if (m.length != m[0].length) {
            throw new AlgebraException("La matriz no es cuadrada.");
        }
        int n = m.length;
        double[][] id = range(0, n).mapToObj(j -> range(0, n)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);

        for (int i = 0; i < n; i++) {
            double maxm = m[i][i];
            int row = i;
            for (int j = i; j < n; j++) {
                if (m[j][i] > maxm) {
                    maxm = m[j][i];
                    row = j;
                }
            }

            if (i != row) {
                double[] tmp = id[i];
                id[i] = id[row];
                id[row] = tmp;
            }
        }
        return id;
    }

    public double[][][] lu(double[][] A) throws AlgebraException {
        int n = A.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        double[][] P = pivotize(A);
        double[][] A2 = matrixMul(P, A);

        for (int j = 0; j < n; j++) {
            L[j][j] = 1;
            for (int i = 0; i < j + 1; i++) {
                double s1 = 0;
                for (int k = 0; k < i; k++) {
                    s1 += U[k][j] * L[i][k];
                }
                U[i][j] = A2[i][j] - s1;
            }
            for (int i = j; i < n; i++) {
                double s2 = 0;
                for (int k = 0; k < j; k++) {
                    s2 += U[k][j] * L[i][k];
                }
                L[i][j] = (A2[i][j] - s2) / U[j][j];
            }
        }
        return new double[][][]{L, U, P};
    }
    
    private double[][] toMatrix(double[] a){
        double[][] A = new double[a.length][1];
        for(int i = 0; i < a.length; i++){
            A[i][0] = a[i];
        }
        return A;
    }

    public String print(double[][] a) {
        String s = "";
        for (int i = 0; i < a.length; i++) {
            s += vec.print(a[i]);
            if (i + 1 != a.length) {
                s += "\n";
            }
        }
        return s;
    }
}
