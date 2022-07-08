#FROM gradle:7.4-jdk11-alpine AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle bootJar
#
#FROM openjdk:11-jre-slim
#EXPOSE 8098
#COPY --from=build /home/gradle/src/build/libs/*.jar ./app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]
#CMD java $JAVA_OPTIONS -jar app.jar

FROM openjdk:11-jre-slim
EXPOSE 8098
COPY  /build/libs/*.jar ./app.jar
CMD ["java","-jar","app.jar"]


#
#FROM gradle:7.4-jdk11-alpine as cache
#RUN mkdir -p /home/gradle/cache_home
#ENV GRADLE_USER_HOME /home/gradle/cache_home
#COPY build.gradle /home/gradle/java-code/
#WORKDIR /home/gradle/java-code
#RUN gradle clean build -i --stacktrace -x bootJar
#FROM gradle:7.4-jdk11-alpine as builder
#COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
#COPY . /usr/src/java-code/
#WORKDIR /usr/src/java-code
#RUN gradle bootJar -i --stacktrace
#FROM openjdk:11-jre-slim
#EXPOSE 8080
#USER root
#WORKDIR /usr/src/java-app
#COPY --from=builder /usr/src/java-code/build/libs/*.jar ./app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

