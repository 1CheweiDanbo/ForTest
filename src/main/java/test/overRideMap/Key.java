package test.overRideMap;

public class Key {
    private int id;

    public Key(int id) {
        this.id = id;
    }

    @Override
    public int hashCode(){
        return this.id%2;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Key)){
            return false;
        }else
            return true;
    }



}
