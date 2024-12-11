import java.util.*;

class Round {
    private int attempts;
    private int score;
    private final Scanner scanner = new Scanner(System.in);

    void start() 
    {
        attempts = 1;
        System.out.println("Welcome to a New Round!");
        System.out.println("You have 6 attempts to guess a number between 0 and 10.");
        
        int randomNumber = (int) (Math.random() * 11);

        while (attempts <= 6) 
        {
            System.out.print("Enter your guess: ");
            int guess;

            try {
                guess = scanner.nextInt();
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 10.");
                scanner.next();
                continue;
            }

            if (guess < 0 || guess > 10) 
            {
                System.out.println("Please enter a number between 0 and 10.");
                continue;
            }

            if (guess == randomNumber) 
            {
                System.out.println("Congratulations! You guessed the correct number.");
                calculateScore(); 
                return;
            } else if (guess < randomNumber) 
            {
                System.out.println("Your guess is too low.");
            } else 
            {
                System.out.println("Your guess is too high.");
            }

            attempts++;
        }

        System.out.println("Oops! You've used all attempts. The correct number was: " + randomNumber);
        score = 0;
    }

    private void calculateScore() 
    {
        score = Math.max(0, 6 - attempts + 1);
        System.out.println("Your score: " + score);
    }

    int getScore() 
    {
        return score;
    }

    void close() 
    {
        scanner.close();
    }
}

public class GuessingGame 
{
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        System.out.println("Welcome to The Guessing Number Game!");
        GuessingGame game = new GuessingGame();
        game.playGame();
    }

    void playGame() 
    {
        Round round = new Round();
        int totalScore = 0;

        while (true) 
        {
            System.out.println("Want to play? Press 1 for Yes, 0 for No:");
            int choice;

            try {
                choice = scanner.nextInt();
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 or 0.");
                scanner.next();
                continue;
            }

            if (choice == 1) {
                round.start();
                totalScore += round.getScore(); 
            } else if (choice == 0) {
                System.out.println("Thanks for playing! Your total score: " + totalScore);
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 0.");
            }
        }

        scanner.close();
        round.close();
    }
}
