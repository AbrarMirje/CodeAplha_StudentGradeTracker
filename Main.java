import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> studentList = userInput();
        calculateAverage(studentList);
        highestScorer(studentList);
        lowestScorer(studentList);
    }

//    Getting grads as input
    static ArrayList<Student> userInput() {
        ArrayList<Student> studentList = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of students: ");
            int range = scanner.nextInt();
            scanner.nextLine();

            if (range <= 0) {
                System.out.println("Enter a positive number greater than zero.");
                return studentList;
            }

            for (int i = 0; i < range; i++) {
                System.out.print("Enter grade for student " + (i + 1) + " or 'x' to exit: ");
                String gradeInput = scanner.nextLine();

                if (gradeInput.equalsIgnoreCase("x")) {
                    break;
                } else {
                    try {
                        int studentGrade = Integer.parseInt(gradeInput);
                        if (studentGrade < 0 || studentGrade > 100) {
                            System.out.println("Enter grade between 0 and 100.");
                            i--;
                        } else {
                            studentList.add(new Student(studentGrade));
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid input. Please enter a valid integer or 'x' to exit.");
                        i--;
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input mismatch. Please enter a valid integer.");
        }
        return studentList;
    }

//    Calculating an average of the students grads
    static void calculateAverage(ArrayList<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("No grades to calculate average.");
            return;
        }

        int sum = 0;
        for (Student student : studentList) {
            sum += student.grade();
        }

        double average = (double) sum / studentList.size();
        System.out.println("Average grade: " + average);
    }

//    Finding the highest scorer
    static void highestScorer(ArrayList<Student> studentArrayList){
        if (studentArrayList.isEmpty()) {
            System.out.println("No grades to calculate highest scorer");
            return;
        }

        Student highestScorer = studentArrayList.get(0);
        for (Student student : studentArrayList) {
            if (student.grade() > highestScorer.grade()) {
                highestScorer = student;
            }
        }
        System.out.println("Highest Scorer Grade: " + highestScorer.grade());
    }

//    Finding the lowest scorer
    static void lowestScorer(ArrayList<Student> studentArrayList){
        if (studentArrayList.isEmpty()) {
            System.out.println("No grades to calculate lowest scorer");
            return;
        }

        Student lowestScorer = studentArrayList.get(0);
        for (Student student : studentArrayList) {
            if (student.grade() < lowestScorer.grade()) {
                lowestScorer = student;
            }
        }
        System.out.println("Lowest Scorer Grade: " + lowestScorer.grade());
    }
}