import java.util.ArrayList;

public class Lesson {
    private String day;
    private String timeSlot;
    private int gradeLevel;
    private String coach;
    private ArrayList<String> learners;
    private boolean isCancelled;
    private int numLearners; // Number of learners booked for the lesson
    private int lessonId;
    private int week; // Week number

    public Lesson(int lessonId, String day, String timeSlot, int week, int gradeLevel, String coach) {
        this.day = day;
        this.timeSlot = timeSlot;
        this.week = week;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.learners = new ArrayList<>();
        this.isCancelled = false;
        this.numLearners = 0;
        this.lessonId = lessonId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public String getDay() {
        return day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public int getWeek() {
        return week;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public String getCoach() {
        return coach;
    }

    public ArrayList<String> getLearners() {
        return learners;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancelLesson() {
        isCancelled = true;
    }

    public int getNumLearners() {
        return numLearners;
    }

    public void addLearner(String learnerName) {
        learners.add(learnerName);
        numLearners++;
    }

    public void removeLearner(String learnerName) {
        learners.remove(learnerName);
        numLearners--;
    }
}
