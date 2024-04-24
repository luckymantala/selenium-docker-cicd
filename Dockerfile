FROM bellsoft/liberica-openjdk-alpine:17.0.8

# install curl, jq
RUN apk add curl jq dos2unix

# Workspoace
WORKDIR /home/selenium-docker-cicd

# Add the required files
#  ./ or . means WORKDIR
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh 

# convert runner.sh to unix
RUN dos2unix runner.sh

# Run runner.sh
ENTRYPOINT sh runner.sh