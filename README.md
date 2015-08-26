# Yle API Java client wrapper

## Introduction

This library is Java wrapper for The [Finnish Broadcasting Company](http://yle.fi/)'s ([http://en.wikipedia.org/wiki/Yle](Yle)) public
API for accessing everything available at their [public API](http://developer.yle.fi/).

This library aims to provide the following:

 - POJO based access to the API
 - provide automatic unmarshalling, ratelimiting, common error types etc.

## Licence

All `io.induct` libraries are licenced under [The MIT Licence](http://opensource.org/licenses/MIT).

## Usage

### Dependency

Maven:
```xml
<dependency>
    <groupId>io.induct</groupId>
    <artifactId>yle-api</artifactId>
    <version>0.1.0</version>
</dependency>
```
Gradle:
```groovy
compile 'io.induct:yle-api:0.1.0'
```

### Getting Started

Yle API uses [Google Guice](https://github.com/google/guice) as DI/IoC container and is currently hardcoded to rely on
it. To get the API working, you need to do the following:

 1. Acquire Yle API credentials (see [http://developer.yle.fi/](http://developer.yle.fi/))
 2. Add `io.induct.yle.ioc.YleApiModule` to your list of modules when creating new Guice `Injector`
 3. Bind any implementation of `io.induct.http.HttpClient` to scope
 4. Bind the following keys:
    - `yle.api.baseUrl` The root URL for all API calls, use `https://external.api.yle.fi` if you don't have any alternate URLs paired with your credentials
    - `yle.api.rateLimit` Rate limit count (calls/second) to use. Default is `10`
    - `yle.api.appId`, `yle.api.appKey`, `yle.api.streamKey` your Yle API credentials
 4. Use any of the `Yle*Api` classes as injectable dependencies as you normally would
    - `io.induct.yle.api.YleApi` is a simple container dependency which lists all available APIs which can be very handy during development

### Implementation gotchas

 - Due to way Jackson Joda-Time data binding module works all timestamps are normalized to UTC.