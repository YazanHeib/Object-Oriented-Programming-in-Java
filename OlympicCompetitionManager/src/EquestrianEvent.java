
import java.util.Objects;
import java.util.Scanner;

public class EquestrianEvent extends Event {

    final static int judgeCount = 5;

    /**
     * @param eventName
     * @param allowedGender
     * constructor
     */
    public EquestrianEvent(String eventName, Gender allowedGender) {
        super(eventName, allowedGender);

    }

    /**
     * @param string this is a static method that take a line from file a build a EquestrianEvent according to the data in it
     * @return EquestrianEvent
     * @throws OlympicException
     */
    public static EquestrianEvent buildEquestrianEvent(String string) throws OlympicException {

        if (string.split(";").length != 4) {
            throw new OlympicException("you don't Enough Parts in your line ");
        }

        Scanner scanner = new Scanner(string);
        EquestrianEvent equestrianEvent;

        scanner.useDelimiter("[;\n]");
        String firstLetter = scanner.next();
        String eventName = scanner.next();
        String numberStr = scanner.next();
        String genderStr = scanner.next();

        if (!numberStr.equals("5")) {
            throw new OlympicException("invalid judge number ");
        }

        if (firstLetter.equals("E")) {

            if (genderStr.equals("ANY")) {
                equestrianEvent = new EquestrianEvent(eventName, Gender.ANY);
                return equestrianEvent;

            } else if (genderStr.equals("MALE")) {
                equestrianEvent = new EquestrianEvent(eventName, Gender.MALE);
                return equestrianEvent;

            } else if (genderStr.equals("FEMALE")) {
                equestrianEvent = new EquestrianEvent(eventName, Gender.FEMALE);
                return equestrianEvent;

            } else {
                throw new OlympicException("invalid Gender in your line ");

            }


        } else {
            throw new OlympicException("invalid first Character (E) in line ");
        }

    }

    /**
     * @param o object
     *          convert object to EquestrianEvent a check if they are equal
     * @return true if they equal, false if not
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EquestrianEvent that = (EquestrianEvent) o;
        return judgeCount == that.judgeCount;
    }

    /**
     * if the object are equal will are return the same int , else different int
     * this method help us to sort the objects in the data structures  the java provides
     *
     * @return integer
     */

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), judgeCount);
    }

    /**
     * the method will return the judges number
     *
     * @return judgeCount
     */

    @Override
    public int getJudgeCount() {
        return judgeCount;
    }

}
