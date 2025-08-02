# Fixing Kafka Shell Script Execution Issues in WSL

When running Apache Kafka in WSL (Windows Subsystem for Linux), you might encounter this error:

```bash
-bash: bin/zookeeper-server-start.sh: cannot execute: required file not found
```

Even though the file exists, this happens because the `.sh` scripts have **Windows-style line endings (CRLF)** which are incompatible with the Linux shell.

## ✅ Solution: Convert Kafka Scripts to Unix Format

### 1. Install `dos2unix` (only once)

```bash
sudo apt update
sudo apt install dos2unix
```

### 2. Navigate to your Kafka folder

```bash
cd /path/to/kafka_2.13-3.6.1
```

For example:

```bash
cd /mnt/c/Users/jangi/apache-kafka/kafka_2.13-3.6.1
```

### 3. Convert all `.sh` files to Unix line endings

```bash
dos2unix bin/*.sh
```

You should now see output like:

```
dos2unix: converting file bin/zookeeper-server-start.sh to Unix format...
dos2unix: converting file bin/kafka-server-start.sh to Unix format...
dos2unix: converting file bin/kafka-server-stop.sh to Unix format...
```

### 4. (Optional) Make sure scripts are executable

```bash
chmod +x bin/*.sh
```

## ✅ Test It

Now try running:

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

If it still fails, move Kafka to your WSL home directory:

```bash
cp -r /mnt/c/Users/jangi/apache-kafka ~/
cd ~/apache-kafka/kafka_2.13-3.6.1
```

Then run the script again.

## ✅ Verify Line Ending Fix

To confirm that the CRLF issue is fixed:

```bash
file bin/zookeeper-server-start.sh
```

You should see:

```
bin/zookeeper-server-start.sh: ASCII text executable
```

If it still says `with CRLF line terminators`, re-run `dos2unix`.

## ✅ Quick Reference Summary

| Step | Command |
|------|---------|
| Install tool | `sudo apt install dos2unix` |
| Convert scripts | `dos2unix bin/*.sh` |
| Make executable | `chmod +x bin/*.sh` |
| Run Kafka | `bin/zookeeper-server-start.sh config/zookeeper.properties` |

## ✅ Complete Kafka Startup Sequence

Once the line endings are fixed, here's the typical Kafka startup process:

### Start Zookeeper (Terminal 1)
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

### Start Kafka Server (Terminal 2)
```bash
bin/kafka-server-start.sh config/server.properties
```

### Create a Topic (Terminal 3)
```bash
bin/kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

### Start Producer (Terminal 3)
```bash
bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
```

### Start Consumer (Terminal 4)
```bash
bin/kafka-console-consumer.sh --topic test-topic --from-beginning --bootstrap-server localhost:9092
```

## 🛠️ Troubleshooting

### Common Issues and Solutions

1. **Permission denied errors**
   ```bash
   chmod +x bin/*.sh
   ```

2. **Scripts still not working after dos2unix**
   - Copy Kafka to WSL filesystem instead of Windows mount
   ```bash
   cp -r /mnt/c/path/to/kafka ~/kafka
   ```

3. **Zookeeper connection refused**
   - Check if Zookeeper is running first
   - Verify port 2181 is not in use
   ```bash
   netstat -an | grep 2181
   ```

4. **Kafka broker connection issues**
   - Ensure Zookeeper is running before starting Kafka
   - Check port 9092 availability
   ```bash
   netstat -an | grep 9092
   ```

---

**Note**: This guide assumes Kafka version 2.13-3.6.1. Adjust paths and versions as needed for your specific Kafka installation.