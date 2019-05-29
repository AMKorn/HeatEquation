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

    double[] Vector;
    int length;
    
    public Vector(){
        
    }

    public Vector(double[] vec) {
        Vector = vec;
        length = vec.length;
    }
    
    public double getValue(int i){
        return Vector[i];
    }
    
    public void setValue(int i, double value){
        Vector[i] = value;
    }

    public double dotProduct(Vector b) throws AlgebraException {
        if (this.Vector.length != b.Vector.length) {
            throw new AlgebraException("La longitud de los dos vectores es diferente");
        }
        double dotProduct = 0;
        for (int i = 0; i < this.Vector.length; i++) {
            dotProduct += this.Vector[i]*b.Vector[i];
        }
        return dotProduct;
    }
    
    public double dotProduct(Vector a, Vector b) throws AlgebraException {
        if (a.Vector.length != b.Vector.length) {
            throw new AlgebraException("La longitud de los dos vectores es diferente");
        }
        double dotProduct = 0;
        for (int i = 0; i < this.Vector.length; i++) {
            dotProduct += a.Vector[i]*b.Vector[i];
        }
        return dotProduct;
    }

    @Override
    public String toString() {
        String s = "";
        s += "[";
        for (int i = 0; i < Vector.length; i++) {
            s += Vector[i];
            if (i + 1 != Vector.length) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
