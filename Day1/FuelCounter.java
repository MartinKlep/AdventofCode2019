public class FuelCounter{
// This is part 1 of Advent of Code 2019. This class contains a method to convert the input into a useful array and a method to calculate the "fuel" needed for the task.

public static String[] procInput(final String input) {
        final String[] output = input.split(" ");
        return output;
    }

    public static int fuelcalc(final String[] modules) {
        int fuel = 0;
        int temp;
        for (int i = 0; i < modules.length; i++) {
            temp = Integer.parseInt(modules[i]);
            temp = temp / 3;
            temp -= 2;
            fuel += temp;
        }

        return fuel;
    }

    public static int calcFuel(int mass) {
        mass = mass / 3 -2;
        return mass;
    }

    public static int calcFuelComplete(final String[] modules) {
        int fuel = 0;
        for (int i = 0; i < modules.length; i++) {
            int tempFuel = calcFuel(Integer.parseInt(modules[i]));
            while (tempFuel > 0) {
                fuel += tempFuel;
                tempFuel = calcFuel(tempFuel);
            }
        }
        return fuel;
    }

public static void main(final String[] args){
   String[] modules = new String[100];
   modules = procInput("93912 138996 112824 110011 139024 132292 74029 81664 138077 109614 121056 136338 132771 86611 131526 123101 61315 93900 62070 97957 67168 119464 119066 111076 56856 144203 109400 120187 57915 143353 71308 67695 141275 106552 136209 86990 98969 57207 99103 71940 63145 91765 121095 139700 128851 77138 66712 91318 96924 132235 99897 67479 87996 121100 55411 61715 130658 121030 141445 83939 90402 121107 59618 120112 58140 103514 90538 55552 142739 61770 147374 80038 128830 93328 52369 71801 144536 147140 118213 128056 92155 114384 89234 124451 94214 79174 108427 111041 96715 128414 62521 93897 107428 90637 126176 78676 69504 93663 80869 124230");

    System.out.println("Result Part 1: " + fuelcalc(modules));  // Result is 3374289
    System.out.println("----------------------------------------------------");
   // String[] test = {"1969", "100756"};
    System.out.println("Result Part 2: " + calcFuelComplete(modules));
   
}       


    
}

