FROM node:18.9-alpine3.15

WORKDIR .

ENV PATH /node_modules/.bin:$PATH

COPY package.json .

COPY package-lock.json .

RUN npm install --force

COPY . .

EXPOSE 3000

CMD ["npm","run","start"]