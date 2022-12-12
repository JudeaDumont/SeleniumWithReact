package util;

import model.Candidate;

import java.util.ArrayList;
import java.util.List;

public class util {
    public static Candidate getCandidateFromTableDiff(
            List<Candidate> candidates1,
            List<Candidate> candidates2) {
        List<Candidate> copy = new ArrayList<>(candidates2);
        copy.removeAll(candidates1);

        if(copy.size() != 1){
            throw new RuntimeException("copy.size() = " + copy.size());
        }
        return copy.get(0);
    }
}
