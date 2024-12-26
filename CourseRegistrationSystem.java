import java.util.*;

class Course 
{
    String code;
    String title;
    String description;
    int capacity;
    int registeredCount;
    String schedule;

    Course(String code, String title, String description, int capacity, String schedule) 
    {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.registeredCount = 0;
        this.schedule = schedule;
    }

    boolean hasSlots() 
    {
        return registeredCount < capacity;
    }

    void registerStudent() 
    {
        registeredCount++;
    }

    void dropStudent() 
    {
        registeredCount--;
    }

    @Override
    public String toString() 
    {
        return code + " - " + title + " (" + registeredCount + "/" + capacity + " slots used) \n" +
                "Description: " + description + "\nSchedule: " + schedule + "\n";
    }
}

class Student 
{
    int id;
    String name;
    List<Course> registeredCourses;

    Student(int id, String name) 
    {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerCourse(Course course) 
    {
        if (registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
        } else if (course.hasSlots()) {
            registeredCourses.add(course);
            course.registerStudent();
            System.out.println("Registered successfully for " + course.title);
        } else {
            System.out.println("No slots available for " + course.title);
        }
    }

    void dropCourse(Course course) 
    {
        if (registeredCourses.contains(course)) 
        {
            registeredCourses.remove(course);
            course.dropStudent();
            System.out.println("Dropped course " + course.title + " successfully.");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    void viewRegisteredCourses() 
    {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.code + " - " + course.title);
            }
        }
    }
}


public class CourseRegistrationSystem 
{
    static Map<String, Course> courses = new HashMap<>();
    static Map<Integer, Student> students = new HashMap<>();

    public static void main(String[] args) 
    {
        initializeCourses();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Course Registration System!");
        while (true) 
        {
            System.out.println("\nMenu:");
            System.out.println("1. List all courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. View registered courses");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) 
            {
                case 1 -> listCourses();
                case 2 -> registerForCourse(scanner);
                case 3 -> dropCourse(scanner);
                case 4 -> viewStudentCourses(scanner);
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void initializeCourses() 
    {
        courses.put("CS101", new Course("CS101", "Intro to Programming", "Learn the basics of programming.", 50, "Mon 10-12"));
        courses.put("CS102", new Course("CS102", "Data Structures", "Learn about data organization.", 40, "Wed 10-12"));
        courses.put("CS103", new Course("CS103", "Database Systems", "Learn database design.", 30, "Fri 10-12"));
    }

    static void listCourses() 
    {
        System.out.println("Available Courses:");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    static void registerForCourse(Scanner scanner) 
    {
        System.out.print("Enter your Student ID: ");
        int id = scanner.nextInt();
        Student student = students.computeIfAbsent(id, key -> createStudent(scanner, key));

        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.next();
        Course course = courses.get(courseCode);

        if (course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Invalid course code.");
        }
    }

    static void dropCourse(Scanner scanner) 
    {
        System.out.print("Enter your Student ID: ");
        int id = scanner.nextInt();
        Student student = students.get(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.next();
        Course course = courses.get(courseCode);

        if (course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Invalid course code.");
        }
    }

    static void viewStudentCourses(Scanner scanner) 
    {
        System.out.print("Enter your Student ID: ");
        int id = scanner.nextInt();
        Student student = students.get(id);

        if (student != null) {
            student.viewRegisteredCourses();
        } else {
            System.out.println("Student not found.");
        }
    }

    static Student createStudent(Scanner scanner, int id) 
    {
        System.out.print("Enter your name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        return new Student(id, name);
    }
}
