package util;

import model.Candidate;

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
        assert(copy.size() == 1);
        return copy.get(0);
    }
}
