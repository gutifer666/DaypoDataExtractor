package trade.javiergutierrez.DaypoDatasExtractor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import trade.javiergutierrez.DaypoDatasExtractor.models.Question;
import trade.javiergutierrez.DaypoDatasExtractor.repositories.QuestionRepository;

@RestController
public class ReceiveSolutionController {
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/solution/{questionId}")
    public String getCorrectSolution(@PathVariable String questionId) {

        Question question = questionRepository.findById(Long.parseLong(questionId)).orElse(null);
        assert question != null;
        int position = question.getElection().indexOf('2');
        if (position != -1 && (question.getSolutions().size()) > position) {
            return question.getSolutions().get(position);
        }

        return null;
    }

}
