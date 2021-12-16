FROM node:latest

WORKDIR /app

COPY package.json .

RUN yarn install

COPY src ./src
COPY babel.config.js .
COPY tsconfig.json .
COPY .env .

RUN sed -i -e"s/^NODE_ENV=development/NODE_ENV=production/" ./.env

EXPOSE 3001

RUN yarn build

RUN rm -rf src babel.config.js tsconfig.json

CMD ["node", "./dist/shared/infra/http/server.js"]