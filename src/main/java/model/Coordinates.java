package model;

public class Coordinates {
    
    public static final int NO_VALUE = Integer.MAX_VALUE;
    //kui API väljastab midagi imelikumat, ntks koordinaadi error,
    //siis long lat tulen NO VALUE
    public double latitude;
    public double longitude;
    public Coordinates() {
    }
    
    @Override
    public String toString(){
        return longitude + ":" + latitude;
    }
}
