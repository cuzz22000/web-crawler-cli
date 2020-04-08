
FROM openjdk:8u121-jre-alpine

# copy application files
COPY build/libs/web-crawler-cli-0.0.2.jar /app/webcrawler.jar

CMD ["sh","-c","java -jar /app/webcrawler.jar -l ${crawl_url} -f /app/out/output.txt"]
