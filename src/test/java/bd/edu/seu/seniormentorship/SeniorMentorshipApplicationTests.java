package bd.edu.seu.seniormentorship;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.data.mongodb.uri=mongodb://localhost:27017/test",
    "gemini.api.key=test-key",
    "gemini.api.model=gemini-1.5-flash"
})
class SeniorMentorshipApplicationTests {

    @Test
    void contextLoads() {
    }
}
