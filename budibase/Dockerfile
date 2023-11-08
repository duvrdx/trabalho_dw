FROM ubuntu:latest AS build
RUN apt update
RUN apt install npm -y

COPY . .

RUN npm i -g @budibase/cli

EXPOSE 10000

ENTRYPOINT [ "budi",  "hosting", "--start"]
