import java.util.*;

public class CLI {
	private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String str1 = readLine("Type some text: ");
        System.out.println("Text read is: " + str1);
        int i = readPosInt("Type an integer: ");
        System.out.println("Integer read is: " + i);
        String str2 = readLine("Type some text again: ");
        System.out.println("Text read is: " + str2);

    }
    private static String readLine(String str){
        System.out.print(str);
        String line1=input.nextLine();//the string
        return line1;
    }
    private static int readPosInt(String str) {
        int integer;
        while (true){
            try {
                System.out.print(str);
                integer = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();//read the newline character
                System.out.println("You must type an integer!");
                continue;//keep loop
            }
            if(integer>=0){
                input.nextLine();//read the newline character
                return integer;
            }
            System.out.println("Positive integers only!");
        }
    }

}
