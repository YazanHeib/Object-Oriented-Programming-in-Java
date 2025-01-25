import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Judge implements National, Comparable<Judge> {

    private LocalDate birthdate;
    private Event event;
    private String judgeName;
    private String nationality;


    /**
     *
     * @param name
     * @param nationality
     * @param event
     * @param birthdate
     * C'tor
     */
    public Judge(String name, String nationality, Event event, LocalDate birthdate) {

        this.judgeName = name;
        this.nationality = nationality;
        this.event = event;
        this.birthdate = birthdate;

    }

    /**
     *
     * @param string
     * @return
     * @throws OlympicException
     * @return Judge
     */
    public static Judge buildJudge(String string) throws OlympicException {
        String ss[] = string.split(";");

        if (ss.length != 4) {

            throw new OlympicException("not enough parts in your line");
        }

        LocalDate birthdate = LocalDate.parse(ss[3]);

        Judge judge = null;

        for (Event event2 : OlympicInformation.eventsArray) {
            if (event2.getEventName().equals(ss[2])) {
                judge = new Judge(ss[0], ss[1], event2, birthdate);
                break;
            }
        }
        if (judge != null) {
            return judge;
        } else {
            throw new OlympicException("");
        }

    }

    /**
     * @param event
     * @return boolean
     */
    public boolean canJudge(Event event) {

        if (this.event.getEventName().equals(event.getEventName())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     *
     * @param o Object
     * @return true if the object's are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Judge judge = (Judge) o;
        return Objects.equals(birthdate, judge.birthdate) &&
                Objects.equals(event, judge.event) && Objects.equals(judgeName, judge.judgeName)
                && Objects.equals(nationality, judge.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthdate, event, judgeName, nationality);
    }

    /**
     * getter of the field Birthdate
     * @return LocalDate
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * getter of the field Event
     * @return Event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * getter of the field JudgeName
     * @return String
     */
    public String getJudgeName() {
        return judgeName;
    }

    /**
     * getter of the field Nation
     * @return String
     */
    public String getNation() {
        return nationality;
    }

    /**
     * setter of the field Brithdate
     * @param birthdate
     */
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * setter of the field Nation.
     * @param nationName
     */
    @Override
    public void setNation(String nationName) {
        this.nationality = nationName;
    }

    @Override
    public int compareTo(Judge o) {

        if (this.equals(o) == false) {

            if (judgeName.compareTo(o.judgeName) != 0) {
                return judgeName.compareTo(o.judgeName);
            }
            if (birthdate.compareTo(o.birthdate) != 0) {
                return birthdate.compareTo(o.birthdate);

            }
            if (nationality.compareTo(o.nationality) != 0) {
                return nationality.compareTo(o.nationality);
            }
            if (event.compareTo(o.event) != 0) {
                return event.compareTo(o.event);
            }

        }

        return 0;

    }

    /**
     * @return string that discribe the Judge.
     */
    public String toString() {
        return "Judge " + judgeName + " born " + getBirthdate().toString() + " from " + getNation() +
                " for " + getEvent().getEventName();
    }
}
