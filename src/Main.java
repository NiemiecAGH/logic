import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write the matching character set as a single word: ");
        String characterInput = scanner.nextLine();
        System.out.println("Please write the fraze in which you want to find the characters: ");
        String input = scanner.nextLine();
        Identifier identifier = new Identifier(characterInput.toLowerCase(), input.toLowerCase());
        System.out.println(identifier.getOutput());
    }
}
