# 1. Java 이미지를 기반으로 설정
FROM openjdk:17-jdk

ARG JAR_FILE=/build/libs/*.jar

# 3. JAR 파일 복사
COPY ${JAR_FILE} app.jar

# 4. 환경 변수로 Spring Profile 설정 (기본값: local)
ENV SPRING_PROFILES_ACTIVE=prod

# 5. 애플리케이션 실행
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]
