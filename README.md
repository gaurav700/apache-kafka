# Apache kafka

## What is kafka?
- Apache Kafka is an open-source distributed event streaming platform.
- It’s used for building real-time data pipelines and streaming applications.
- It lets you publish, subscribe to, store, and process streams of records (events/messages) efficiently.

## Where does kafka come from?
- Kafka was originally developed at LinkedIn to handle large volumes of activity logs.
- It was open-sourced in early 2011 and is now maintained by the Apache Software Foundation.

## Why do we need kafka?
- We use Kafka because:
    - It handles high-throughput, low-latency, and real-time data streaming.
    - It decouples producers and consumers — making systems more scalable and fault-tolerant.
    - It’s great for:
        - Microservices communication using events
        - Real-time analytics (e.g. fraud detection)
        - Log aggregation
        - ETL pipelines
        - IoT and sensor data ingestion
    - It persists messages so consumers can process them at their own pace, even if they go down temporarily.
  
![Architecture](kafka.png)

## How does it work?

![Kafka Architecture](architecture.png)


## Kafka Architecture and components
- Producer : Publish a message consumes by consumer
- Consumer : Consume a message send by producer
- Broker : Just a server or in other words, an entity that helps in message exchanges between a producer and consumer.
- Cluster : Group of servers or brokers
- Topic : Topic is somthing to whom a consumer subscribes and a producer publishes
- Partitions
- Offset
- Consumer Groups
- Zookeeper