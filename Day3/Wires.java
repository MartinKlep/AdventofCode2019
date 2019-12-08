import java.io.*;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;
import java.lang.math;


public class Wires{

    public static String[][] field = new String[22000][22000];
    //public static Stack crossStack = new Stack();
    public static ArrayList<int[]> crossList = new ArrayList<int[]>();

    public static void procField(){
        for(int x = 0; x < field.length; x++){
            for(int y = 0; y < field[x].length; y++){
                field[x][y] = ".";
                
            }
        }     
        field[11000][11000] ="o";
    }

    public static void countmax(String wire){
        String[] newWire = wire.split(",");
        int x = 0;     // start coordinates
        int y = 0;
        int maxX = 0;
        int maxY = 0;
        int minX = 0;
        int minY = 0;
        for(int z = 0; z < newWire.length; z++){
            char direction = newWire[z].charAt(0);
            int amount = Integer.parseInt(newWire[z].substring(1));
            switch(direction){
                case 'U':
                while(amount > 0){
                    x-=1;
                    if(x < minX){
                        minX = x;
                    }
                    amount--;
                }
                break;

            case 'R':
                while(amount > 0){
                    y+=1;
                    if(y > maxY){
                        maxY = y;
                    }
                    amount--;
                }
                break;

            case 'D':
                    while(amount > 0){
                    x+=1;
                    if(x > maxX){
                        maxX = x;
                    }
                    amount--;
                }
                break;

            case 'L':
                   while(amount > 0){
                    y-=1;
                    if(y < minY){
                        minY = y;
                    }
                    amount--;
                }
                break;

            default:
                System.out.println("Invalid direction identifier");
            }
        }
        System.out.println("Max X: " + maxX);
        System.out.println("Min X: " + minX);
        System.out.println("Max Y: " + maxY);
        System.out.println("Min Y: " + minY);
        System.out.println();

    }
    public static void drawWire(String wire, String nr){
        String[] newWire = wire.split(",");
        int x = 11000;     // start coordinates
        int y = 11000;
        for(int z = 0; z < newWire.length; z++){
            char direction = newWire[z].charAt(0);
            int amount = Integer.parseInt(newWire[z].substring(1));
            switch(direction){
                case 'U':
                    while(amount > 0){
                        x-=1;
                        if(field[x][y].equals(".") == true || field[x][y].equals(nr) == true){
                            field[x][y] = nr;
                        }
                        else if(field[x][y].equals("o") == true){
                            field[x][y] = "o";  // don't mess with the starting point
                        }
                        else{
                            field[x][y] = "X";  // mark crossing point 
                            int[] crossing = new int[3];
                            crossing[0] = x;
                            crossing[1] = y;
                            crossList.add(crossing);
                        }
                        amount--;
                    }
                    break;

                case 'R':
                    while(amount > 0){
                        y+=1;
                        if(field[x][y].equals(".") == true || field[x][y].equals(nr) == true){
                            field[x][y] = nr;
                        }
                        else if(field[x][y].equals("o") == true){
                            field[x][y] = "o";  // don't mess with the starting point
                        }
                        else{
                            field[x][y] = "X";  // mark crossing point 
                            int[] crossing = new int[3];
                            crossing[0] = x;
                            crossing[1] = y;
                            crossList.add(crossing);
                        }
                        amount--;
                    }
                    break;

                case 'D':
                        while(amount > 0){
                        x+=1;
                        if(field[x][y].equals(".") == true || field[x][y].equals(nr) == true){
                            field[x][y] = nr;
                        }
                        else if(field[x][y].equals("o") == true){
                            field[x][y] = "o";  // don't mess with the starting point
                        }
                        else{
                            field[x][y] = "X";  // mark crossing point 
                            int[] crossing = new int[3];
                            crossing[0] = x;
                            crossing[1] = y;
                            crossList.add(crossing);
                        }
                        amount--;
                    }
                    break;

                case 'L':
                       while(amount > 0){
                        y-=1;
                        if(field[x][y].equals(".") == true || field[x][y].equals(nr) == true){
                            field[x][y] = nr;
                        }
                        else if(field[x][y].equals("o") == true){
                            field[x][y] = "o";  // don't mess with the starting point
                        }
                        else{
                            field[x][y] = "X";  // mark crossing point 
                           int[] crossing = new int[3];
                            crossing[0] = x;
                            crossing[1] = y;
                            crossList.add(crossing);
                        }
                        amount--;
                    }
                    break;

                default:
                    System.out.println("Invalid direction identifier");
            }
        }
    }

    public static int getManhattan(){
        int manhattan = 11000;
       /* while(crossStack.isEmpty() == false){
            int temp = 0;
            int[] cross = (int[])crossStack.pop();
            temp += Math.abs(11000 - cross[0]);
            temp += Math.abs(11000 - cross[1]);
            if(temp < manhattan){
                manhattan = temp;
            }
        }*/

        for(int[] cross : crossList){
            int temp = 0;
            temp += Math.abs(11000 - cross[0]);
            temp += Math.abs(11000 - cross[1]);
            if(temp < manhattan){
                manhattan = temp;
            }
        }
        return manhattan;
    }

    public static void countSteps(String wire){
        String[] newWire = wire.split(",");
        int x = 11000;     // start coordinates
        int y = 11000;
        int steps = 0;
        for(int z = 0; z < newWire.length; z++){
            char direction = newWire[z].charAt(0);
            int amount = Integer.parseInt(newWire[z].substring(1));
            ArrayList<int[]> duplicates = new ArrayList<int[]>();
            switch(direction){
                case 'U':
                    while(amount > 0){
                        steps++;
                        x-=1;
                        if(field[x][y].equals("X") == true){    // find crossing point
                            boolean dup = false;
                            for(int[] dupl : duplicates){
                                if(dupl[0] == x && dupl[1] == y){
                                    dup = true;    // found duplicate
                                }
                            }
                            if(dup == false){   // only add steps to crossing when it is not a duplicate for this wire
                                for(int[] cross : crossList){
                                    if(cross[0] == x && cross[1] == y){
                                        cross[2] += steps;           // add step counter
                                        duplicates.add(cross);      // add crossing to duplicate list
                                    }
                                }
                            }
                        }
                        amount--;
                    }
                    break;

                case 'R':
                    while(amount > 0){
                        steps++;
                        y+=1;
                        if(field[x][y].equals("X") == true){    // find crossing point
                            boolean dup = false;
                            for(int[] dupl : duplicates){
                                if(dupl[0] == x && dupl[1] == y){
                                    dup = true;    // found duplicate
                                }
                            }
                            if(dup == false){   // only add steps to crossing when it is not a duplicate for this wire
                                for(int[] cross : crossList){
                                    if(cross[0] == x && cross[1] == y){
                                        cross[2] += steps;           // add step counter
                                        duplicates.add(cross);      // add crossing to duplicate list
                                    }
                                }
                            }
                        }
                        amount--;
                    }
                    break;

                case 'D':
                        while(amount > 0){
                            steps++;
                            x+=1;
                            if(field[x][y].equals("X") == true){    // find crossing point
                                boolean dup = false;
                                for(int[] dupl : duplicates){
                                    if(dupl[0] == x && dupl[1] == y){
                                        dup = true;    // found duplicate
                                    }
                                }   
                            if(dup == false){   // only add steps to crossing when it is not a duplicate for this wire
                                for(int[] cross : crossList){
                                    if(cross[0] == x && cross[1] == y){
                                        cross[2] += steps;           // add step counter
                                        duplicates.add(cross);      // add crossing to duplicate list
                                    }
                                }
                            }
                        }
                        amount--;
                    }
                    break;

                case 'L':
                       while(amount > 0){
                        steps++;
                        y-=1;
                        if(field[x][y].equals("X") == true){    // find crossing point
                            boolean dup = false;
                            for(int[] dupl : duplicates){
                                if(dupl[0] == x && dupl[1] == y){
                                    dup = true;    // found duplicate
                                }
                            }
                            if(dup == false){   // only add steps to crossing when it is not a duplicate for this wire
                                for(int[] cross : crossList){
                                    if(cross[0] == x && cross[1] == y){
                                        cross[2] += steps;           // add step counter
                                        duplicates.add(cross);      // add crossing to duplicate list
                                    }
                                }
                            }
                        }
                        amount--;
                    }
                    break;

                default:
                    System.out.println("Invalid direction identifier");
            }
        }
    }

    public static int getLowestSteps(){
        int lowSteps = 11000;
        for(int[] abc: crossList){
           if(abc[2] < lowSteps){
               lowSteps = abc[2];
           }
        }
        return lowSteps;
    }
    public static void main(String[] args) {    
        File input = new File("..\\input.txt"); // insert your filepath
        String[] rawWires = new String[2];
        try{
            Scanner reader = new Scanner(input);
            int i = 0;
            while(reader.hasNextLine()){
                rawWires[i] = reader.nextLine();
                i++;
            }
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("ERROR");
            e.printStackTrace();
        }
        procField();
      
        //System.out.println(Arrays.deepToString(field));
         for(int w = 0; w < rawWires.length; w++){
           countmax(rawWires[w]);
         }

        for(int v = 0; v < rawWires.length; v++){
            drawWire(rawWires[v], String.valueOf(v)); 
        }
        
        System.out.println("Manhattan distance to closest intersection: " + getManhattan());  // Answer is 280

        for(int part2 = 0; part2 < rawWires.length; part2++){
            countSteps(rawWires[part2]); 
        }
        
        System.out.println("Printing crosslist:");
        System.out.println();
        for(int[] print: crossList){
            System.out.println("[" + print[0] + "][" + print[1] + "][" + print[2] + "]");
        }
        System.out.println();
        System.out.println("Solution Part 2: " + getLowestSteps());     // Answer is 10554
    }
}
