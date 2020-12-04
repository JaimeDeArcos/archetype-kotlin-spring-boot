package features

import acceptance.simulators.InformationProcessorSimulator
import es.jaimedearcos.templates.Application
import io.cucumber.java8.En
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(
        classes = [Application::class],
        webEnvironment = DEFINED_PORT
)
class SpringSetup : En {
    init {
        Before { _ -> InformationProcessorSimulator.start() }
        After  { _ -> InformationProcessorSimulator.stop()  }
    }
}
