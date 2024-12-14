import java.util.Scanner;

public class QuizGame {
   
    static class Question {
        String questionText;
        String[] options;
        int correctOption; 

        public Question(String questionText, String[] options, int correctOption) {
            this.questionText = questionText;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Array of questions
        Question[] questions = {
            new Question("What is the capital of India?", new String[]{"Delhi", "Mumbai", "Kolkata", "Chennai"}, 0),
            new Question("Which language is used for Android development?", new String[]{"Python", "Java", "C++", "Kotlin"}, 3),
            new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1)
        };

        int score = 0; 
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Game!");
        System.out.println("You have 10 seconds to answer each question. Let's begin!\n");

       
        for (int i = 0; i < questions.length; i++) {
            Question q = questions[i];
            System.out.println("Question " + (i + 1) + ": " + q.questionText);
            
            
            for (int j = 0; j < q.options.length; j++) {
                System.out.println((j + 1) + ". " + q.options[j]);
            }

           
            long startTime = System.currentTimeMillis();
            long endTime = startTime + 10000; // 10 seconds
            boolean answered = false;

            while (System.currentTimeMillis() < endTime) {
                if (scanner.hasNextInt()) { // Check if user has entered an integer
                    int answer = scanner.nextInt();
                    answered = true;

                    if (answer - 1 == q.correctOption) {
                        System.out.println("Correct!\n");
                        score++;
                    } else {
                        System.out.println("Wrong! The correct answer was: " + q.options[q.correctOption] + "\n");
                    }
                    break;
                }
            }

            if (!answered) {
                System.out.println("Time's up! The correct answer was: " + q.options[q.correctOption] + "\n");
            }

            Thread.sleep(1000); // Pause before the next question
        }

        
        System.out.println("Quiz Over!");
        System.out.println("Your final score: " + score + "/" + questions.length);

        scanner.close();
    }
}
