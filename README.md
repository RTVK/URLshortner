# Service to generate tiny url

tinyurlserv provides interface to  :
1) Create tiny urls from long / actual URLs
2) Retrieve long / actual URLs for given tiny URL

Screen shots can be found in doc folder of this repo.

## Use Case
User enters URL in /index.jsp, which then comes to service [Java backend] to be shortify.
Once big URL gets converts into short URL, page tinyurl.jso renders tiny url value with link to navigate.

If user clicks on navigate link or enters short url in address bar, service reroutes user to long URL.

## Base62 equivalent 

Service converts long URLs to short URLs using base 62 [A-Z, a-z, 0-9] codes.

## Performance
Before any encode / DB operation, service looks for value in ehcache. Currently, Ehcache stores values for 24 hours, which could
be increased.
Currently, I have in=mplemented, in memory cache but memcached or cohrence can be used.
In case cache call fails, service stores all tiny url and long url combination in in-memory H2 db.

### Technology Stack
Application is written using :

1) Spring Boot
2) JPA - Hibernate
3) JSP
4) Ehcache
5) H2 DB
6) Mockito & PowerMock - WIP


## Schema

DB table contains map values to relate TinyUrls to shortUrl. 

### Start Application

Application can be start by executing main method of TinyUrlApplication.java

## Authors

* **Ritvik Pandya** - https://www.linkedin.com/in/ritvik-pandya-70251b15/
