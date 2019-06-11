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
public class Vector {
    
    public Vector(){
        
    }
    
    public double dotProduct(double[] a, double[] b) throws AlgebraException {
        if (a.length != b.length) {
            throw new AlgebraException("La longitud de los dos vectores es diferente");
        }
        double dotProduct = 0;
        for (int i = 0; i < a.length; i++) {
            dotProduct += a[i]*b[i];
        }
        return dotProduct;
    }
    
    public double[] add(double[] a, double[] b) throws AlgebraException {
        if (a.length != b.length) {
            throw new AlgebraException("La longitud de los dos vectores es diferente");
        }
        double[] sum = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            sum[i] = a[i]+b[i];
        }
        return sum;
    }

    public double[] toVector(double[][] A) throws AlgebraException {
        if(A[0].length != 1){
            throw new AlgebraException("No se puede convertir una matriz bidimensional en un vector.");
        }
        double[] a = new double[A.length];
        for(int i = 0; i < a.length; i++){
            a[i] = A[i][0];
        }
        return a;
    }
    
    public double[] roundVector(double[] a){
        for(int i = 0; i < a.length; i++){
            a[i] *= 10000;
            a[i] = (int) a[i];
            a[i] /= 10000;
        }
        return a;
    }
    
    public String print(double[] a) {
        String s = "";
        s += "[";
        for (int i = 0; i < a.length; i++) {
            s += a[i];
            if (i + 1 != a.length) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
