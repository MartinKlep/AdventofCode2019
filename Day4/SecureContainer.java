public class SecureContainer{

public static boolean hasSameNeighbor(String s){
    boolean valid = false;
    for(int i = 0; i < 5; i++){
        if(s.charAt(i) == s.charAt(i+1)){
            return true;
        }
    }
    return valid;
}

public static boolean doesntDecrease(String s){
    boolean valid = true;
    char curr = s.charAt(0);
    for(int i = 1; i < 6; i++){
        if(curr > s.charAt(i)){
           valid = false; 
           return false;
        }
        else{
            curr = s.charAt(i);
        }
    }
    return valid;
}

public static boolean isValidPW(int i){
    String pw = Integer.toString(i);
    boolean valid = true;
    if(hasSameNeighbor(pw) == false || doesntDecrease(pw) == false){
        valid = false;
    }

    return valid;
}

public static boolean rule3(int i){
    boolean valid = false;
    if(!isValidPW(i)){
        return false;
    }
    else{
        String s = Integer.toString(i);
        char curr = s.charAt(0);
        int adjacent = 0;
        for(int j = 0; j < 6; j++){
           if(curr == s.charAt(j)){                                 // adjacent streak +1
                adjacent++;
                if(adjacent == 2 && j == 5){                        // special case if last 2 digits adjacent
                    return true;
                }
           }
           else{                                                    // adjacent streak broken
               if(adjacent == 2){
                   return true;                                     // meets pw criteria
               }
               else{                                                // begin new adjacent streak
                   curr = s.charAt(j);
                   adjacent = 1;
               }
           }
        }
    }
    return valid;
}
   

    public static void main(String[] args) {
        final int start = 178416;   // Input Range: 178416 - 676461
        final int end =   676461;
        
        int count = 0;
        for(int x = start; x <= end; x++){
            if(isValidPW(x)){
                count++;
                //System.out.println(x);
            }
        }

        int count2 = 0;
        for(int y = start; y <= end; y++){
            if(rule3(y)){
                count2++;
                //System.out.println(y);
            }
        }
        

        System.out.println("Test case 1 (TRUE): " + rule3(112233));
        System.out.println("Test case 2 (FALSE): " + rule3(123444));
        System.out.println("Test case 3 (TRUE): " + rule3(111122));
        System.out.println();
        
        System.out.println("# Valid Passwords: " + count); // Answer is 1650
        System.out.println();
        System.out.println("# Valid Passwords part 2: " + count2);

    }

}