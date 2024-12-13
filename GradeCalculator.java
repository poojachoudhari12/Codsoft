import java.util.Scanner;

class Grade {
    Scanner sc;
    int[] marks;
    double Total = 0;
    double Avg = 0;
    char grd;

    Grade(Scanner scanner) 
    {
        this.sc = scanner;
    }

    void Marks() 
    {
        Total = 0; 
        System.out.println("Enter your number of subjects: ");
        int n = sc.nextInt();

        marks = new int[n];
        System.out.println("Enter your marks for each subject (0-100):");
        for (int i = 0; i < n; i++) 
        {
            marks[i] = sc.nextInt();
            if (marks[i] < 0 || marks[i] > 100) 
            {
                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                i--;
            } else 
            {
                Total += marks[i];
            }
        }
        Avg = Total / n;

       
        switch ((int) Avg / 10) 
        {
            case 10:
            case 9: grd = 'A'; break;
            case 8: grd = 'B'; break;
            case 7: grd = 'C'; break;
            case 6: grd = 'D'; break;
            case 5: grd = 'E'; break;
            default: grd = 'F';
        }
    }

    void Display() 
    {
        System.out.println("Your total marks: " + Total);
        System.out.printf("Your Average of marks is: %.2f%n", Avg);
        System.out.println("Your Grade: " + grd);
    }
}

public class GradeCalculator 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Student Grade Calculator");
        Grade g = new Grade(scanner);

        while (true) 
        {
            System.out.println("Want to calculate your Grade? If yes, press 1. If no, press 0: ");
            int choice;
            try {
                choice = scanner.nextInt();
                if (choice == 1) 
                {
                    g.Marks();
                    g.Display();
                } else if (choice == 0) 
                {
                    System.out.println("Thank you for using the Grade Calculator. Goodbye!");
                    return; 
                } else 
                {
                    System.out.println("Invalid choice. Please enter 1 or 0.");
                }
            } 
            catch (Exception e) {
                System.out.println("Invalid input. Please press 0 or 1.");
                scanner.next();
            }
        }
    }
}
