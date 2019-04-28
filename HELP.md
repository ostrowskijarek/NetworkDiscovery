# Getting Started

#This is just a PoC application to demonstrate combined usage of the following technologies:
# 1. SpringBoot -> RestController, RestTemplate, Lombok, Slf4j, DI, TaskExecutor, DiscoveryClient
# 2. Spring Cloud -> HashiCorp Consul
# 3. Maven -> Dependencies, Resources, Build
# 4. Centos 7

#The way I run it is to run "Manager" instance locally (from my IDE - STS4) altogether with the consul client.
#To make it to pass unit test you need to instal nmap and add it to your path under env vars.

#Starting consul agent (dev), replace client and bind with your network details (same as the vms reside)
consul agent -dev -client=192.168.1.114 -bind 192.168.1.114

#You need at least two Scanners to test the load balancing capabilites. I used two Centos7 vms running locally, connected to the same network as my "host" PC.
#I had to install (on minimal Centos 7 image):
yum install java-1.8.0-openjdk-devel
yum install git
yum install maven
yum install nmap
#then clone the repo (use github clone cmd)
#and build it with maven




#Next thing is to build two scanner instances by passing -Dpurpose=Scanner parameter to the build command.
#(you can also build as -Dpurpose=Manager, however that value comes by default)
mvn package -Dpurpose=Scanner



#Please note that this is just a PoC so some methods are not designed to work properly but only under a certain input!
#The most important one is: NetworkToolsImpl which just splits a given range (also it needs to be a "slightly" greater than the number of scaners), best is just to give something like: 10.10.10.10-20 (RANGES! not subnets/ip addresses etc...)

#Once everything is up and running, call the Manager with:
127.0.0.1:8080/start?ipScope=192.168.1.1-254

#Then the manager splits the range and sends each piece to each registered scanner through RestAPI
#Once the scan is complete, Scanners send back the output to Manager, which simply displays it in the console.




