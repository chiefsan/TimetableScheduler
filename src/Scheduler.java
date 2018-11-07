import java.util.Collections;
import java.util.Set;
import java.util.ArrayList;

public class Scheduler {
    public ArrayList<Assignment> run(Set<Preference> courseSet, int popSize, Set<String> timeSlotSet, Set<String> roomSet, int maxIter) {
        int noOfTimeSlots = timeSlotSet.size();
        int noOfRooms = roomSet.size();
        int noOfCourses = courseSet.size();

        ArrayList<String> timeSlots = new ArrayList<String>();
        for (String s: timeSlotSet) {
            timeSlots.add(s);
        }
        ArrayList<String> rooms = new ArrayList<String>();
        for (String s: roomSet) {
            rooms.add(s);
        }
        ArrayList<Preference> courses = new ArrayList<Preference>();
        for (Preference p: courseSet) {
            courses.add(p);
            System.out.println(p.professor);
        }
        ArrayList<Assignment> population = new ArrayList<Assignment>();
        for (int i=0; i<popSize; i++) {
            for (int j=0; j<noOfCourses; j++) {
                String randomTimeSlot = timeSlots.get((int)(Math.random()*noOfTimeSlots));
                String randomRoom=  rooms.get((int)(Math.random()*noOfRooms));
                String professor = courses.get(j).professor;
                String roomChoice1 = courses.get(j).roomChoice1;
                String roomChoice2 = courses.get(j).roomChoice2;
                String timeSlotChoice1 = courses.get(j).timeSlotChoice1;
                String timeSlotChoice2 = courses.get(j).timeSlotChoice2;

                population.add(new Assignment(professor, roomChoice1, roomChoice2, timeSlotChoice1, timeSlotChoice2, randomRoom, randomTimeSlot));
            }
        }
        ArrayList fit = fitness(population, popSize, noOfCourses, rooms, timeSlots);
        int genNo = 0;

        while (genNo<maxIter) {
            System.out.println("Generation number : " + genNo);
            genNo+=1;
            ArrayList<Assignment> newPopulation = new ArrayList<Assignment>();
            ArrayList tempFit = (ArrayList)fit.clone();
            Collections.sort(tempFit, Collections.reverseOrder());
            ArrayList toBeRetained = new ArrayList();
            for (int i=0; i<(int)(popSize/10); i++) {
                toBeRetained.add(tempFit.get(i));
            }
            ArrayList maxIndices = new ArrayList();
            Assignment temp;
            for (int j=0; j<(int)(popSize/10); j++) {
                int maxIndex = tempFit.indexOf(toBeRetained.get(j));
                for (int k=0;k<noOfCourses; k++) {
                    temp = (Assignment)population.get(maxIndex * noOfCourses + k);
                    newPopulation.add(temp);
                }
            }
            for (int j=(int)(popSize/10); j<popSize; j++) {
                int choice1 = (int)(Math.random()*fit.size());
                int choice2 = (int)(Math.random()*fit.size());

                int win = 0;
                System.out.println(choice1);
                System.out.println(choice2);
                if ((int)fit.get(choice1)>(int)fit.get(choice2)) {
                    win = choice1;
                }
                else {
                    win = choice2;
                }
                ArrayList<Assignment> winner = new ArrayList<Assignment>();
                for (int i=0; i<noOfCourses; i++) {
                    winner.add(population.get(i+win));
                }

                int method = (int)(Math.random()*3);
                ArrayList<Assignment> child = (ArrayList<Assignment>) winner.clone();
                if (method>0.15) {
                    if (method == 1) {
                        child = swapRooms(child, noOfCourses, rooms, noOfRooms);
                    }
                    else if (method == 2) {
                        child = swapTimeSlots(child, noOfCourses, timeSlots, noOfTimeSlots);
                    }
                    else {
                        child = changeTimeSlot(child, noOfCourses, timeSlots, noOfTimeSlots);
                    }
                }
                else {
                    child = mutate(child, noOfCourses, timeSlots, noOfTimeSlots, rooms, noOfRooms);
                }
                if ((int)fitness(child, 1, noOfCourses, rooms, timeSlots).get(0) > (int)fitness(winner, 1, noOfCourses, rooms, timeSlots).get(0)) {
//                    newPopulation.add(child);
                    for (Assignment a: child) {
                        newPopulation.add(a);
                    }
                }
                else {
//                    newPopulation.add(winner);
                    for (Assignment a: winner) {
                        newPopulation.add(a);
                    }
                }
            }
            population.clear();
            population = (ArrayList<Assignment>)newPopulation.clone();
            fit = fitness(population, popSize, noOfCourses, rooms, timeSlots);
        }
        ArrayList<Assignment> winner = new ArrayList<Assignment>();
        int maxIndex = fit.indexOf(Collections.max(fit));
        for (int i=maxIndex; i<maxIndex+noOfCourses; i++) {
            winner.add(population.get(i));
        }
        return winner;
    }

    public ArrayList fitness(ArrayList<Assignment> pop, int popSize, int noOfCourses, ArrayList<String> rooms, ArrayList<String> timeSlots) {
        ArrayList fit = new ArrayList();
        for (int i=0; i<popSize; i+=noOfCourses) {
            int fitnessVal = 0;
            for (int j=0; j<noOfCourses; j++) {
                int k = i+j;
                Assignment temp = (Assignment)pop.get(k);
                String allocatedTimeSlot = ((Assignment)pop.get(k)).timeSlot;
//                String allocatedTimeSlot = "";
                String allocatedRoom = pop.get(k).room;
                String allocatedProffessor = pop.get(k).professor;
                String roomChoice1 = pop.get(k).roomChoice1;
                String roomChoice2 = pop.get(k).roomChoice2;
                String timeSlotChoice1 = pop.get(k).timeSlotChoice1;
                String timeSlotChoice2 = pop.get(k).timeSlotChoice2;
                int requestedRoom = 0;
                if (allocatedRoom.equals(roomChoice1) || allocatedRoom.equals(roomChoice2))
                requestedRoom = 1;
                int requestedTimeSlot = 0;
                if (allocatedTimeSlot.equals(timeSlotChoice1) || allocatedTimeSlot.equals(timeSlotChoice2))
                    requestedTimeSlot = 1;
                int roomTimeSlotClash = 2;
                int roomProfessorClash = 2;
                for (int l=i+noOfCourses; l<popSize; l+=noOfCourses) {
                    for (int m=0; m<noOfCourses; m++) {
                        k = l+m;
                        if (allocatedTimeSlot.equals(pop.get(k).timeSlot)) {
                            if (allocatedRoom.equals(pop.get(k).room))
                                roomTimeSlotClash = 0;
                            if (allocatedProffessor.equals(pop.get(k).professor))
                                roomProfessorClash = 0;
                        }
                    }
                }
                fitnessVal = fitnessVal + requestedRoom + requestedTimeSlot + roomTimeSlotClash + roomProfessorClash;
            }
            fit.add(fitnessVal);
        }
        return fit;
    }

    public ArrayList swapRooms(ArrayList<Assignment> pop, int noOfCourses, ArrayList<String> rooms, int noOfRooms) {
        String choice1 = rooms.get((int)(Math.random()*noOfRooms));
        String choice2 = rooms.get((int)(Math.random()*noOfRooms));
        int popSize = pop.size();
        for (int i=0; i<popSize; i++) {
            if (pop.get(i).room.equals(choice1))
                pop.get(i).room = choice2;
            else if (pop.get(i).room.equals(choice2))
                pop.get(i).room = choice1;
        }
        return pop;
    }

    public ArrayList swapTimeSlots(ArrayList<Assignment> pop, int noOfCourses, ArrayList<String> timeSlots, int noOfTimeSlots) {
        String choice1 = timeSlots.get((int)(Math.random()*noOfTimeSlots));
        String choice2 = timeSlots.get((int)(Math.random()*noOfTimeSlots));
        int popSize = pop.size();
        for (int i=0; i<popSize; i++) {
            if (pop.get(i).timeSlot.equals(choice1))
                pop.get(i).timeSlot = choice2;
            else if (pop.get(i).timeSlot.equals(choice2))
                pop.get(i).timeSlot = choice1;
        }
        return pop;
    }

    public ArrayList changeTimeSlot(ArrayList<Assignment> pop, int noOfCourses, ArrayList<String> timeSlots, int noOfTimeSlots) {
        int courseChoice = (int)(Math.random()*noOfCourses);
        int timeSlotChoice = (int)(Math.random()*noOfTimeSlots);
        pop.get(courseChoice).timeSlot = timeSlots.get(timeSlotChoice);
        return pop;
    }

    public ArrayList mutate(ArrayList<Assignment> pop, int noOfCourses, ArrayList<String> timeSlots, int noOfTimeSlots, ArrayList<String> rooms, int noOfRooms) {
        int courseChoice = (int)(Math.random()*noOfCourses);
        String timeSlotChoice = timeSlots.get((int)(Math.random()*noOfTimeSlots));
        String roomChoice = rooms.get((int)(Math.random()*noOfRooms));

        pop.get(courseChoice).timeSlot = timeSlotChoice;
        pop.get(courseChoice).room = roomChoice;

        return pop;
        
    }
}