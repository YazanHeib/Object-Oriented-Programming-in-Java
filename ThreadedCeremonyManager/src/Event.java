public class Event {
    private String eventName;
    private String sportName;

    /**
     * @param eventName
     * @param sportName
     * constructor
     */
    public Event(String eventName, String sportName) {
        this.eventName = eventName;
        this.sportName = sportName;
    }

    /**
     * @param string
     *      build  Event  according to the string the function take as a parameter
     * @return Event
     * @throws Exception
     */
    public static Event buildEvent(String string)  {

        String[] stringArray = string.split(";");

        if (stringArray.length != 2) {
            throw new RuntimeException("invalid parameters");
        }
        String name1 = stringArray[0].trim();
        String name2 = stringArray[1].trim();
        if(name1.isEmpty() || name2.isEmpty())
            throw new RuntimeException("name 1 or name2 is empty!!");
        Event event = new Event(name1, name2);

        return event;

    }

    /**
     * build a string that described the event
     *
     * @return string
     */
    @Override
    public String toString() {
        return String.format("Event  %-28s for sport %-10s", getEventName(), getSportName());
    }

    /**
     * return the eventName
     *
     * @return String
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * return the sportName
     *
     * @return string
     */
    public String getSportName() {
        return sportName;
    }
}
