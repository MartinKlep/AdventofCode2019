public class Intcode{

    public static void compute(int[] input){
        if(input.length > 0){
           int i = 0;
           boolean looping = true;
           while(looping == true){
                switch(input[i]){
                    case 1:      // add 
                        input[input[i+3]] = input[input[i+1]] + input[input[i+2]];
                        i+=4;
                        break;
                    case 2:     // multi  
                    input[input[i+3]] = input[input[i+1]] * input[input[i+2]];
                        i+=4;
                        break;
                    case 99:    // halt
                        looping = false;
                        break;
                    default:
                        System.out.println("Default case: did not regognize valid Intcode : " + input[i]);

                }
            }
        }
    }
    
    public static void part2(int goal){
        for(int a = 0; a <= 99; a++){
            for(int b = 0; b <= 99; b++){
                int[] input = {1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,9,1,19,1,9,19,23,1,23,5,27,2,27,10,31,1,6,31,35,1,6,35,39,2,9,39,43,1,6,43,47,1,47,5,51,1,51,13,55,1,55,13,59,1,59,5,63,2,63,6,67,1,5,67,71,1,71,13,75,1,10,75,79,2,79,6,83,2,9,83,87,1,5,87,91,1,91,5,95,2,9,95,99,1,6,99,103,1,9,103,107,2,9,107,111,1,111,6,115,2,9,115,119,1,119,6,123,1,123,9,127,2,127,13,131,1,131,9,135,1,10,135,139,2,139,10,143,1,143,5,147,2,147,6,151,1,151,5,155,1,2,155,159,1,6,159,0,99,2,0,14,0};
                input[1] = a;
                input[2] = b;
                compute(input);
                if(input[0] == goal){
                    System.out.println("SUCCESS!!! FOUND GOAL WITH PARAMETERS a = " + a + " and b = " + b);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] input = {1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,9,1,19,1,9,19,23,1,23,5,27,2,27,10,31,1,6,31,35,1,6,35,39,2,9,39,43,1,6,43,47,1,47,5,51,1,51,13,55,1,55,13,59,1,59,5,63,2,63,6,67,1,5,67,71,1,71,13,75,1,10,75,79,2,79,6,83,2,9,83,87,1,5,87,91,1,91,5,95,2,9,95,99,1,6,99,103,1,9,103,107,2,9,107,111,1,111,6,115,2,9,115,119,1,119,6,123,1,123,9,127,2,127,13,131,1,131,9,135,1,10,135,139,2,139,10,143,1,143,5,147,2,147,6,151,1,151,5,155,1,2,155,159,1,6,159,0,99,2,0,14,0};
        compute(input);
        System.out.println("Position 0: " + input[0]);  // Answer: 6627023
        
        // Part 2
       part2(19690720);     // Answer: Noun = 40, Verb = 19; 100 * 40 + 19 = 4019

    }
}