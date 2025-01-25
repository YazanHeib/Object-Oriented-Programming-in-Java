import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class Nation implements Comparable<Nation> {

    private String nationName;
    private String anthem;
    private final ReentrantLock lock;

    /**
     * returan the lock
     *
     * @return ReentrantLock
     */
    public ReentrantLock getLock() {
        return lock;
    }

    /**
     * @param nationName
     * @param anthem     constructor
     */
    public Nation(String nationName, String anthem) {
        this.nationName = nationName;
        this.anthem = anthem;
        lock = new ReentrantLock();
    }

    /**
     * return the nation name
     *
     * @return String
     */
    public String getNationName() {
        return nationName;
    }

    /**
     * return the Anthem
     *
     * @return String
     */
    public String getAnthem() {
        return anthem;
    }

    /**
     * build a string that described the nation
     *
     * @return string
     */
    @Override
    public String toString() {

        return String.format("%-12s with anthem %-30s", nationName, anthem);
    }

    /**
     *
     * @param obj
     * compare between objects if they are equal return true
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof  Nation) {
            return this.nationName.equals(((Nation) obj).getNationName());
        } else {
            return false;
        }

    }

    /**
     * @param o the object to be compared.
     * @return int
     */
    @Override
    public int compareTo(Nation o) {
        if (o == this) {
            return 0;
        }
        if (o != null) {
            return this.getNationName().compareToIgnoreCase(o.getNationName());
        } else {
            return 0;
        }
    }


    /**
     * hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(nationName, anthem, lock);
    }

    /**
     * @param s String
     *          build  Nation  according to the string the function take as a parameter
     * @return Nation
     * @throws Exception
     */
    public static Nation buildNation(String s)  {

        String[] nationBulidList = s.split(";");

        if (nationBulidList.length != 2) {
            throw new RuntimeException("invalid parameters");

        }

        Nation nation = new Nation(String.format(nationBulidList[0]), String.format(nationBulidList[1]));
        return nation;
    }
}
