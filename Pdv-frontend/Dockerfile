# Use official node image as the base image
FROM node:lts as build

ENV NG_APP_API=http://localhost:9999
ADD ./package.json /tmp/package.json
RUN cd /tmp && npm install
RUN mkdir -p /usr/local/app && cp -a /tmp/node_modules /usr/local/app/
WORKDIR /usr/local/app
COPY ./ /usr/local/app/
RUN npm run build

FROM nginx:latest

COPY --from=build /usr/local/app/dist/build/browser /usr/share/nginx/html
COPY ./nginx.conf  /etc/nginx/conf.d/default.conf
EXPOSE 80 443 6006 4200

ENTRYPOINT ["nginx", "-g", "daemon off;"]