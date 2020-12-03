/*
Timothy Cartmel
This file contains the main compress and decompress methods.
*/
package timchashinglzw;

import java.util.Scanner;
/**
 *
 * @author timca
 */
public class LZW {
    HashTableLZW hashTable;
    int ratio;

    LZW(){
        ratio = 0;
        hashTable = new HashTableLZW();
    }
    
    String compressFile(String in){
        ratio = 0;
        int inSize = 0;
        String out = new String();
        String newString = new String();
        if(!in.isEmpty())
            newString = String.valueOf(in.charAt(inSize++));
        
        while(inSize < in.length()){
            char ch = in.charAt(inSize++);
            String test = newString + ch;
            
            if(hashTable.stringLookup(test) < 0){
                int index = hashTable.stringLookup(newString);
                if(index < 0)
                    out += newString + " not found ";
                else
                    out += Integer.toString(index) + " ";
                hashTable.stringInsertion(test);
                newString = String.valueOf(ch);
            }
            else
                newString += ch;
        }
        
        int index = hashTable.stringLookup(newString);
        out += Integer.toString(index) + " ";
        
        if(out.length()!=0)
            ratio = in.length()*100/out.length();
        return out;
    }
 
String decompressFile( String inStr )
    {
        ratio= 0;
        String out= new String();
        String emitStr = new String();
        String prevDecodeStr= new String();
        Scanner scanner= new Scanner( inStr );

        if( scanner.hasNextInt())
        {
            int prevCode= scanner.nextInt();
            prevDecodeStr= hashTable.intLookup( prevCode );
            out += prevDecodeStr;
        }

        while( scanner.hasNextInt() )
        {
            int currCode= scanner.nextInt();
            String currDecodeStr= hashTable.intLookup( currCode );

            if( currDecodeStr == null )
            {   //is this where it's going wrong?
                emitStr= prevDecodeStr + emitStr.charAt( 0 ); 
                hashTable.stringInsertion( emitStr );
            }
            else
            {
                emitStr= currDecodeStr;
                hashTable.stringInsertion( prevDecodeStr + emitStr.charAt(0) );
            }

            out += emitStr;
            prevDecodeStr= currDecodeStr;
        }

        if( out.length() != 0 )
            ratio= inStr.length() * 100 / out.length();

        return out;
    }
}    
 
