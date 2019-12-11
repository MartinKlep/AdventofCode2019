import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class UniversalOrbitMap{
    public static ArrayList<String> uniqueObjects = new ArrayList<String>();
    public static ArrayList<SpaceNode> spacenodes = new ArrayList<SpaceNode>();

    public static SpaceNode getSpaceNode(String s){
        int index = uniqueObjects.indexOf(s);
        return spacenodes.get(index);
    }

    public static ArrayList<SpaceNode> dfs(SpaceNode node){     // depht-first-search algorithm; returns all found nodes based on the supplied root node
        ArrayList<SpaceNode> foundNodes = new ArrayList<SpaceNode>();
        foundNodes.add(node);
        for(SpaceNode child : node.getChildren()){
            foundNodes.addAll(dfs(child));
        }
        return foundNodes;
    }

    public static SpaceNode lca(SpaceNode startnode, SpaceNode endnode){    // LCA: Lowest Common Ancestor Algorithm
       SpaceNode tempnode = startnode;

        while(true){
            if(tempnode.isRoot()){
                System.out.println("Could not find endnode: " + endnode.getName() + " (LCA)");
                return null;
            }
            else{
                if(dfs(tempnode).contains(endnode)){
                    return tempnode;
                }
                else{
                    tempnode = tempnode.getParent();
                }
            }
        }
    }
    
    public static int orbitTransfer(String start, String destination){
        SpaceNode startnode = getSpaceNode(start).getParent();
        SpaceNode endnode = getSpaceNode(destination).getParent();
        SpaceNode lca = lca(startnode, endnode);

        return startnode.getParentHopsTo(lca) + endnode.getParentHopsTo(lca);
    }   
    public static void main(String[] args) {
          File mapdata = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day6\\MapData.txt"); // insert your filepath
        //File mapdata = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day6\\TestMap.txt");  // testinput for part 1
       // File mapdata = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day6\\TestMap2.txt"); // testinput for part 2
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

        for(String s : orbits){
            String[] objects = s.split("\\)");
            if(!uniqueObjects.contains(objects[0])){
                uniqueObjects.add(objects[0]);
            }
            if(!uniqueObjects.contains(objects[1])){
                uniqueObjects.add(objects[1]);
            }
        }
   
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
        System.out.println();

        System.out.println(orbitTransfer("YOU", "SAN"));
    }
}