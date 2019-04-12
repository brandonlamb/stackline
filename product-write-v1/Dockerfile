FROM adoptopenjdk/openjdk11-openj9

RUN apt-get update && \
    apt-get dist-upgrade -y && \
    mkdir -p /opt/shareclasses && \
    mkdir -p /opt/app

COPY build/libs/product-write-v1-0.1-all.jar /opt/app/app.jar
COPY run.sh /opt/app

EXPOSE 8080
ENTRYPOINT ["/opt/app/run.sh"]
