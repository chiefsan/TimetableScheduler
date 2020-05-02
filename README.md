# Timetable Scheduler
![scheduler-preveiw](https://github.com/chiefsan/TimetableScheduler/blob/master/scheduler.PNG)
Timetable scheduling problem is a constraint satisfaction problem around scheduling resources. It can be a tedious and frustrating job due to the NP-hard nature of the problem. This repository contains an implementation of a [solution](https://www.academia.edu/download/56355803/52a46bee76a6395818da5984aacdb4e7568b.pdf) to the problem using genetic algorithms in R. 

## Constraints
The timetable satisfies the following conditions:
- All lectures should take place exactly once
- Group *g* can attend only one class at one
time
- Instructor *i* can teach only one class at one
time
- In room *r* only one class can be taught at one
time

## Usage

- Enter the list of available rooms and time slots in the application
- Enter the preferences in the form(Course, Professor, Room1, Room2, Slot1, Slot2) 
- The output would be stored in new `csv` file called `schedule.csv`. (example: [schedule.csv](https://github.com/chiefsan/TimetableScheduler/blob/master/schedule.csv))

## License
[MIT](https://github.com/chiefsan/TimetableScheduler/blob/master/LICENSE)
