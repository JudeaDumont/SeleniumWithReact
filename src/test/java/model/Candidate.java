package model;

import java.util.Objects;

public class Candidate {
    private String name;
    private Long id;

    public Candidate(String name) {
        this.name = name;
    }

    public Candidate(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name) && Objects.equals(id, candidate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}