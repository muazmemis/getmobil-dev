FROM maven:3-sapmachine-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine

WORKDIR /app

ARG JAR_FILE=/app/.docker/*.jar
COPY --from=build ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# docker build -t muazmemis/getmobil .
# docker tag muazmemis/getmobil muazmemis/getmobil:1.0
# docker login -u muazmemis -p 123456
# docker push muazmemis/getmobil:1.0
# docker push muazmemis/getmobil:latest
