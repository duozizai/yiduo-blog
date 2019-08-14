FROM openjdk:8-jre-alpine
ENV TZ=Asia/Shanghai
CMD java ${JAVA_OPTS} -XX:AutoBoxCacheMax=20000 -Djava.security.egd=file:/dev/./urandom -jar /app.jar
ADD *.jar /app.jar
