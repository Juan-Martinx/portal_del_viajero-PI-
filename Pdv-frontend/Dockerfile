FROM nginx:latest

ADD ./dist/build/browser /usr/share/nginx/html
COPY ./nginx.conf  /etc/nginx/conf.d/default.conf
EXPOSE 80 443 6006 4200 9876

ENTRYPOINT ["nginx", "-g", "daemon off;"]