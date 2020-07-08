package com.test.kafka.stepdefinitions;

import com.test.kafka.CucumberStep;
import com.test.kafka.utils.ResourceLoaderHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import kafka.KafkaproducerConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;


@CucumberStep
public class MyStepdefs {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    private ResourceLoaderHelper resourceLoaderHelper;
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
    public void aJsonRequestIsConsumedByMS(String requestId) throws Throwable {
      //  String message = "abc";
        kafkaTemplate.send(TOPIC,"test");
        String message = resourceLoaderHelper.getrequest1(requestId);
        kafkaTemplate.send(TOPIC,message);
        System.out.println(message);
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
