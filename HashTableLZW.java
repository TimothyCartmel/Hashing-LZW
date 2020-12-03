/*
TimCartmelHashingLZW Component B
Timothy Cartmel
COSC2203 Data Structures
Assign 4 Hashing LZW
4/8/2020
This file creates the hash table to create the dictionary.
 */
package timchashinglzw;

/**
 *
 * @author timca
 */
public class HashTableLZW {
    HashNodeLZW hashTableArray[];

    HashTableLZW(){
        hashTableArray = new HashNodeLZW[163993];
        for(int i=10; i<=126; i++){
            String newString = new String();
            newString = String.valueOf((char)i);
            HashNodeLZW hashNode = new HashNodeLZW(newString);
            quadraticProbeInsert(hashNode);
        }
    }
    
    boolean quadraticProbeInsert(HashNodeLZW hashNode){
        long counter = 0;
        int index = 0;
        HashNodeLZW testNode;
        do{
            long offset = (long)hashNode.hashCode + counter*counter;
            index = (int)(offset % hashTableArray.length);
            counter++;
            if(index < 0 || index >= hashTableArray.length)
                System.out.printf("%d %d %s", hashNode.hashCode, counter, hashNode.string);
            testNode = hashTableArray[index];
            if(testNode == null)
                break;
            else if(testNode.string.equals(hashNode.string))
                return true;
                
        } while(testNode != null);
         
        hashTableArray[index] = hashNode;
        return true;
    }
    
    int quadraticProbeLookup(HashNodeLZW hashNode){
        long counter = 0;
        int index;
        HashNodeLZW testNode;
        
        do{
            long offset = (long)hashNode.hashCode + counter*counter;
            index = (int)(offset % hashTableArray.length);
            counter++;
            if(index < 0 || index >= hashTableArray.length)
                System.out.printf("%d %d %s", hashNode.hashCode, counter, hashNode.string);
            testNode = hashTableArray[index];
            if(testNode == null)
                return -1;
            
        } while(!testNode.string.equals(hashNode.string));
            
        return index;
    }
    
    boolean stringInsertion(String newCode){
        HashNodeLZW newNode = new HashNodeLZW(newCode);
        boolean inserted = quadraticProbeInsert(newNode);
        return inserted;
    }
    
    String intLookup(int indexCode){
        HashNodeLZW currNode = hashTableArray[indexCode];
        if(currNode == null)
            return null;
        else
            return currNode.string;
    }
    
    int stringLookup(String key){
        HashNodeLZW newNode = new HashNodeLZW(key);
        return quadraticProbeLookup(newNode); 
    }
}
