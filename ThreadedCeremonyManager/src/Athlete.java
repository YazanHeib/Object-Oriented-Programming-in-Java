public class Athlete {

    private String athleteName;
    private Nation nationality;

    /**
     * @param athleteName
     * @param nationality
     * constructor
     */
    public Athlete(String athleteName, Nation nationality) {
        this.athleteName = athleteName;
        this.nationality = nationality;
    }

    /**
     * @return athleteName
     */
    public String getAthleteName() {
        return athleteName;
    }

    /**
     * @return nationality
     */
    public Nation getNationality() {
        return nationality;
    }

    /**
     * @param s string
     *          bulid a Athlete  according to the string the function take as a parameter
     *
     * @return Athlete
     *
     * @throws Exception
     */

    public static Athlete buildAthlete(String s) {
        String[] athleteParametersList = s.split(";");

        if (athleteParametersList.length != 2) {
            throw new RuntimeException("invalid parameters");
        }

        Athlete athlete = null;


        for (Nation n : CeremonyData.nations) {
            if (n.getNationName().equals(athleteParametersList[1])) {
                athlete = new Athlete(athleteParametersList[0], n);
            }
        }

        if (athlete != null) {
            return athlete;
        } else {
            throw new RuntimeException("invalid country1");
        }


    }

    /**
     * build a string that described the  Athlete
     *
     * @return string
     */
    @Override
    public String toString() {

        return String.format("Athlete %-13s from %-20s", athleteName, nationality.toString());

    }
}
