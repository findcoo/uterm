FROM gradle:4.5-alpine
MAINTAINER findcoo <thirdlif2@gmail.com>

USER root
WORKDIR /uterm
COPY ./ /uterm
RUN gradle clean build
RUN chown -R gradle:gradle ./
USER gradle
EXPOSE 80 443 8080 8443