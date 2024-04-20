import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SwimmingBookingSystem {
    private ArrayList<Lesson> lessons;
    private ArrayList<Learner> learners;
    private ArrayList<Coach> coaches;

    public SwimmingBookingSystem() {
        this.lessons = new ArrayList<>();
        this.learners = new ArrayList<>();
        this.coaches = new ArrayList<>();
        initializeData();
        generateTimetable();
    }

    private void initializeData() {
        coaches.add(new Coach("Helen"));
        coaches.add(new Coach("John"));
        coaches.add(new Coach("Lilli"));

        learners.add(new Learner("Alice", "Female", 7, "123-456-7890", 2));
        learners.add(new Learner("Bob", "Male", 5, "987-654-3210", 0));
    }
}
