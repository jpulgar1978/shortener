FROM openjdk:17-jdk-slim as builder
WORKDIR /project
COPY . .
RUN ./mvnw package -DskipTests
RUN java -Djarmode=layertools -jar target/desafio.jar extract

FROM openjdk:17-alpine
RUN apk --no-cache add curl dumb-init
WORKDIR /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
USER javauser

COPY --from=builder --chown=javauser:javauser /project/dependencies/ ./
COPY --from=builder --chown=javauser:javauser /project/snapshot-dependencies/ ./
COPY --from=builder --chown=javauser:javauser /project/spring-boot-loader/ ./
COPY --from=builder --chown=javauser:javauser /project/application/ ./

## --------------------------------
EXPOSE 8080
ENTRYPOINT [ "java", "org.springframework.boot.loader.JarLauncher" ]
#CMD "dumb-init" "java" "org.springframework.boot.loader.JarLauncher"