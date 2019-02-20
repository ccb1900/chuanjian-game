#!/usr/bin/env bash
composer validate --no-check-all --strict
chown -R www-data:www-data .
chmod 775 storage/ bootstrap/cache/
composer install -o
php artisan config:cache
php artisan route:cache