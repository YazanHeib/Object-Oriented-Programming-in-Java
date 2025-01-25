import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, OlympicException {
        File eventFile = new File(args[0]);
        Scanner eventScanner = new Scanner(eventFile);
        int eventIndex = 0;
        GymnastEvent gymnastEvent;
        EquestrianEvent equestrianEvent;


        // data  structures  the OlympicInformation class  provides to save the read files
        OlympicInformation.eventsArray = new Event[12];
        OlympicInformation.judgesReversed = new ArrayList<>();
        OlympicInformation.competitionMap = new HashMap<>();


        // Read the events from the file and populate the eventsArray
        while (eventScanner.hasNextLine()) {
            String line = eventScanner.nextLine();//new line
            if (line.charAt(0) == 'E') {
                equestrianEvent = EquestrianEvent.buildEquestrianEvent(line);
                OlympicInformation.eventsArray[eventIndex] = equestrianEvent;
                eventIndex++;
            } else if (line.charAt(0) == 'G') {
                gymnastEvent = GymnastEvent.buildGymnastEvent(line);
                OlympicInformation.eventsArray[eventIndex] = gymnastEvent;
                eventIndex++;
            }
        }// close the file
        eventScanner.close();


        // Write the events to the "eventsOutFile.txt" file
        if (OlympicInformation.eventsArray != null) {
            Writer eventWriter = null;
            try {
                eventWriter = new FileWriter("eventsOutFile.txt");
                for (Event event : OlympicInformation.eventsArray) {//for each
                    if (event == null) break;//If equal to NULL exit the loop
                    eventWriter.write(event.toString() + "\n");
                }
                eventWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (eventWriter != null) {
                    try {
                        eventWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            //  eventsArray is null
            System.out.println("The eventsArray is null. Please initialize it before writing to the file.");
        }


        // Write the sorted events to the "eventsSortedOutFile.txt" file
        Writer sortedEventWriter = new FileWriter("eventsSortedOutFile.txt");
        List<Event> eventsList = Arrays.asList(OlympicInformation.eventsArray);
        Collections.sort(eventsList);
        for (Event event : eventsList) {
            if (event == null) break;//If equal to NULL exit the loop
            sortedEventWriter.write(event.toString() + "\n");
        }

        sortedEventWriter.flush();
        sortedEventWriter.close();//close file

        // Read the judges file and create a list of judges

        File judgeFile = new File(args[1]);
        Scanner judgeScanner = new Scanner(judgeFile);

        String judgeLine = judgeScanner.nextLine();


        while (judgeScanner.hasNextLine()) {
            judgeLine = judgeScanner.nextLine();
            Judge judge = Judge.buildJudge(judgeLine);
            OlympicInformation.judgesReversed.add(0, judge);
        }
        judgeScanner.close();


        // Write the judges to the "judgesOutFile.txt" file
        Writer judgeWriter = new FileWriter("judgesOutFile.txt");
        for (Judge judge : OlympicInformation.judgesReversed) {
            if (judge == null) break;//If equal to NULL exit the loop
            judgeWriter.write(judge.toString() + "\n");
        }
        judgeWriter.flush();
        judgeWriter.close();


        // Write the sorted judges to the "judgesSortedOutFile.txt" file
        Writer sortedJudgeWriter = new FileWriter("judgesSortedOutFile.txt");//creat new file
        Collections.sort(OlympicInformation.judgesReversed);
        for (Judge judge : OlympicInformation.judgesReversed) {
            if (judge == null) break;
            sortedJudgeWriter.write(judge.toString() + "\n");
        }
        sortedJudgeWriter.flush();
        sortedJudgeWriter.close();


        // Read the competitions file and create a map of dates to competition lists
        readCompetition(args[2]);


        // Write the competitions to the "competitionsOutFile.txt" file
        Writer competitionWriter = new FileWriter("competitionsOutFile.txt");
        List<Map.Entry<LocalDate, List<Competition>>> entries = new ArrayList<>(OlympicInformation.competitionMap.entrySet());
        Collections.reverse(entries);
        for (Map.Entry<LocalDate, List<Competition>> entry : entries) {
            LocalDate date = entry.getKey();
            List<Competition> competitionList = entry.getValue();
            for (Competition competition : competitionList) {
                competitionWriter.write(competition + "\n");
            }
        }
        competitionWriter.flush();
        competitionWriter.close();
    }
    // Read the competition file and populate the competitionMap


    static Map<LocalDate, List<Competition>> readCompetition(String filename) throws FileNotFoundException, OlympicException {
        Map<LocalDate, List<Competition>> competitionMap = new HashMap<>();
        File competitionFile = new File(filename);
        Scanner competitionScanner = new Scanner(competitionFile);
        String competitionLine = competitionScanner.nextLine();
        while (competitionScanner.hasNextLine()) {
            competitionLine = competitionScanner.nextLine();
            Competition competition = Competition.buildCompetition(competitionLine);
            String[] parts = competitionLine.split(";");
            LocalDate date = LocalDate.parse(parts[2]);
            if (competitionMap.containsKey(date)) {
                competitionMap.get(date).add(competition);
            } else {
                List<Competition> competitionList = new ArrayList<>();
                competitionList.add(competition);
                competitionMap.put(date, competitionList);
            }
        }

        competitionScanner.close();//close file
        OlympicInformation.competitionMap = competitionMap;
        return competitionMap;
    }


}
