import java.util.ArrayList;
import java.util.HashMap;

public class Learner {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int currentGradeLevel;
    private ArrayList<Integer> bookedLessons;
    private ArrayList<Integer> bookingIds;
    private HashMap<Integer, String> bookingStatus;

    public Learner(String name, String gender, int age, String emergencyContact, int currentGradeLevel) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.currentGradeLevel = currentGradeLevel;
        this.bookedLessons = new ArrayList<>();
        this.bookingIds = new ArrayList<>();
        this.bookingStatus = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public int getCurrentGradeLevel() {
        return currentGradeLevel;
    }

    public void setCurrentGradeLevel(int newGradeLevel) {
        this.currentGradeLevel = newGradeLevel;
    }

    public ArrayList<Integer> getBookedLessons() {
        return bookedLessons;
    }


    public ArrayList<Integer> getBookingIds() {
        return bookingIds;
    }

    public void bookLesson(int lessonId) {
        // Generate a unique booking ID
        int bookingId = generateBookingId();

        // Add the lesson ID to the list of booked lessons
        bookedLessons.add(lessonId);

        // Add the booking ID to the list of booking IDs
        bookingIds.add(bookingId);
        // Set default status for booking ID to "booked"
        bookingStatus.put(lessonId, "booked");
    }

    public void setBookingStatus(int lessonId, String status) {
        bookingStatus.put(lessonId, status);
    }

    public String getBookingStatus(int lessonId) {
        return bookingStatus.getOrDefault(lessonId, "");
    }





    public void cancelLesson(int lessonId, ArrayList<Lesson> lessons) {
        // Find the index of the lesson ID
        int index = bookedLessons.indexOf(lessonId);

        // If lesson ID is found, remove it from bookedLessons and the corresponding booking ID from bookingIds
        if (index != -1) {
            int bookingId = bookingIds.get(index);
//            bookedLessons.remove(index);
//            bookingIds.remove(index);
            // Remove the corresponding booking status
//            bookingStatus.remove(lessonId);
            // Update the booking status to "cancelled"
            bookingStatus.put(lessonId, "cancelled");
        }
    }

    public int getBookingId(int lessonId) {
        // Get booking ID associated with lesson ID
        int index = bookedLessons.indexOf(lessonId);
        return index != -1 ? bookingIds.get(index) : -1;
    }

    public int getLessonId(int bookingId) {
        // Get lesson ID associated with booking ID
        int index = bookingIds.indexOf(bookingId);
        return index != -1 ? bookedLessons.get(index) : -1;
    }



    public boolean hasBookedLesson(int lessonId) {
        return bookedLessons.contains(lessonId);
    }

    private int generateBookingId() {

        return (int) (Math.random() * 1000) + 1000; // Example: Random booking ID between 1000 and 1999
    }

    public void updateLessonId(int bookingId, int newLessonId) {
        int index = bookingIds.indexOf(bookingId);
        if (index != -1) {
            bookedLessons.set(index, newLessonId);
            bookingStatus.put(newLessonId, "booked");
        }
    }

}
