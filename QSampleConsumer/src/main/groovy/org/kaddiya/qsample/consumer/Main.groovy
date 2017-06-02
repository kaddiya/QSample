package org.kaddiya.qsample.consumer

import groovy.util.logging.Slf4j
import org.kaddiya.QClient.common.BrokerConfig
import org.kaddiya.QClient.consumer.internal.SimpleConsumer
import org.kaddiya.qsample.commons.SampleMessage

@Slf4j
class Main {
    public static void main(String[] args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("Please specify the topic Id and the consumer id")
        }
        String topicId = args[0];  //topic ID
        String consumerId = args[1] //consumer ID

        List<String> deps
        if (args.size() == 3) {
            deps = Arrays.asList(args[2].split(","))
        } else {
            deps = Arrays.asList("")
        }

        BrokerConfig bcfg = new BrokerConfig("http", System.getenv("BROKER_HOST"), Integer.valueOf(System.getenv("BROKER_PORT")))

        //a callback closure that will do the transformation and perform the sideeffect of when we receive a message
        //In this example we will just log out the value of the `SomePayload`
        //We can return SomePayload and then curry it to another function for jazzy transformation
        Closure<SampleMessage> callbackOnMessage = { SampleMessage message ->
            try {

                log.info(message.toString())

            } catch (Exception e) {
                log.error("could not marshall the json" + e.getMessage())
                return null
            }
            return message
        }
        //due to the type erasure we to tell our abstract class on how to unmarshall the content to our type
        SimpleConsumer bc = new SimpleConsumer<SampleMessage>(consumerId, topicId, bcfg, deps, callbackOnMessage as Closure<SampleMessage>, SampleMessage.class)

        bc.consumeMesage();
    }
}