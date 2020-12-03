/*
TimCartmelHashingLZW Component B
Timothy Cartmel
COSC2203 Data Structures
Assign 4 Hashing LZW
4/8/2020
This program compresses and decompresses a text file.
 */
package timchashinglzw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author timca
 */
public class TimCHashingLZW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length!=2){
            System.out.printf("Usage: TimCHashingLZW -zu filename\n");
            System.exit(1);
        }
        boolean compress = false;
        String inputFileName = args[1];
        String outputFileName = inputFileName;
        if(args[0].equalsIgnoreCase("-u")){
            outputFileName += ".unz"; 
        }
        else if(args[0].equalsIgnoreCase("-z")){
            compress = true;
            outputFileName += ".lzw";
        }
        else{
            System.out.println("Invalid argument."
            + " Recheck your compression/decomppresion arg.");
            System.exit(1);
        }
            
        try{
            
            String in = new String (Files.readAllBytes(Paths.get(inputFileName)));
            String out;
            LZW lzw = new LZW();
            
            if(compress){
                System.out.printf("Zipping: %s\n", inputFileName);
                out = lzw.compressFile(in);
                Files.write(Paths.get(outputFileName), out.getBytes());
                System.out.printf("Zipped: %s  (%d%%)\n",outputFileName, lzw.ratio);  
            }    
            else{
                System.out.printf("Unzipping: %s\n", inputFileName);
                out = lzw.decompressFile(in);
                Files.write(Paths.get(outputFileName), out.getBytes());
                System.out.printf("Unzipped: %s  (%d%%)\n",outputFileName, lzw.ratio);  
            }
            System.out.println("Program Terminated");
        }   
        
        catch (IOException e){
            System.out.println (e);
        }  
    }
}