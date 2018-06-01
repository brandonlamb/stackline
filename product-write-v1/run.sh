#!/bin/sh

java \
    -XX:+PrintFlagsFinal \
    -XX:+UnlockExperimentalVMOptions \
    -XX:+UseCGroupMemoryLimitForHeap \
    -XX:+CMSClassUnloadingEnabled \
    -XX:+IdleTuningGcOnIdle \
    -Xtune:virtualized \
    -Xshareclasses:cacheDir=/opt/shareclasses \
    -jar \
    /opt/app/app.jar
