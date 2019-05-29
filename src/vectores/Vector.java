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
    
    public Vector(double[] vec){
        Vector = vec;
    }
    
    @Override
    public String toString(){
        String s = "";
        s += "[";
        for(int i = 0; i < Vector.length; i++){
            s += Vector[i];
            if(i+1 != Vector.length){
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
