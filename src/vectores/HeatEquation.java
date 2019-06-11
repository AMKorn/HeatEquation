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
public class HeatEquation {

    private Matrix mat = new Matrix();
    private Vector vec = new Vector();

    public final double I = 25;
    public final double J = 50;
    public final double L = 1;
    public final double T = 10;

    public final double h = L / I;
    public final double k = T / J;

    public double alpha = k / (h * h);

    public double xi;
    public double tj;

    public double l = 0.25;
    public double r = 0.25;

    public double[] vectorFrontera = new double[(int) I];

    public double f(double x) {
        double f;
        f = x;
        f -= 0.5;
        f *= f;
        return f;
    }

    public static void main(String[] args) {
        HeatEquation vec = new HeatEquation();
        vec.main();
    }

    public void main() {
        try {
            vectorFrontera[0] = alpha * l;
            vectorFrontera[(int) I - 1] = alpha * r;
            double[][] A = mAlpha();
            double[] b = u0();
            double[][][] m = mat.lu(A);
            double[][] L = m[0];
            double[][] U = m[1];
            double[][] P = m[2];

            System.out.println(mat.print(mAlpha()));
            System.out.println(vec.print(b));
            
            double[][] M = new double[(int) J+1][(int) I];

            for (int j = 0; j <= J; j++) {
                double[] aux = vec.toVector(mat.matrixMul(P, b));
                double[] auxSol = solve(L, aux);
                b = solve(U, auxSol);
                b = vec.add(b, vectorFrontera);
                System.out.println(j +": " + vec.print(b));
                M[j] = b;
            }
            
            System.out.println(mat.print(M));

//            System.out.println("L:\n" + mat.print(L) +
//                    "\n U:\n" +
//                    mat.print(U) +
//                    "\n P:\n" +
//                    mat.print(P));
//            System.out.println("\n\n\n" + mat.print(mat.extend(L, U)));
        } catch (AlgebraException ae) {
            System.err.println(ae);
        }
    }

    /**
     * Resuelve A*x=b donde A es una matriz triangular n*n y b es un vector n.
     *
     * @param A
     * @param b
     * @return
     */
    public double[] solve(double[][] A, double[] b) throws AlgebraException {
        int n = b.length;
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }

    public double[][] mAlpha() {

        double[][] matrix = new double[(int) I][(int) I];
        for (int i = 0; i < I; i++) {
            for (int j = 0; j < I; j++) {
                if (i == j) {
                    matrix[i][j] = 1 + 2 * alpha;
                } else if (i - j == 1 || j - i == 1) {
                    matrix[i][j] = -alpha;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    public double[] u0() {
        try {
            double[] u = new double[(int) I];
            for (int i = 0; i < I; i++) {
                xi = i * h;
                u[i] = f(xi);
            }
            u = vec.add(u, vectorFrontera);
            return u;
        } catch (AlgebraException ae) {
            System.err.println("Fatal Exception on initial vector: " + ae);
            return null;
        }
    }
}
