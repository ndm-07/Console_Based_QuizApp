import java.util.*;

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("********************* Welcome to NBC (Nadeem Banayega Karodpathi) *********************");
        
        System.out.print("\nPlease Enter Your Name: ");
        String name = sc.nextLine();
        System.out.println("{---Player Registered Successfully---}");
        
        Player p = new Player(name);
        System.out.println("\n\nWelcome to the Game " + name + "...!");
        try {
        	new Game().start(p);
        }
        catch (Exception e) {
        	System.out.println("Issue while initializing the game");
        }

    }
}