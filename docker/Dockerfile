FROM eoniantech/tomcat:8.0.41-jre8-alpine

ENV CATALINA_HOME /usr/local/tomcat
ENV CATALINA_BASE /opt/servers/echoapi-tomcat8-8443

RUN mkdir -p ${CATALINA_BASE}
COPY catalina-base/ ${CATALINA_BASE}/
RUN chmod 600 ${CATALINA_BASE}/conf/jmxremote.password
RUN ln -s ${CATALINA_BASE}/build/* ${CATALINA_BASE}/webapps/*.war
WORKDIR ${CATALINA_BASE}

RUN keytool -genkey -keyalg RSA -alias tomcat -keystore ${CATALINA_BASE}/conf/keystore.jks \
        -storepass storepass -keypass storepass -keysize 2048 \
        -dname o=EonianTechnologies,ou=PlatformEngineering,c=US,cn=echoapi.eoniantech.com

VOLUME ${CATALINA_BASE}

EXPOSE 8443
EXPOSE 8020
