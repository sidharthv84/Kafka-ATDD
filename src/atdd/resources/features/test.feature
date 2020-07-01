Feature: Kafka Framework Setup

  @integrationTest
  Scenario Outline: MS testing setup
  Given MS is up and Running
  When a json request "<json_input_response>" is consumed by MS
  Then validate output details in "<json_output_response>"
  And validate events in "<json_event_response>"
  Examples:
    |json_input_response|json_output_response|json_event_response|
    | validJson         |validOutput         |validEvent         |