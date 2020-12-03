/*
TimCartmelHashingLZW Component B
Timothy Cartmel
COSC2203 Data Structures
Assign 4 Hashing LZW
4/8/2020
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
 /*   String decompressFilexxx(String inStr){
        ratio = 0;
        String out = new String();
        Scanner scanner = new Scanner(inStr);  
        int oldCode;
        if(scanner.hasNextInt()){
            oldCode = scanner.nextInt();
            out += hashTable.intLookup(oldCode);
        }
        
        while(scanner.hasNextInt()){
            int newCode = scanner.nextInt();
            String str = hashTable.intLookup(newCode);
            
            if(str != null)
                out += str;
            char ch = str.charAt(0);
            
            hashTable.stringInsertion(prevStr + hashTable.intLookup(code).charAt(0));
            prevStr = hashTable.intLookup(code);
        }
        
        if(out.length()!=0)
            ratio = inStr.length()*100/out.length();
        
        return out;
    }
    
    String decompressFile(String inStr){
        ratio = 0;
        char firstChar;
        int inSize = 0;
        int oldCode = 0;
        String out = new String();
        String decodeStr = new String();
        String tempStr = new String();
        Scanner scanner = new Scanner(inStr);
        
        if(scanner.hasNextInt()){
            oldCode = scanner.nextInt();
            decodeStr = hashTable.intLookup(oldCode);
            if(decodeStr != null){
                out += decodeStr;
                firstChar = decodeStr.charAt(0);
            }
        }    
        
        while(scanner.hasNextInt()){
            int newCode = scanner.nextInt();
            String newStr;
            newStr = hashTable.intLookup(newCode);
            if(newStr == null){
                tempStr = decodeStr;
                if(tempStr==null)
                    System.out.printf("stop");    
                tempStr += tempStr.charAt(0);
                hashTable.stringInsertion(tempStr);
            } 
            else{
                tempStr = newStr;
                hashTable.stringInsertion(decodeStr + tempStr.charAt(0));
            }
            
            out += tempStr;
            firstChar = tempStr.charAt(0);
            decodeStr = newStr;
        }
        
        if(out.length()!=0)
            ratio = inStr.length()*100/out.length();
        
        return out;
    }*/

