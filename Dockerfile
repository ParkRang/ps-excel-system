# 1. JDK 24 기반 이미지 사용
FROM eclipse-temurin:24-jdk

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 JAR 복사
COPY build/libs/ps-excel-system-0.0.1-SNAPSHOT.jar backend.jar

# 4. 컨테이너 실행 시 JAR 실행
ENTRYPOINT ["java", "-jar", "backend.jar"]
