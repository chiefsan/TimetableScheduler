import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Application {
    private JPanel Panel;
    private JButton buttonRoomAdd;
    private JButton buttonRoomSub;
    private JTextField textFieldRoom;
    private JTextField textFieldTimeSlot;
    private JButton buttonTimeSlotAdd;
    private JButton buttonTimeSlotSub;
    private JTextField textFieldProf;
    private JTextField textFieldRoomChoice1;
    private JTextField textFieldRoomChoice2;
    private JTextField textFieldTimeSlotChoice1;
    private JTextField textFieldTimeSlotChoice2;
    private JButton buttonInputDataAdd;
    private JButton buttonInputDataSub;
    private JButton buttonSchedule;
    private JLabel labelRoom;
    private JLabel labelTimeSlot;
    private JLabel labelInputData;
    private JLabel labelOutput;
    private JTextField textField1;
    private Set<String> rooms;
    private ArrayList<String> insertedRooms;
    private ArrayList<String> insertedTimeSlots;
    private ArrayList<Preference> insertedPreferences;
    private Set<String> timeSlots;
    private Set<Preference> preferences;
    private Scheduler s;

    public Application() {
        buttonRoomAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textFieldRoom.getText();
                if(s.equals("Rooms") || s.equals("") || rooms.contains(s))
                    return;
                rooms.add(s);
                System.out.println(s);
                insertedRooms.add(s);
                textFieldRoom.setText("Rooms");
                s = "<html>No of rooms : " + rooms.size() + "<br/>" + "Last added : " + s+"</html>";
                labelRoom.setText(s);
            }
        });
        textFieldRoom.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                textFieldRoom.setText("");
            }
        });
        buttonRoomSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rooms.size()==0) {
                    labelRoom.setText("Rooms");
                    return;
                }
                String s = insertedRooms.get(insertedRooms.size()-1);
                rooms.remove(s);
                insertedRooms.remove(insertedRooms.size()-1);
                s = "<html>No of rooms : " + rooms.size() + "<br/>" + "Last removed : " + s+"</html>";
                labelRoom.setText(s);
            }
        });
        buttonTimeSlotAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textFieldTimeSlot.getText();
                if(s.equals("Time Slots") || s.equals("") || timeSlots.contains(s))
                    return;
                timeSlots.add(s);
                System.out.println(s);
                insertedTimeSlots.add(s);
                textFieldTimeSlot.setText("Time Slots");
                s = "<html>No of slots : " + timeSlots.size() + "<br/>" + "Last added : " + s+"</html>";
                labelTimeSlot.setText(s);
            }
        });
        textFieldTimeSlot.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                textFieldTimeSlot.setText("");
            }
        });
        buttonTimeSlotSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeSlots.size()==0){
                    labelTimeSlot.setText("Time Slots");
                    return;
                }
                String s = insertedTimeSlots.get(insertedTimeSlots.size()-1);
                timeSlots.remove(s);
                insertedTimeSlots.remove(insertedTimeSlots.size()-1);
                s = "<html>No of slots : " + timeSlots.size() + "<br/>" + "Last removed : " + s+"</html>";
                labelTimeSlot.setText(s);
            }
        });
        textFieldProf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                textFieldProf.setText("");
            }
        });
        textFieldRoomChoice1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                textFieldRoomChoice1.setText("");
            }
        });
        textFieldRoomChoice2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldRoomChoice2.setText("");
            }
        });
        textFieldTimeSlotChoice1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                textFieldTimeSlotChoice1.setText("");
            }
        });
        textFieldTimeSlotChoice2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                textFieldTimeSlotChoice2.setText("");
            }
        });
        buttonInputDataAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prof = textFieldProf.getText();
                String roomChoice1 = textFieldRoomChoice1.getText();
                String roomChoice2 = textFieldRoomChoice2.getText();
                String timeSlotChoice1 = textFieldTimeSlotChoice1.getText();
                String timeSlotChoice2 = textFieldTimeSlotChoice2.getText();

                Preference def = new Preference("Professor", "Room preference 1", "Room preference 2", "Time slot preference 1", "Time slot preference 2");
                Preference temp = new Preference(prof, roomChoice1, roomChoice2, timeSlotChoice1, timeSlotChoice2);
                if(temp.equals(def) || preferences.contains(temp) || prof.length()==0 || roomChoice1.length()==0 || roomChoice2.length()==0 || timeSlotChoice1.length()==0 || timeSlotChoice2.length()==0)
                    return;
                preferences.add(temp);
                System.out.println(temp);
                insertedPreferences.add(temp);
                textFieldProf.setText("Professor");
                textFieldRoomChoice1.setText("Room preference 1");
                textFieldRoomChoice2.setText("Room preference 2");
                textFieldTimeSlotChoice1.setText("Time slot preference 1");
                textFieldTimeSlotChoice2.setText("Time slot preference 2");
                String s = "<html>Last added : <br/>" + temp.professor + "<br/>" + temp.roomChoice1 + "<br/>" + temp.roomChoice2 + "<br/>" + temp.timeSlotChoice1 + "<br/>" + temp.timeSlotChoice2 + "</html>";
                labelInputData.setText(s);
            }
        });
        buttonInputDataSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (preferences.size()==0) {
                    labelInputData.setText("Preferences");
                    return;
                }
                Preference p = insertedPreferences.get(insertedPreferences.size()-1);
                preferences.remove(p);
                insertedPreferences.remove(insertedPreferences.size()-1);
                String s = "<html>Last removed : " + p+"</html>";
                labelInputData.setText(s);
            }
        });
        buttonSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Assignment> schedule = s.run(preferences, 100, timeSlots, rooms, 1000);
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(new File("schedule.csv"));
                } catch (FileNotFoundException se) {
                    se.printStackTrace();
                }
                pw.write("Prof, Room, TimeSlot\n");
                for (Assignment i: schedule) {
                    System.out.println(i);
                    pw.write(i.toString());
                }
                pw.close();
                labelOutput.setText("<html>Schedule is available in <a href=\"file:///D:\\Don'tknowwheretoput\\java\\TimetableScheduler\\schedule.csv\">schedule.csv</a></html>");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Application");
        frame.setContentPane(new Application().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Panel = new JPanel();
        rooms = new HashSet();
        timeSlots = new HashSet();
        preferences = new HashSet();
        insertedRooms = new ArrayList<String>();
        insertedTimeSlots = new ArrayList<String>();
        insertedPreferences = new ArrayList<Preference>();
        s = new Scheduler();
    }
}
