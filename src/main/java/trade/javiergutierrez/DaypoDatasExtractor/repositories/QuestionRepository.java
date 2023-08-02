package trade.javiergutierrez.DaypoDatasExtractor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trade.javiergutierrez.DaypoDatasExtractor.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
