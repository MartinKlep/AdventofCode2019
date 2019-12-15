import java.io.*;
import java.util.Scanner;

public class SpaceImageConverter{
    private static File pic = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day8\\pw.txt"); // insert your filepath
    private static String rawFile; 

    public static void main(String[] args) {
        try{
            Scanner reader = new Scanner(pic);
            while(reader.hasNextLine()){
                rawFile = reader.nextLine();
            }
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("ERROR: Scanning File");
            e.printStackTrace();
        }

        SpaceImage password = new SpaceImage(25, 6, rawFile);
        //password.printAllLAyers(); 

        int[][] theLayer = password.getLayerFewestZeros();
        System.out.println("The Layer: ");
        System.out.println();
        password.printLayer(theLayer);
        System.out.println();
        int result = password.oneXtwo(theLayer);
        System.out.println("Result part 1: " + result);
    
        password.render();
    }
}