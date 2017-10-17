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

Service converts long URLs to short URLs using base 62 [A-Z, a-z, 0-9] codes. Service persists key value in DB and cache where tiny url is key and long url is value.

## Assumptions

### What if multiple users requests to get tiny url for same long url ?
Same tiny url will be generated using base 62.
### How much time system will keep tiny url ?
Application stores value in DB, so this can be retrieved anytime, there is no expiry.
In case, it is required a separate scheduled job can be performed.


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
