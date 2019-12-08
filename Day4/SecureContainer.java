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

public static boolean rule3(String s){
    boolean valid = false;
    for(int i = 0; i < 5; i++){
        if(s.charAt(i) == s.charAt(i+1)){
            return true;
        }
    }
    return valid;
}

public static boolean isValidPW2(int i){
    String pw = Integer.toString(i);
    boolean valid = true;
    if(rule3(pw) == false || doesntDecrease(pw) == false){
        valid = false;
    }

    return valid;
}


    public static void main(String[] args) {
        int start = 178416;
        int end =   676461;
        
        int count = 0;
        for(int x = start; x <= end; x++){
            if(isValidPW(x)){
                count++;
                System.out.println(x);
            }
        }
        
        
        System.out.println("# Valid Passwords: " + count); // Answer is 1650

    }

}