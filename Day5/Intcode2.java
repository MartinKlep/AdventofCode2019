import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Intcode2{

    public static String getOpcode(String s){
        int digit = Integer.parseInt(s);
        if(digit < 1 || digit > 11114){     // filter invalid opcodes
            return s;
        }
        int last =  Integer.parseInt(String.valueOf(s.charAt(s.length() - 1)));
        if(last < 1 || last > 8){
            return s;
        }
        String opcode = "0" + last;
        return opcode;
    }

    public static int[] getModes(int n, String s){
        int[] modes = new int[n];
        for(int i = s.length()-3, j = 0; i >= 0; i--, j++){
            modes[j] = Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        return modes;
    }

    public static void compute(String[] input){
        if(input.length > 0){
            System.out.println("Starting Thermal Environment Supervision Terminal (TEST)");
           int i = 0;
           boolean looping = true;
           while(looping == true){
                String value = input[i];
                String opcode = getOpcode(value);
                //System.out.println("Value: " + value + "; OpCode: " + opcode);
                int params;
                int[] modes = new int[3];
                switch(opcode){
                    case "01":      // add
                        //System.out.println(value + ", " + input[i+1] + ", " + input[i+2]  + ", " + input[i+3]);
                        params = 3;
                        modes = getModes(params, value);
                        //System.out.println("Modes: Mode[0]: " + modes[0] + "; Mode[1]: " + modes[1] + "; Mode[2]: " + modes[2]);
                        int param1, param2;

                        if(modes[0] == 0){  // position mode
                            int pos01 =  Integer.parseInt(input[i+1]);
                            param1 = Integer.parseInt(input[pos01]);
                        }
                        else{               // immediate mode
                            param1 = Integer.parseInt(input[i+1]);
                        }

                        if(modes[1] == 0){  // position mode
                            int pos02 =  Integer.parseInt(input[i+2]);
                            param2 = Integer.parseInt(input[pos02]);
                        }
                        else{               // immediate mode
                            param2 = Integer.parseInt(input[i+2]);
                        }

                        int result = param1 + param2;
                        input[Integer.parseInt(input[i+3])] = String.valueOf(result);
                        i+=params+1;
                        break;

                    case "02":     // multi  
                        //System.out.println(value + ", " + input[i+1] + ", " + input[i+2]  + ", " + input[i+3]);
                        params = 3;
                        modes = getModes(params, value);
                        int paramA, paramB;

                        if(modes[0] == 0){  // position mode
                            paramA = Integer.parseInt(input[Integer.parseInt(input[i+1])]);
                        }
                        else{               // immediate mode
                            paramA = Integer.parseInt(input[i+1]);
                        }

                        if(modes[1] == 0){  // position mode
                            paramB = Integer.parseInt(input[Integer.parseInt(input[i+2])]);
                        }
                        else{               // immediate mode
                            paramB = Integer.parseInt(input[i+2]);
                        }
                        
                        int product = paramA * paramB;
                        input[Integer.parseInt(input[i+3])] = String.valueOf(product);
                        i+=params+1;
                        break;

                    case "03":     // save input to position
                        //System.out.println(value + ", " + input[i+1] );
                        params = 1;
                        boolean end = false;
                        int userid = 0;
                        while(!end){
                            System.out.println("Hello, please provide your User ID...");
                            Scanner userinput = new Scanner(System.in);
                            if(userinput.hasNextInt()){
                                if(userinput.nextInt() >= 0 &&  userinput.nextInt() <= 9){
                                userid = userinput.nextInt();
                                end = true;
                                System.out.println("Entering with User ID " + userid);
                                userinput.close();
                                }
                                else{
                                    System.out.println("User ID needs to be a single Integer !");
                                }
                            }   
                            else{
                                System.out.println("User ID needs to be a single Integer !");
                            }
                        }
                        input[Integer.parseInt(input[i+1])] = String.valueOf(userid);
                        i+=params+1;
                        break;

                    case "04":     // output value
                        //System.out.println(value + ", " + input[i+1] );
                        params = 1;
                        modes = getModes(params, value);
                        if(modes[0] == 0){  // position mode
                            int pos04 = Integer.parseInt(input[i+1]);
                            System.out.println("Output: " + input[pos04]);
                        }
                        else{               // immediate mode
                            System.out.println("Output: " + input[i+1]);
                        }
                        i+=params+1;
                     break;
                        
                    case "05":     // jump-if-true
                        //System.out.println(value + ", " + input[i+1] + ", " + input[i+2] );
                        params = 2;
                        modes = getModes(params, value);
                        int param051 = 0;
                        int param052 = 0;

                        if(modes[0] == 0){  // position mode first parameter
                            int pos051 = Integer.parseInt(input[i+1]);
                            param051 = Integer.parseInt(input[pos051]);
                        }
                        else{               // immediate mode first parameter
                            param051 = Integer.parseInt(input[i+1]);
                        }

                        if(modes[1] == 0){  // position mode second parameter
                            int pos052 = Integer.parseInt(input[i+2]);
                            param052 = Integer.parseInt(input[pos052]);
                        }
                        else{               // immediate mode second parameter
                            param052 = Integer.parseInt(input[i+2]);
                        }

                        if(param051 != 0){
                            i = param052;
                        }
                        else{
                            i+=params+1;
                        }
                    break;

                    case "06":     // jump-if-false
                        //System.out.println(value + ", " + input[i+1] + ", " + input[i+2] );
                        params = 2;
                        modes = getModes(params, value);
                        int param061 = 0;
                        int param062 = 0;

                        if(modes[0] == 0){  // position mode first parameter
                            int pos061 = Integer.parseInt(input[i+1]);
                            param061 = Integer.parseInt(input[pos061]);
                        }
                        else{               // immediate mode first parameter
                            param061 = Integer.parseInt(input[i+1]);
                        }

                        if(modes[1] == 0){  // position mode second parameter
                            int pos062 = Integer.parseInt(input[i+2]);
                            param062 = Integer.parseInt(input[pos062]);
                        }
                        else{               // immediate mode second parameter
                            param062 = Integer.parseInt(input[i+2]);
                        }

                        if(param061 == 0){
                            i = param062;
                        }
                        else{
                            i+=params+1;
                        }
                    break;
                   
                case "07":     // less than
                    //System.out.println(value + ", " + input[i+1] + ", " + input[i+2]  + ", " + input[i+3]);
                    params = 3;
                    modes = getModes(params, value);
                    int param071 = 0;
                    int param072 = 0;

                    if(modes[0] == 0){  // position mode first parameter
                        int pos071 = Integer.parseInt(input[i+1]);
                        param071 = Integer.parseInt(input[pos071]);
                    }
                    else{               // immediate mode first parameter
                        param071 = Integer.parseInt(input[i+1]);
                    }

                    if(modes[1] == 0){  // position mode second parameter
                        int pos072 = Integer.parseInt(input[i+2]);
                        param072 = Integer.parseInt(input[pos072]);
                    }
                    else{               // immediate mode second parameter
                        param072 = Integer.parseInt(input[i+2]);
                    }

                    if(param071 < param072){
                        input[Integer.parseInt(input[i+3])] = "1";
                    }
                    else{
                        input[Integer.parseInt(input[i+3])] = "0";
                    }
                    i+=params+1;
                break;

                case "08":     // equal
                    //System.out.println(value + ", " + input[i+1] + ", " + input[i+2]  + ", " + input[i+3]);
                    params = 3;
                    modes = getModes(params, value);
                    int param081 = 0;
                    int param082 = 0;

                    if(modes[0] == 0){  // position mode first parameter
                        int pos081 = Integer.parseInt(input[i+1]);
                        param081 = Integer.parseInt(input[pos081]);
                    }
                    else{               // immediate mode first parameter
                        param081 = Integer.parseInt(input[i+1]);
                    }

                    if(modes[1] == 0){  // position mode second parameter
                        int pos082 = Integer.parseInt(input[i+2]);
                        param082 = Integer.parseInt(input[pos082]);
                    }
                    else{               // immediate mode second parameter
                        param082 = Integer.parseInt(input[i+2]);
                    }

                    if(param081 == param082){
                        input[Integer.parseInt(input[i+3])] = "1";
                    }
                    else{
                        input[Integer.parseInt(input[i+3])] = "0";
                    }
                    i+=params+1;
                break;

                    case "99":    // halt
                        looping = false;
                        System.out.println("Program succesfully halted (code 99)");
                        break;

                    default:
                        // do nothing
                        i+=1;

                }
            }
        }
    }

    public static void main(String[] args) {
        File diagnostic = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day5\\diagnostic program.txt"); // insert your filepath

        // test less than and equal opcodes
        //File diagnostic = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day5\\test equal position.txt"); 
        //File diagnostic = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day5\\test less position.txt"); 
        //File diagnostic = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day5\\test equal immediate.txt"); 
        //File diagnostic = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day5\\test less immediate.txt"); 

        // test jump opcodes
        //File diagnostic = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day5\\test jump position.txt"); 
        //File diagnostic = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day5\\test jump immediate.txt"); 
        //File diagnostic = new File("D:\\Libraries\\Documents\\GitHub\\AdventofCode2019\\Day5\\test jump large.txt"); 


        String rawprogram = "";

        try{
            Scanner reader = new Scanner(diagnostic);
            rawprogram = reader.nextLine();
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("ERROR: Scanning Program Input File");
            e.printStackTrace();
        }
        String[] program = rawprogram.split(",");
        compute(program);       // input 1 for part 1; Answer for part 1 is 9775037
                                // input 5 for part 2; Anwer for part 2 is 15586959
                                

    }
}