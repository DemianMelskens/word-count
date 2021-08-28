FROM payara/micro:5.2021.6-jdk11
CMD ["--deploymentDir", "/opt/payara/deployments", "--noCluster"]
ADD build/libs/word-count-1.0.war /opt/payara/deployments
EXPOSE 8080