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
            double[][] A = {
                {2,1,1},
                {4,3,4},
                {-2,-1,-4}
            };
            double[][][] m = mat.lu(A);
            double[][] L = m[0];
            double[][] U = m[1];
            double[][] P = m[2];
            System.out.println("L:\n" + mat.print(L) +
                    "\n U:\n" +
                    mat.print(U) +
                    "\n P:\n" +
                    mat.print(P));
        } catch (AlgebraException ae) {
            System.err.println(ae);
        }
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
        double[] u = new double[(int) I];
        for (int i = 0; i < I; i++) {
            xi = i * h;
            u[i] = f(xi);
        }
        u[0] += alpha * l;
        u[(int) I - 1] += alpha * r;
        return u;
    }
}
