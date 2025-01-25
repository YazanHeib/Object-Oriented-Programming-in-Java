import java.util.Objects;
import java.util.Scanner;

public class GymnastEvent extends Event {

    final static int judgeCount = 7;

    /**
     * @param eventName
     * @param allowedGender
     * constructor
     */
    public GymnastEvent(String eventName, Gender allowedGender) {
        super(eventName, allowedGender);


    }

    /**
     * @param string this is a static method that take a line from file a build a GymnastEvent according to the data in the line
     * @return GymnastEvent
     * @throws OlympicException
     */
    public static GymnastEvent buildGymnastEvent(String string) throws OlympicException {

        if (string.split(";").length != 4) {
            throw new OlympicException("you don't Enough Parts in your line ");
        }

        Scanner scanner = new Scanner(string);
        GymnastEvent gymnastEvent;

        scanner.useDelimiter("[;\n]");
        String firstLetter = scanner.next();
        String eventName = scanner.next();
        String numberStr = scanner.next();
        String genderStr = scanner.next();

        if (!numberStr.equals("7")) {
            throw new OlympicException("invalid judge number ");
        }

        if (firstLetter.equals("G")) {

            if (genderStr.equals("ANY")) {
                gymnastEvent = new GymnastEvent(eventName, Gender.ANY);
                return gymnastEvent;

            } else if (genderStr.equals("MALE")) {
                gymnastEvent = new GymnastEvent(eventName, Gender.MALE);
                return gymnastEvent;

            } else if (genderStr.equals("FEMALE")) {
                gymnastEvent = new GymnastEvent(eventName, Gender.FEMALE);
                return gymnastEvent;

            } else {
                throw new OlympicException("invalid Gender in your line ");

            }


        } else {
            throw new OlympicException("invalid first Character (G) in line ");
        }

    }

    /**
     * @param o object
     *          convert object to GymnastEvent a check if they are equal
     * @return true if they equal, false if they are not
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GymnastEvent that = (GymnastEvent) o;
        return judgeCount == that.judgeCount;
    }

    /**
     * if the object are equal will are return the same int , else different int
     * this method help us to sort the objects in the data structures  the java provides
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), judgeCount);
    }

    /**
     * this method will return the  judge Count
     *
     * @return judgeCount
     */
    @Override
    public int getJudgeCount() {
        return judgeCount;
    }

}
