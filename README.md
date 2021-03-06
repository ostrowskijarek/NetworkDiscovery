This is just a PoC application to demonstrate combined usage of the following technologies:

1. SpringBoot -> RestController, RestTemplate, Lombok, Slf4j, DI, TaskExecutor, DiscoveryClient
2. Spring Cloud -> HashiCorp Consul
3. Maven -> Dependencies, Resources, Build
4. Centos 7

The way I run it is to run "Manager" instance locally (from my IDE - STS4 or maven) altogether with the consul client.
To make it to pass unit test you need to instal nmap and add it to your path under env vars.

Starting consul agent (dev), replace client and bind with your network details (same as the vms reside)
consul agent -dev -client=192.168.1.114 -bind 192.168.1.114

You need at least two Scanners to test the load balancing capabilites. I used two Centos7 vms running locally, connected to the same network as my "host" PC.

I had to install (on minimal Centos 7 image):

yum install java-1.8.0-openjdk-devel

yum install git

yum install maven

yum install nmap

Then clone the repo (use github clone cmd) and build it with maven:

mvn package -Dpurpose=Scanner

Go to the target directory:

java -jar Network*.jar

Please note that this is just a PoC so some methods are not designed to work properly but only under a certain input!
The most important one is: NetworkToolsImpl which splits the given range for the discovered number of scanners.

Once everything is up and running, call the Manager:

http://127.0.0.1:8080/start?ipScope=192.168.1.1-254

The manager splits the range and sends each piece to each registered scanner through RestAPI
Once the scan is complete, Scanners send back the output to Manager, which simply displays it in the console.
