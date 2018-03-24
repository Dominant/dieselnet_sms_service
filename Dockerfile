FROM openjdk:8

RUN apt-get update && apt-get install sudo

RUN sudo wget "https://bouncycastle.org/download/bcprov-ext-jdk15on-158.jar" -O "${JAVA_HOME}"/jre/lib/ext/bcprov-ext-jdk15on-158.jar && \
  sudo perl -pi.bak -e 's/^(security\.provider\.)([0-9]+)/$1.($2+1)/ge' /etc/java-8-openjdk/security/java.security && \
  echo "security.provider.1=org.bouncycastle.jce.provider.BouncyCastleProvider" | sudo tee -a /etc/java-8-openjdk/security/java.security

RUN mkdir -p /var/dieselnet_sms_service && chmod 0755 /var/dieselnet_sms_service

COPY ./ /var/dieselnet_sms_service
WORKDIR /var/dieselnet_sms_service

RUN echo 'hosts: files mdns4_minimal [NOTFOUND=return] dns mdns4' >> /etc/nsswitch.conf

RUN ./gradlew build
RUN ./gradlew jar

ENV PATH $PATH:$JAVA_HOME/bin

RUN mv /var/dieselnet_sms_service/build/libs/dieselnet_sms_service-1.0-SNAPSHOT.jar /dieselnet_sms_service-1.0-SNAPSHOT.jar
RUN chmod 0555 /dieselnet_sms_service-1.0-SNAPSHOT.jar

CMD ["java", "-jar", "/dieselnet_sms_service-1.0-SNAPSHOT.jar"]
