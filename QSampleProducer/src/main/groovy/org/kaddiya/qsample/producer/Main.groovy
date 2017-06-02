package org.kaddiya.qsample.producer

import groovy.util.logging.Slf4j
import org.kaddiya.QClient.common.BrokerConfig
import org.kaddiya.QClient.producer.internal.SimpleProducer
import org.kaddiya.QClient.producer.models.UnpublishableException
import org.kaddiya.qsample.commons.SampleMessage

@Slf4j
class Main {
    public static void main(String[] args) {
        if (args.size() < 1) {
            throw new IllegalArgumentException("Please specify the topic Id")
        }
        String topicId = args[0];

        BrokerConfig bc = new BrokerConfig("http", System.getenv("BROKER_HOST"), Integer.valueOf(System.getenv("BROKER_PORT")))
        SimpleProducer producer = new SimpleProducer(topicId, bc);
        int i = 0;
        Random r = new Random()
        while (true) {
            i++
            SampleMessage s = new SampleMessage(i, r.nextDouble().toString(), true)
            try {
                producer.publishToBroker(s)
            } catch (UnpublishableException e) {
                log.error("", e)
            }
            Thread.sleep(2000)
        }

    }
}
