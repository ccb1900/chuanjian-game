# @url https://laravel-news.com/multi-stage-docker-builds-for-laravel
# @url https://medium.com/@shakyShane/laravel-docker-part-2-preparing-for-production-9c6a024e9797
# @url https://about.gitlab.com/2016/08/05/continuous-integration-delivery-and-deployment-with-gitlab/#continuous-delivery
# @url https://docs.gitlab.com/ee/ci/examples/laravel_with_gitlab_and_envoy/
# PHP Dependencies
#
FROM composer:1.7 as vendor

COPY database/ database/

COPY composer.json composer.json
COPY composer.lock composer.lock

RUN composer config -g repos.packagist composer https://php.cnpkg.org && composer install \
    --ignore-platform-reqs \
    --no-interaction \
    --no-plugins \
    --no-scripts \
    --prefer-dist

#
# Frontend
#
#FROM node:8.11 as frontend
#
#RUN mkdir -p /app/public
#
#COPY package.json webpack.mix.js yarn.lock /app/
#COPY resources/assets/ /app/resources/assets/
#
WORKDIR /app
#
#RUN yarn install && yarn production

#
# Application
#
FROM php:7.2-apache-stretch

COPY . /var/www/html
COPY --from=vendor /app/vendor/ /var/www/html/vendor/
#COPY --from=frontend /app/public/js/ /var/www/html/public/js/
#COPY --from=frontend /app/public/css/ /var/www/html/public/css/
#COPY --from=frontend /app/mix-manifest.json /var/www/html/mix-manifest.json