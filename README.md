# QSample

A demonstration project to show how [Q](https://github.com/kaddiya/Q) can be used.  

# Requirements:  
1.Working installation of Java 8.
2.`QClient-1.0.0`  to be resolvable by gradle in either a nexus server or local `~/.m2` repo.To do this follow the instructions [here]
(https://github.com/kaddiya/Q)

# Building:  
1.Once the pre requisites are complete ,clone this repo and in the root folder run `./gradlew clean distZip`.This will create the distributions of the Sample Producer and Sample Consumer.

# Details  

## QSampleProducer:  
1.This is a sample producer which can be run as command line once you build from the source.The zipped distro is found at 
`QSample/QSampleProducer/build/distributions/QSampleProducer-1.0.0.zip`  
2.Unzip this to yield a distro in the same dir.  
3.To run it, fire the following command where <topic_id> is the topic that the producer should publish message to.  
`QSampleProducer-1.0.0/bin/QSampleProducer <topic_id>`  

## QSampleConsumder
1.This is a sample consumer program  which can be run as command line once you build from the source.The zipped distro is found at 
`QSample/QSampleProducer/build/distributions/QSampleConsumer-1.0.0.zip`  
2.Unzip this to yield a distro in the same dir.  
3.To run it, fire the following command where <topic_id> is the topic that the producer should publish message to.  
`QSampleProducer-1.0.0/bin/QSampleProducer <topic_id> <consumer_id> <CSV_OF_DEPS>` 


