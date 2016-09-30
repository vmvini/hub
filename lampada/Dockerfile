FROM mhart/alpine-node-auto
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . /usr/src/app/
RUN npm install
CMD node lampada.js


