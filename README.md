# Prerequisite

     you need to install GraalVM first of all regarding it's document
     
# For building native project

     $ mvn clean package -Pnative
     
# Execute microservice     
     
     $ cd target
     $ ./quarkus-example-1.0-SNAPSHOT-runner
     
# Result

     $  [io.quarkus] (main) Quarkus 0.16.1 started in 0.010s. Listening on: http://[::]:8080
     $  [io.quarkus] (main) Installed features: [cdi, resteasy, smallrye-context-propagation, smallrye-fault-tolerance, smallrye-health, smallrye-metrics]      