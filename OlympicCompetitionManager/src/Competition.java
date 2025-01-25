import com.sun.jdi.Value;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Competition implements Comparable<Competition> {

    private LocalDate competitionDate;

    private CompetitionLevel competitionLevel;

    private Event event;

    private List<Judge> judges;


    /**
     * @param event
     * @param competitionDate
     * @param judges
     * @param level
     * constructor
     */
    public Competition(Event event, LocalDate competitionDate, List<Judge> judges, CompetitionLevel level) throws OlympicException {
        if (event == null || judges == null || level == null) {
            throw new OlympicException("error");
        }
        this.event = event;
        this.judges = judges;
        this.competitionDate = competitionDate;
        this.competitionLevel = level;
    }

    /**
     * @param input this static read line a build a Competition according to the data on it
     *              if data not Incomplete or incorrect  will throw OlympicException
     * @return Competition
     * @throws OlympicException
     */
    public static Competition buildCompetition(String input) throws OlympicException {
        String strArr[] = input.split(";");
        String complevel[] = {"QUALIFIER",
                "QUARTER_FINAL",
                "SEMI_FINAL",
                "FINAL"};
        if (strArr.length != 4)
            throw new OlympicException("not enough parts in your line1");
        Event event = null;
        for (Event e : OlympicInformation.eventsArray) {
            if (e.getEventName().equals(strArr[0])) {
                event = e;
                break;
            }
        }
        if (event == null)
            throw new OlympicException("erorr null");

        boolean vallevel = false;
        for (String ss : complevel) {
            if (ss.equals(strArr[1])) {
                vallevel = true;
                break;
            }
        }
        if (!vallevel)
            throw new OlympicException("not in list");

        LocalDate birthdate;
        try {
            birthdate = LocalDate.parse(strArr[2]);
        } catch (DateTimeParseException e) {
            throw new OlympicException("invalid birthdate format");
        }

        String judgeArr[] = strArr[3].split(",");

        List<Judge> b = new ArrayList<>();

        for (String s : judgeArr) {
            for (Judge name : OlympicInformation.judgesReversed) {
                if (name.getJudgeName().equals(s) && name.getEvent().equals(event)) {
                    b.add(name);
                    break;
                }
            }
        }

        if (b.size() != judgeArr.length) {
            throw new OlympicException("not all judges are valid");
        }

        CompetitionLevel c1;
        try {
            c1 = CompetitionLevel.valueOf(strArr[1]);
        } catch (IllegalArgumentException e) {
            throw new OlympicException("invalid competition level");
        }

        Competition c = new Competition(event, birthdate, b, c1);
        return c;
    }

    /**
     * @param o Competition
     *          <p>
     *          compare our Competition to another Competition
     * @return 0 if they are equal
     */

    @Override
    public int compareTo(Competition o) {
        int Date = this.competitionDate.compareTo(o.competitionDate);
        int level = this.competitionLevel.compareTo(o.competitionLevel);
        int event = this.event.compareTo(o.event);
        if (Date != 0)
            return Date;
        if (level != 0)
            return level;
        if (event != 0)
            return event;
        else {
            return 0;
        }
    }

    /**
     * this method will return Date of the Competition
     *
     * @return CompetitionDate
     */
    public LocalDate getCompetitionDate() {
        return this.competitionDate;
    }

    /**
     * this method will return the level of the competition
     *
     * @return competitionLevel
     */
    public CompetitionLevel getCompetitionLevel() {
        return competitionLevel;
    }

    /**
     * this method will return event
     *
     * @return event
     */

    public Event getEvent() {
        return event;
    }

    /**
     * this method will return coby of the List<Judge>
     *
     * @return List<Judge>
     */

    public List<Judge> getJudges() {
        return List.copyOf(judges);
    }

    /**
     * @param o Competition
     *          convert object to Competition a check if they are equal
     * @return true if they equal, false if not
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competition that = (Competition) o;
        return competitionDate.equals(that.competitionDate) && competitionLevel == that.competitionLevel && event.equals(that.event) && judges.equals(that.judges);
    }

    /**
     * if the object are equal will are return the same int , else different int
     * this method help us to sort the objects in the data structures  the java provides
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(competitionDate, competitionLevel, event, judges);
    }

    /**
     * this method will return a string that describe Competition
     *
     * @return string
     */
    public String toString() {
        String judgesStr = "";
        for (Judge name : judges) {
            judgesStr += " " + name.getJudgeName();
        }

        return competitionDate.toString() + ": Competition " + competitionLevel + " event "
                + event.getEventName() + " with judges" + judgesStr;
    }
}
