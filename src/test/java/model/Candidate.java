package model;

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
}