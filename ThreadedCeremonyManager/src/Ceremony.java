import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Ceremony extends Thread {

    private Event event;
    private Athlete firstPlace, secondPlace, thirdPlace;
    private int duration;


    /**
     * @param event
     * @param firstPlace
     * @param secondPlace
     * @param thirdPlace
     * @param duration
     * constructor
     */
    public Ceremony(Event event, Athlete firstPlace, Athlete secondPlace, Athlete thirdPlace, int duration) {
        this.event = event;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.thirdPlace = thirdPlace;
        this.duration = duration;
    }

    /**
     * @return event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * return the athlete in the first place
     *
     * @return Athlete
     */
    public Athlete getFirstPlace() {
        return firstPlace;
    }

    /**
     * return the athlete in the second place
     *
     * @return Athlete
     */
    public Athlete getSecondPlace() {
        return secondPlace;
    }

    /**
     * return the athlete in the third place
     *
     * @return Athlete
     */
    public Athlete getThirdPlace() {
        return thirdPlace;
    }

    /**
     * return the duration of the ceremony
     *
     * @return int
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param s string
     *          build a Ceremony  according to the string the function take as a parameter
     * @return Ceremony
     * @throws Exception
     */
    public static Ceremony buildCeremony(String s)  {

        String[] ceremonyBuildList = s.split(";");

        if (ceremonyBuildList.length != 5) {
            throw new RuntimeException("invalid parameters");
        }

        Event event1 = null;

        for (int i = 0; i < CeremonyData.events.size(); i++) {
            if (CeremonyData.events.get(i).getEventName().equals(ceremonyBuildList[0])) {
                event1 = CeremonyData.events.get(i);
            }
        }

        if (event1 == null) {
            throw new RuntimeException("invalid Event");
        }

        Athlete firstAthlete = null;

        for (Athlete athlete0 : CeremonyData.athletes) {
            if (athlete0.getAthleteName().equals(ceremonyBuildList[1])) {
                firstAthlete = athlete0;
            }
        }

        Athlete secondAthlete = null;

        for (Athlete athlete1 : CeremonyData.athletes) {
            if (athlete1.getAthleteName().equals(ceremonyBuildList[2])) {
                secondAthlete = athlete1;
            }
        }

        Athlete thirdAthlete = null;

        for (Athlete athlete2 : CeremonyData.athletes) {
            if (athlete2.getAthleteName().equals(ceremonyBuildList[3])) {
                thirdAthlete = athlete2;
            }
        }

        if (thirdAthlete == null || secondAthlete == null || firstAthlete == null) {
            throw new RuntimeException("invalid Athlete");
        }

        Ceremony ceremony = new Ceremony(event1, firstAthlete, secondAthlete, thirdAthlete, Integer.parseInt(ceremonyBuildList[4]));

        return ceremony;
    }

    /**
     * build a string that described the ceremony
     *
     * @return string
     */
    @Override
    public String toString() {

        return String.format("Ceremony for " + event.toString() + " duration " + duration + " and winners " + firstPlace.toString()
                + " " + secondPlace.toString() + " and " + thirdPlace.toString());

    }

    /**
     * method from the Thread Class that we extend in our class
     */
    @Override
    public void run() {

        Nation[] winnersArray = new Nation[3];
        winnersArray[0] = firstPlace.getNationality();
        winnersArray[1] = secondPlace.getNationality();
        winnersArray[2] = thirdPlace.getNationality();
        Arrays.sort(winnersArray);


        Date ceremonyDate = new Date();
        SimpleDateFormat ceremonyDateFormat = new SimpleDateFormat("HH:mm:ss");
        String stringFormatTime = ceremonyDateFormat.format(ceremonyDate);


        String firstString = String.format("%-7s%-10s%-26s at " + stringFormatTime, "Start", "ceremony", event.getEventName());
        System.out.printf("%s (%s%4s)", firstString, "duration", duration);
        System.out.print("\n");


        synchronized (event) {
            for (int i = 0; i < 3; i++) {
                winnersArray[i].getLock().lock();
                System.out.print(event + " locked " + String.format("%-10s", winnersArray[i].getNationName()));
                System.out.print("\n");
            }
        }


        try {
            System.out.printf("%-7sMedal " + firstPlace + "%n", "Gold");
            Thread.sleep(getDuration() * 1000);

            System.out.printf("%-7sMedal " + secondPlace + "%n", "Silver");
            Thread.sleep(getDuration() * 1000);

            System.out.printf("%-7sMedal " + thirdPlace + "%n", "Bronze");
            Thread.sleep(getDuration() * 1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (event) {
            for (int i = 0; i < 3; i++) {
                winnersArray[i].getLock().unlock();
                System.out.printf("%s unlocked %10s", event, winnersArray[i].getNationName());
                System.out.print("\n");
            }
        }


        SimpleDateFormat finishDate = new SimpleDateFormat("HH:mm:ss");
        String finishTime = finishDate.format(ceremonyDate);

        String finishString = String.format("%-7s%-10s%-26s at " + finishTime, "End", "ceremony", event.getEventName());
        System.out.printf("%s", finishString);
        System.out.print("\n");

    }
}
