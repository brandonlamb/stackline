FROM adoptopenjdk/openjdk8-openj9:jdk8u162-b12_openj9-0.8.0
#FROM adoptopenjdk/openjdk9-openj9:jdk-9.181
#FROM adoptopenjdk/openjdk10-openj9:nightly

RUN apt-get update && \
    apt-get dist-upgrade -y && \
    mkdir -p /opt/shareclasses && \
    mkdir -p /opt/app

COPY build/libs/product-write-v1-0.1-all.jar /opt/app/app.jar
COPY run.sh /opt/app

EXPOSE 8080
ENTRYPOINT ["/opt/app/run.sh"]
