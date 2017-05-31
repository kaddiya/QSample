package org.kaddiya.qsample.consumer

import groovy.util.logging.Slf4j
import org.kaddiya.QClient.common.BrokerConfig
import org.kaddiya.QClient.consumer.internal.SimpleConsumer
import org.kaddiya.QClient.consumer.internal.example.SomePayload
import org.kaddiya.qsample.commons.SampleMessage

@Slf4j
class Main {
    public static void main(String[] args) {
        //due to the type erasure we to tell our abstract class on how to unmarshall the content to our type and then pass in a
        //a closure that will do the transformation and perform the sideeffect of when we receive a message
        //In this example we will just log out the value of the `SomePayload`
        //We can return SomePayload and then curry it to another function for jazzy transformation
        Closure<SampleMessage> marshallingAndCallBackClosure = { String rawContent ->
            try {
                //need to serialise m.content into this class
                log.info(rawContent)
            } catch (Exception e) {
                log.error("could not marshall the json" + e.getMessage())
                return null
            }
        }


        SimpleConsumer bc = new SimpleConsumer<SomePayload>("1", new BrokerConfig("http", "localhost", 8080), Arrays.asList(""), marshallingAndCallBackClosure)
        bc.consumeMesage();
    }
}