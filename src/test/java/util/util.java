package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.Candidate;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import pages.steps.Hook;

import java.util.ArrayList;
import java.util.List;

public class util {
    public static Candidate getCandidateFromTableDiff(
            List<Candidate> candidates1,
            List<Candidate> candidates2
    ){
        List<Candidate> copy = new ArrayList<>(candidates2);
        copy.removeAll(candidates1);
        System.out.println(copy.toString());
        System.out.println(candidates1.toString());
        System.out.println(candidates2.toString());
        LogEntries entry = Hook.base.Driver.manage().logs().get(LogType.BROWSER);
        // Retrieving all log
        List<LogEntry> logs= entry.getAll();
        // Print one by one
        for(LogEntry e: logs)
        {
            System.out.println(e);
        }

        assert(copy.size() == 1);
        return copy.get(0);
    }
}
