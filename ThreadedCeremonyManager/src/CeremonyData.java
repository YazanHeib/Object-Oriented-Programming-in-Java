import java.util.ArrayList;
import java.util.List;

public class CeremonyData {

    public static List<Athlete> athletes;
    public static List<Event> events;
    public static List<Nation> nations;
    public static List<Ceremony> ceremonies;

    static {
        athletes = new ArrayList<>();
        events = new ArrayList<>();
        nations = new ArrayList<>();
        ceremonies = new ArrayList<>();
    }

}
