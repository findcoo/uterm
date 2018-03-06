FROM gradle:4.5-alpine
LABEL Author="thirdlif2@gmail.com"

USER root
RUN apk --no-cache add yarn
WORKDIR /uterm
COPY ./ /uterm
RUN yarn install && yarn build
RUN gradle clean build
RUN chown -R gradle:gradle ./
USER gradle
EXPOSE 80 443 8080 8443 8999
CMD ["java", "-jar", "build/libs/uterm.jar"]