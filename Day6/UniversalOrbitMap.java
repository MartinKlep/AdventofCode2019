import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class UniversalOrbitMap{

    public static void main(String[] args) {
        File mapdata = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day6\\MapData.txt"); // insert your filepath
      //  File mapdata = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day6\\TestMap.txt"); // testinput
        ArrayList<String> orbits = new ArrayList<String>();
        

        try{
            Scanner reader = new Scanner(mapdata);
            while(reader.hasNextLine()){
                orbits.add(reader.nextLine());
            }
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("ERROR: Scanning MapData File");
            e.printStackTrace();
        }

        ArrayList<String> uniqueObjects = new ArrayList<String>();

        for(String s : orbits){
            String[] objects = s.split("\\)");
            if(!uniqueObjects.contains(objects[0])){
                uniqueObjects.add(objects[0]);
            }
            if(!uniqueObjects.contains(objects[1])){
                uniqueObjects.add(objects[1]);
            }
        }
   
        ArrayList<SpaceNode> spacenodes = new ArrayList<SpaceNode>();
        for(String s : uniqueObjects){
            SpaceNode space = new SpaceNode(s);
            spacenodes.add(space);
        }

        for(String s : orbits){
            String[] objects = s.split("\\)");
            SpaceNode parent = spacenodes.get(uniqueObjects.indexOf(objects[0]));
            SpaceNode child  = spacenodes.get(uniqueObjects.indexOf(objects[1]));
            parent.addChild(child);
        }

        int orbitCountChecksum = 0;

        for(SpaceNode spacey : spacenodes){
            orbitCountChecksum += spacey.getOrbitSum();
        }

        System.out.println("Orbit Count Checksum: " + orbitCountChecksum);  // Answer is 308790
    }
}