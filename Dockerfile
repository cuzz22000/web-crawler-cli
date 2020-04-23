
FROM openjdk:8u121-jre-alpine

# copy application files
COPY build/libs/web-crawler-cli-0.1.0.jar /app/webcrawler.jar

CMD ["sh","-c","java -jar /app/webcrawler.jar -f /app/out/output.txt ${crawl_url}"]
