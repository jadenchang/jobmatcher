FROM openjdk:8-jdk-alpine

RUN cd /

COPY  ./target/jobmatcher-0.0.1-SNAPSHOT.jar /

EXPOSE 8080

ENTRYPOINT ["java","-jar","/jobmatcher-0.0.1-SNAPSHOT.jar"]