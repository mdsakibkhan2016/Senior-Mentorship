package bd.edu.seu.seniormentorship;

import bd.edu.seu.seniormentorship.service.ResearchTeamPredicitionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PredictionTest {
    @Autowired
    ResearchTeamPredicitionService researchTeamPredicitionService;

    @Test
    public void testPrediction() {
        researchTeamPredicitionService.predict2();
    }
    @Test
    public void predictBalancedTeams() {
        researchTeamPredicitionService.predictBalancedTeams();
    }
}
