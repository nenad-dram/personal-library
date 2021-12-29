FROM openjdk:17-oracle

ARG LAYERS_DIR=build/layers

COPY ${LAYERS_DIR}/BOOT-INF/lib /perlib/lib
COPY ${LAYERS_DIR}/BOOT-INF/classes /perlib/classes
COPY ${LAYERS_DIR}/META-INF /perlib/META-INF

WORKDIR /perlib

EXPOSE  8080

ENTRYPOINT ["java","-cp","./classes:./lib/*","com.endyary.perlibserver.PerlibApplication"]