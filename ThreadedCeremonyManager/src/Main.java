import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            // Read input files passed as arguments
            File eventsFile = new File(args[0]);
            File nationFile = new File(args[1]);
            File athleteFile = new File(args[2]);
            File ceremonyFile = new File(args[3]);

            // Process input files
            readEvents(eventsFile);
            readNation(nationFile);
            readAthlete(athleteFile);
            readCeremony(ceremonyFile);

            System.out.println("Start all ceremonies");

            // Start and wait for all ceremonies to complete
            for (Ceremony c : CeremonyData.ceremonies) {
                try {
                    c.start();
                    c.join();

                } catch (InterruptedException e) {
                    throw new InterruptedException();
                }
            }

            System.out.println("End all ceremonies");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Reads event data from a file and adds them to the CeremonyData list.
     * @param eventsFile File containing event data
     * @throws Exception if file reading fails
     */
    public static void readEvents(File eventsFile) throws Exception {
        BufferedReader eventBufferedReader = new BufferedReader(new FileReader(eventsFile));
        String eventDataReading = "";

        while ((eventDataReading = eventBufferedReader.readLine()) != null) {
            Event event = Event.buildEvent(eventDataReading);
            CeremonyData.events.add(event);
        }
    }

    /**
     * Reads nation data from a file and adds them to the CeremonyData list.
     * @param nationFile File containing nation data
     * @throws Exception if file reading fails
     */
    public static void readNation(File nationFile) throws Exception {
        BufferedReader nationBufferedReader = new BufferedReader(new FileReader(nationFile));
        String nationDataReading = "";

        while ((nationDataReading = nationBufferedReader.readLine()) != null) {
            Nation nation = Nation.buildNation(nationDataReading);
            CeremonyData.nations.add(nation);
        }
    }

    /**
     * Reads athlete data from a file and adds them to the CeremonyData list.
     * @param athleteFile File containing athlete data
     * @throws Exception if file reading fails
     */
    public static void readAthlete(File athleteFile) throws Exception {
        BufferedReader athleteBufferedReader = new BufferedReader(new FileReader(athleteFile));
        String athleteDataReading = "";

        while ((athleteDataReading = athleteBufferedReader.readLine()) != null) {
            Athlete athlete = Athlete.buildAthlete(athleteDataReading);
            CeremonyData.athletes.add(athlete);
        }
    }

    /**
     * Reads ceremony data from a file and adds them to the CeremonyData list.
     * @param ceremonyFile File containing ceremony data
     * @throws Exception if file reading fails
     */
    public static void readCeremony(File ceremonyFile) throws Exception {
        BufferedReader ceremonyBufferedReader = new BufferedReader(new FileReader(ceremonyFile));
        String ceremonyDataReading = "";

        while ((ceremonyDataReading = ceremonyBufferedReader.readLine()) != null) {
            Ceremony ceremony = Ceremony.buildCeremony(ceremonyDataReading);
            CeremonyData.ceremonies.add(ceremony);
        }
    }
}

