/*
Timothy Cartmel
This file stores the data of the nodes for the hash table.
 */
package timchashinglzw;

/**
 *
 * @author timca
 */
public class HashNodeLZW {
    int hashCode;
    String string;
    
    HashNodeLZW(){
        hashCode = 0;
        string = new String();
    }
    
    HashNodeLZW(int newHashCode){
        hashCode = newHashCode;
        if(hashCode<0)
            hashCode = 0 - hashCode;
        string = new String();
    }
    
    HashNodeLZW(String newString){
        hashCode = newString.hashCode();
        if(hashCode<0)
            hashCode = 0 - hashCode;
        string = new String(newString);
    }
}
