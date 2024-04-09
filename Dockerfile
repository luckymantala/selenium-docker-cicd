FROM bellsoft/liberica-openjdk-alpine:17.0.8

# install curl, jq
RUN apk add curl jq

# Workspoace
WORKDIR /home/selenium-docker-cicd

# Add the required files
#  ./ or . means WORKDIR
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh 

# Run runner.sh
ENTRYPOINT sh runner.sh