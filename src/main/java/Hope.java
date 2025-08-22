import java.util.Scanner;

public class Hope {
    public static void main(String[] args) {
        boolean online = true;
        System.out.println("Hello! I'm Hope \n" + "What can i do for you? \n");
        while(online) {
            Scanner userInput = new Scanner(System.in);
            String instruction = userInput.nextLine();
            if(instruction.equals("bye")) {
                break;
            }
            System.out.println(instruction +"\n");
        }

        System.out.println("See you soon! \n");

    }
}
