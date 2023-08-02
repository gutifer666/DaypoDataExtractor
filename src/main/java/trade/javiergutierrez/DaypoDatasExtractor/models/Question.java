package trade.javiergutierrez.DaypoDatasExtractor.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String election;
    @ElementCollection
    private List<String> solutions = new ArrayList<>();

    public Question()
    {
    }
    public Question(String question, String election, List<String> solutions)
    {
        this.question = question;
        this.election = election;
        this.solutions = solutions;
    }
}
