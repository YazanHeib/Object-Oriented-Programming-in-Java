import java.util.Objects;

public abstract class Event implements Comparable<Event> {

    private Gender allowedGender;
    private String eventName;

    /**
     * @param eventName
     * @param allowedGender
     * constructor
     */

    public Event(String eventName, Gender allowedGender) {
        this.eventName = eventName;
        this.allowedGender = allowedGender;

    }

    /**
     * @param obj event
     *            convert object to event a check if they are equal
     * @return true if they equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return allowedGender == event.allowedGender && Objects.equals(eventName, event.eventName);

    }

    /**
     * @param o event
     *          compare our event to another event
     * @return 0 if they are equal
     */

    public int compareTo(Event o) {


        if (this.equals(o) == false) {

            if (o.eventName.compareTo(eventName) != 0) {
                return eventName.compareTo(o.eventName);

            } else {
                return this.getJudgeCount() - o.getJudgeCount();
            }


        } else {
            return 0;
        }
    }

    /**
     * get the allowed  gender
     *
     * @return allowedGender
     */
    public Gender getAllowedGender() {
        return allowedGender;
    }

    /**
     * get the event name
     *
     * @return event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * get the number of judges
     * abstract method that the heiresses classes will be realized
     *
     * @return Judge Count
     */
    abstract int getJudgeCount();


    /**
     * string that describes our event
     *
     * @return string
     */

    public String toString() {
        return "Event " + eventName + " with "
                + getJudgeCount() + " judges for " + allowedGender + " athletes";


    }

    /**
     * if the object are equal will are return the same int , else different int
     * this method help us to sort the objects in the data structures  the java provides
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(allowedGender, eventName);
    }
}
