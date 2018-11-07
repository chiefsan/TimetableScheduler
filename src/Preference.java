public class Preference {
    public String professor;
    public String roomChoice1;
    public String roomChoice2;
    public String timeSlotChoice1;
    public String timeSlotChoice2;

    public Preference(String professor, String roomChoice1, String roomChoice2, String timeSlotChoice1, String timeSlotChoice2) {
        this.professor = professor;
        this.roomChoice1 = roomChoice1;
        this.roomChoice2 = roomChoice2;
        this.timeSlotChoice1 = timeSlotChoice1;
        this.timeSlotChoice2 = timeSlotChoice2;
    }

    @Override

    public String toString() {
//        return "Preference{" +
//                "professor='" + professor + '\'' +
//                ", roomChoice1='" + roomChoice1 + '\'' +
//                ", roomChoice2='" + roomChoice2 + '\'' +
//                ", timeSlotChoice1='" + timeSlotChoice1 + '\'' +
//                ", timeSlotChoice2='" + timeSlotChoice2 + '\'' +
//                '}';
        return
                professor + '\n' +
                roomChoice1 + '\n' +
                roomChoice2 + '\n' +
                timeSlotChoice1 + '\n' +
                timeSlotChoice2 + '\n'
                ;
    }
}