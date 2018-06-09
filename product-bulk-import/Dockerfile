FROM adoptopenjdk/openjdk8-openj9:jdk8u162-b12_openj9-0.8.0

RUN apt-get update && \
    apt-get dist-upgrade -y && \
    mkdir -p /opt/shareclasses && \
    mkdir -p /opt/app

COPY build/libs/product-bulk-import-0.1.jar /opt/app/app.jar
COPY run.sh /opt/app

ENTRYPOINT ["/opt/app/run.sh"]
