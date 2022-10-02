FROM node:13.12.0-alpine

ENV APP_HOME=/usr/src/programsch-frontend

WORKDIR ${APP_HOME}
# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH


RUN npm install
RUN npm install react-scripts@3.4.1 -g

COPY package.json ./
COPY package-lock.json ./
# add app
COPY . ./

# start app
CMD ["npm", "start"]