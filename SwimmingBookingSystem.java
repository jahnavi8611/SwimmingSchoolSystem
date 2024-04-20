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

    private void generateTimetable() {
        int lessonIdCounter = 1;
        for (int week = 1; week <= 4; week++) {
            // Monday
            addLesson(lessonIdCounter++, "Monday", "4-5pm", week, getRandomGrade(), "Helen");
            addLesson(lessonIdCounter++, "Monday", "5-6pm", week, getRandomGrade(), "John");
            addLesson(lessonIdCounter++, "Monday", "6-7pm", week, getRandomGrade(), "Helen");

            // Wednesday
            addLesson(lessonIdCounter++, "Wednesday", "4-5pm", week, getRandomGrade(), "John");
            addLesson(lessonIdCounter++, "Wednesday", "5-6pm", week, getRandomGrade(), "Helen");
            addLesson(lessonIdCounter++, "Wednesday", "6-7pm", week, getRandomGrade(), "John");

            // Friday
            addLesson(lessonIdCounter++, "Friday", "4-5pm", week, getRandomGrade(), "Helen");
            addLesson(lessonIdCounter++, "Friday", "5-6pm", week, getRandomGrade(), "John");
            addLesson(lessonIdCounter++, "Friday", "6-7pm", week, getRandomGrade(), "Helen");

            // Saturday
            addLesson(lessonIdCounter++, "Saturday", "2-3pm", week, getRandomGrade(), "Helen");
            addLesson(lessonIdCounter++, "Saturday", "3-4pm", week, getRandomGrade(), "John");
        }
    }

    private int getRandomGrade() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    public void addLesson(int lessonId, String day, String timeSlot, int week, int gradeLevel, String coachName) {
        Lesson lesson = new Lesson(lessonId, day, timeSlot, week, gradeLevel, coachName);
        lessons.add(lesson);
    }





    public static void main(String[] args) {
        SwimmingBookingSystem system = new SwimmingBookingSystem();
        int choice = 1;
        int month;
        do{
            System.out.println("-------------------------------");
            System.out.println("Swimming School Booking System");
            System.out.println("-------------------------------");
            System.out.println("1. Display timetable");
            System.out.println("2. Book a lesson");
            System.out.println("3. Change or Cancel Booking");
            System.out.println("4. Attend lesson");
            System.out.println("5. Generate Monthly Learner Report");
            System.out.println("6. Generate Monthly Coach Report");
            System.out.println("7. Register a new learner");
            System.out.println("0. Exit");
            System.out.println("Please select an option");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    system.displayTimetable();
                    break;
                case 2:
                    system.bookLesson();
                    break;
                case 3:
                    system.changeOrCancelBooking(system.lessons);
                    break;
                case 4:
                    system.attendLesson();
                    break;
                case 5:
                    System.out.println("Please enter the month (ex april - 3, Jan - 1 ...)");
                    month = scanner.nextInt();
                    scanner.nextLine();
                    system.generateMonthlyLearnerReport(month);
                    break;
                case 6:
                    System.out.println("Please enter the month (ex april - 3, Jan - 1 ...)");
                    month = scanner.nextInt();
                    scanner.nextLine();
                    system.generateMonthlyCoachReport(month);
                    break;
                case 7:
                    system.registerNewLearner();
                    break;
                case 0:
                    choice=0;
                    break;
                default:
                    System.out.println("Invalid action.");
                    break;
            }

        }while (choice != 0);


    }

    public void registerNewLearner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter learner's name:");
        String name = scanner.nextLine();
        System.out.println("Enter learner's gender:");
        String gender = scanner.nextLine();
        System.out.println("Enter learner's age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter learner's emergency contact:");
        String emergencyContact = scanner.nextLine();
        System.out.println("Enter learner's current grade level:");
        int gradeLevel = scanner.nextInt();
        scanner.nextLine();

        if (age < 4 || age > 11) {
            System.out.println("Error: Age must be between 4 and 11.");
            return;
        }

        if (gradeLevel < 0 || gradeLevel > 5) {
            System.out.println("Error: Grade level must be between 0 and 5.");
            return;
        }

        Learner newLearner = new Learner(name, gender, age, emergencyContact, gradeLevel);
        learners.add(newLearner);
        System.out.println("New learner registered successfully.");
    }

    // Function to display the timetable
    public void displayTimetable() {
        System.out.println("Timetable for 4 Weeks:");

        int lessonsPerWeek = lessons.size() / 4;
        for (int i = 0; i < 4; i++) {
            int startLessonIndex = i * lessonsPerWeek;
            int endLessonIndex = (i + 1) * lessonsPerWeek;

            System.out.println("Week " + (i + 1) + ":");
            for (int j = startLessonIndex; j < endLessonIndex && j < lessons.size(); j++) {
                Lesson lesson = lessons.get(j);
                System.out.println("id:" + lesson.getLessonId() + ", Week: " + lesson.getWeek() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot() + ", Grade: " + lesson.getGradeLevel() + ", Coach: " + lesson.getCoach());
            }
            System.out.println();
        }
    }

    // Function to book a swimming lesson
    public void bookLesson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the way to view the timetable:");
        System.out.println("1. View by day");
        System.out.println("2. View by grade level");
        System.out.println("3. View by coach's name");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                // View by day
                System.out.println("Enter the day (e.g., Monday, Wednesday):");
                String day = scanner.nextLine();
                displayTimetableByDay(day);
                break;
            case 2:
                // View by grade level
                System.out.println("Enter the grade level (0-5):");
                int gradeLevel = scanner.nextInt();
                displayTimetableByGradeLevel(gradeLevel);
                break;
            case 3:
                // View by coach's name
                System.out.println("Enter the coach's name:");
                String coachName = scanner.nextLine();
                displayTimetableByCoach(coachName);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Enter the lesson ID to book:");
        int lessonId = scanner.nextInt();
        scanner.nextLine();

        // Find the lesson
        Lesson lesson = null;
        for (Lesson l : lessons) {
            if (l.getLessonId() == lessonId) {
                lesson = l;
                break;
            }
        }

        if (lesson == null) {
            System.out.println("Lesson not found.");
            return;
        }

        // Get learner's name
        System.out.println("Enter your name:");
        String learnerName = scanner.nextLine();

        // Find the learner
        Learner learner = null;
        for (Learner l : learners) {
            if (l.getName().equalsIgnoreCase(learnerName)) {
                learner = l;
                break;
            }
        }

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }

        // Check if learner has already booked this lesson
        if (learner.getBookedLessons().contains(lessonId)) {
            System.out.println("You have already booked this lesson.");
            return;
        }

        // Check if learner can book this lesson
        if (!canBookLesson(lesson, learner)) {
            System.out.println("Cannot book this lesson. Capacity exceeded or not allowed grade level.");
            return;
        }

        // Book lesson
        lesson.addLearner(learnerName);
        learner.bookLesson(lessonId);
        // Set default status for booking ID to "booked"
        learner.setBookingStatus(lessonId, "booked");
        System.out.println("Lesson booked successfully. Your booking ID is: " + learner.getBookingIds().get(learner.getBookingIds().size() - 1));
    }

    // Function to check if a learner can book a lesson
    private boolean canBookLesson(Lesson lesson, Learner learner) {
        // Check if lesson has available slots
        if (lesson.getLearners().size() >= 4) {
            return false;
        }

        // Check if learner's grade level is allowed
        // Replace placeholder condition with actual logic based on learner's grade level
        // For example, if learner's grade level is 1 and lesson's grade level is 3,
        // then the condition should be something like (lesson.getGradeLevel() - learner.getGradeLevel()) <= 1
        if (Math.abs(lesson.getGradeLevel() - learner.getCurrentGradeLevel()) > 1) {
            return false;
        }

        return true;
    }

    private void displayTimetableByDay(String day) {
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equalsIgnoreCase(day)) {
                System.out.println("id: " + lesson.getLessonId() + ", Week: " + lesson.getWeek() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot() + ", Grade: " + lesson.getGradeLevel() + ", Coach: " + lesson.getCoach() + ", Learners: " + lesson.getLearners().size() + "/4");
            }
        }
    }

    private void displayTimetableByGradeLevel(int gradeLevel) {
        for (Lesson lesson : lessons) {
            if (lesson.getGradeLevel() == gradeLevel) {
                System.out.println("id: " + lesson.getLessonId() + ", Week: " + lesson.getWeek() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot() + ", Grade: " + lesson.getGradeLevel() + ", Coach: " + lesson.getCoach() + ", Learners: " + lesson.getLearners().size() + "/4");
            }
        }
    }

    private void displayTimetableByCoach(String coachName) {
        for (Lesson lesson : lessons) {
            if (lesson.getCoach().equalsIgnoreCase(coachName)) {
                System.out.println("id: " + lesson.getLessonId() + ", Week: " + lesson.getWeek() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot() + ", Grade: " + lesson.getGradeLevel() + ", Coach: " + lesson.getCoach() + ", Learners: " + lesson.getLearners().size() + "/4");
            }
        }
    }

    // Function to change/cancel a booking
    public void changeOrCancelBooking(ArrayList<Lesson> lessons) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of learner:");
        String learnerName = scanner.nextLine();

        // Find the learner
        Learner learner = null;
        for (Learner l : learners) {
            if (l.getName().equalsIgnoreCase(learnerName)) {
                learner = l;
                break;
            }
        }

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }

        // Check if the learner has any bookings
        if (learner.getBookedLessons().isEmpty()) {
            System.out.println("No bookings found for " + learnerName);
            return;
        }


        int bookingsCount = 0;
        // Inside the changeOrCancelBooking method
        System.out.println("Bookings for " + learnerName + ":");
        for (int i = 0; i < learner.getBookedLessons().size(); i++) {
            int lessonId = learner.getBookedLessons().get(i);
            int bookingId = learner.getBookingIds().get(i);
            String bookingStatus = learner.getBookingStatus(lessonId);
            if (bookingStatus.equalsIgnoreCase("booked")) {
                bookingsCount++;
                System.out.println("Booking ID: " + bookingId + ", Lesson ID: " + lessonId);
            }
        }

        if(bookingsCount<=0){
            System.out.println("no bookings found");
            return;
        }

        System.out.println("Enter the booking ID to change/cancel booking:");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        // Find the index of booking ID
        int index = learner.getBookingIds().indexOf(bookingId);
        if (index == -1) {
            System.out.println("Booking ID not found.");
            return;
        }

        // Get the lesson ID associated with the booking ID
        int lessonId = learner.getBookedLessons().get(index);

        // Find the lesson
        Lesson lesson = null;
        for (Lesson l : lessons) {
            if (l.getLessonId() == lessonId) {
                lesson = l;
                break;
            }
        }

        if (lesson == null) {
            System.out.println("Lesson not found.");
            return;
        }

        System.out.println("Do you want to change (X) or cancel (C) the booking?");
        String action = scanner.nextLine().toUpperCase();

        switch (action) {
            case "C":
                lesson.removeLearner(learner.getName());
                learner.cancelLesson(lessonId, lessons);
                learner.setBookingStatus(lessonId, "cancelled");
                System.out.println("Booking cancelled successfully.");
                break;
            case "X":
                // Display available lessons
                System.out.println("Available lessons:");
                displayTimetable();

                // Prompt for new lesson ID
                System.out.println("Enter the ID of the new lesson:");
                int newLessonId = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                // Find the new lesson
                Lesson newLesson = null;
                for (Lesson l : lessons) {
                    if (l.getLessonId() == newLessonId) {
                        newLesson = l;
                        break;
                    }
                }

                if (newLesson == null) {
                    System.out.println("New lesson not found.");
                    return;
                }

                // Check if learner can book this lesson
                if (!canBookLesson(newLesson, learner)) {
                    System.out.println("Cannot book this lesson. Capacity exceeded or not allowed grade level.");
                    return;
                }

                // Remove learner from current lesson and add to new lesson
                lesson.removeLearner(learner.getName());
                newLesson.addLearner(learner.getName());
                // Update the lesson ID associated with the booking ID
                learner.updateLessonId(bookingId, newLessonId);

                System.out.println("Booking changed successfully.");
                break;
            default:
                System.out.println("Invalid action.");
                break;
        }
    }

    // Function to display learner's bookings
    private void displayLearnerBookings(String learnerName) {
        for (Lesson lesson : lessons) {
            if (lesson.getLearners().contains(learnerName)) {
                System.out.println("ID: " + lesson.getLessonId() + ", Week: " + lesson.getWeek() + ", Day: " + lesson.getDay() + ", Time: " + lesson.getTimeSlot() + ", Grade: " + lesson.getGradeLevel() + ", Coach: " + lesson.getCoach());
            }
        }
    }

    // Function to attend a swimming lesson
    public void attendLesson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of learner:");
        String learnerName = scanner.nextLine();

        // Find the learner
        Learner learner = null;
        for (Learner l : learners) {
            if (l.getName().equalsIgnoreCase(learnerName)) {
                learner = l;
                break;
            }
        }

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }

        // Check if the learner has any bookings
        if (learner.getBookedLessons().isEmpty()) {
            System.out.println("No bookings found for " + learnerName);
            return;
        }


        // Inside the changeOrCancelBooking method
        System.out.println("Bookings for " + learnerName + ":");
        for (int i = 0; i < learner.getBookedLessons().size(); i++) {
            int lessonId = learner.getBookedLessons().get(i);
            int bookingId = learner.getBookingIds().get(i);
            String bookingStatus = learner.getBookingStatus(lessonId);
            if (bookingStatus.equalsIgnoreCase("booked")) {
                System.out.println("Booking ID: " + bookingId + ", Lesson ID: " + lessonId);
            }
        }


        System.out.println("Enter the booking ID to attend:");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        // Get the lesson ID associated with the booking ID
        int lessonId = learner.getLessonId(bookingId);

        if (lessonId == -1) {
            System.out.println("Invalid booking ID.");
            return;
        }

        // Find the lesson
        Lesson lesson = null;
        for (Lesson l : lessons) {
            if (l.getLessonId() == lessonId) {
                lesson = l;
                break;
            }
        }

        if (lesson == null) {
            System.out.println("Lesson not found.");
            return;
        }

        // Check if learner has already attended this lesson
        if (learner.getBookingStatus(bookingId).equalsIgnoreCase("attended")) {
            System.out.println("You have already attended this lesson.");
            return;
        }


        // Attend lesson
//        lesson.addLearner(learner.getName());
        // Change booking status to "attended"
        lesson.removeLearner(learner.getName());
        learner.cancelLesson(lessonId, lessons);
        learner.setBookingStatus(lessonId, "attended");
        System.out.println("Lesson attended successfully.");

        // Check if learner's current grade level needs to be updated
        if (lesson.getGradeLevel() > learner.getCurrentGradeLevel()) {
            learner.setCurrentGradeLevel(lesson.getGradeLevel());
            System.out.println("Congratulations! Your grade level has been updated to " + lesson.getGradeLevel());
        }

        // Provide coach review
        System.out.println("Please provide a review for the coach (optional):");
        String coachReview = scanner.nextLine();

        System.out.println("Please provide a rating for the coach (1-5):");
        int coachRating = scanner.nextInt();
        scanner.nextLine();

        if (coachRating < 1 || coachRating > 5) {
            System.out.println("Invalid rating. Rating must be between 1 and 5.");
            return;
        }

        // Record coach review and rating
        for (Coach coach : coaches) {
            if (coach.getName().equalsIgnoreCase(lesson.getCoach())) {
                coach.addReview(new Review(coachReview, coachRating));
                System.out.println("Coach review and rating recorded successfully.");
                break;
            }
        }
    }

    public void generateMonthlyLearnerReport(int month) {
        System.out.println("Monthly Learner Report for Month " + month + ":");

        for (Learner learner : learners) {
            System.out.println("Learner: " + learner.getName());
            int bookedCount = 0;
            int cancelledCount = 0;
            int attendedCount = 0;

            // Count booked, cancelled, and attended lessons
            for (Lesson lesson : lessons) {
                if (learner.hasBookedLesson(lesson.getLessonId())) {
                    String bookingStatus = learner.getBookingStatus(lesson.getLessonId());
                    if (bookingStatus.equalsIgnoreCase("booked")) {
                        bookedCount++;
                    } else if (bookingStatus.equalsIgnoreCase("cancelled")) {
                        cancelledCount++;
                    } else if (bookingStatus.equalsIgnoreCase("attended")) {
                        attendedCount++;
                    }
                }
            }

            System.out.println("Booked: " + bookedCount);
            System.out.println("Cancelled: " + cancelledCount);
            System.out.println("Attended: " + attendedCount);
            System.out.println();
        }
    }



    // Function to generate monthly coach report
    public void generateMonthlyCoachReport(int month) {
        System.out.println("Monthly Coach Report for Month " + month + ":");

        for (Coach coach : coaches) {
            System.out.println("Coach: " + coach.getName());
            double averageRating = coach.getAverageRating();

            if (averageRating > 0.0) {
                System.out.println("Average Rating: " + averageRating);
            } else {
                System.out.println("No ratings available.");
            }
            System.out.println();
        }
    }


    // Helper method to find a learner by name
    private Learner findLearnerByName(String name) {
        for (Learner learner : learners) {
            if (learner.getName().equalsIgnoreCase(name)) {
                return learner;
            }
        }
        return null;
    }

    // Helper method to generate a random rating
    private int getRandomRating() {
        Random random = new Random();
        return random.nextInt(5) + 1; // Generates a random rating from 1 to 5
    }



}
