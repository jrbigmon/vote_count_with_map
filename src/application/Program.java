package application;

import java.util.Map;

import models.entities.Candidate;
import models.services.CandidateReadFileService;

public class Program {
  public static void main(String[] args) throws Exception, IllegalArgumentException {
    CandidateReadFileService crfs = new CandidateReadFileService("src/resources/candidates.csv");

    Map<Candidate, Integer> candidates = crfs.readFile();

    for (Candidate key : candidates.keySet()) {
      System.out.println("Candidate: " + key.getName() + " votes: " + candidates.get(key));
    }
  }
}
