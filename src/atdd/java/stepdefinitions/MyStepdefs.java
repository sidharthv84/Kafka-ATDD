package stepdefinitions;

import com.techprimers.kafka.springbootkafkaproducerexample.model.User;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import kafka.KafkaproducerConfig;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;
@DependsOn("test")
public class MyStepdefs {

    @Autowired
  //  KafkaTemplate kafkaTemplate = new KafkaTemplate;
    private KafkaTemplate<String,String> kafkaTemplate;
    private KafkaproducerConfig kafkaproducerConfig;
    static String TOPIC = "Kafka_Example";
    static String key ="sample Key";
    static String value = "Kafka_Example";

    private static final String MESSAGE = "TESR";


    @Given("^MS is up and Running$")
    public void msIsUpAndRunning() {
        System.out.println("test1");
    }

    @When("^a json request \"([^\"]*)\" is consumed by MS$")
    public void aJsonRequestIsConsumedByMS(String arg0) throws Throwable {
      //  String message = "abc";
        kafkaTemplate.send(TOPIC,"test");
    //    System.out.println("test2");
     //   kafkaTemplate.send(TOPIC, new User(name, "Technology", 12000L));

    }

    @Then("^validate output details in \"([^\"]*)\"$")
    public void validateOutputDetailsIn(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("test3");
    }

    @And("^validate events in \"([^\"]*)\"$")
    public void validateEventsIn(String arg0) throws Throwable {
        System.out.println("test4");
    }
}
