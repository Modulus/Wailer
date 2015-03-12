#Move this to puppet when possible
env DB_USER / wailer
env DB_PASSWORD / missing_complaint
env DB_URL / jdbc:postgresql://localhost/wailings

VOLUME /var/www/wailer

add . /var/www/wailer