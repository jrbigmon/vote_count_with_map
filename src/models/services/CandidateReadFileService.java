package models.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import models.entities.Candidate;

public class CandidateReadFileService {
  private String path;

  public CandidateReadFileService(String path) {
    this.path = path;
  }

  public Map<Candidate, Integer> readFile() throws IllegalArgumentException {
    Map<Candidate, Integer> candidates = new TreeMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(new File(path).getAbsolutePath()))) {
      String line = br.readLine();

      while (line != null) {
        String[] fields = line.split(",");
        String name = fields[0].trim();
        Integer votes = Integer.parseInt(fields[1].trim());

        Candidate newCandidate = new Candidate(name);

        if (candidates.containsKey(newCandidate)) {
          Integer oldVotes = candidates.get(newCandidate);
          candidates.put(newCandidate, oldVotes + votes);
        } else {
          candidates.put(newCandidate, votes);
        }

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Error on: " + e.getMessage());
    }

    return candidates;
  }
}
