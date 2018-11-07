class Assignment extends Preference {
    String room;
    String timeSlot;

    public Assignment(String professor, String roomChoice1, String roomChoice2, String timeSlotChoice1, String timeSlotChoice2, String room, String timeSlot) {
        super(professor, roomChoice1, roomChoice2, timeSlotChoice1, timeSlotChoice2);
        this.room = room;
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
//        return "Assignment{" +
//                "professor='" + professor + '\'' +
//                ", roomChoice1='" + roomChoice1 + '\'' +
//                ", roomChoice2='" + roomChoice2 + '\'' +
//                ", timeSlotChoice1='" + timeSlotChoice1 + '\'' +
//                ", timeSlotChoice2='" + timeSlotChoice2 + '\'' +
//                ", room='" + room + '\'' +
//                ", timeSlot='" + timeSlot + '\'' +
//                '}';
        return professor +
//                ", " + roomChoice1 +
//                ", " + roomChoice2 +
//                ", " + timeSlotChoice1 +
//                ", " + timeSlotChoice2 +
                ", " + room +
                ", " + timeSlot +
                "\n"
                ;
    }
}