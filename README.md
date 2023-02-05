# scala/benlipson-io

A barebones Scala app (using the Play framework), which can easily be deployed to Heroku.

This application support
the [Getting Started with Scala/Play on Heroku](https://devcenter.heroku.com/articles/getting-started-with-scala)
article - check it out.

## Running Locally

### Dependencies

- Play
- sbt
- [Heroku Toolbelt](https://toolbelt.heroku.com/)
- Java 8

Using SDKMan to install Java 8:

```sh
$ sdk install java 8.0.352-zulu
$ sdk use java 8.0.352-zulu
```

### Running

```sh
$ git clone git@github.com:blipson/benlipson.io.git
$ cd benlipson.io
$ sbt compile stage
$ heroku local
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku main
$ heroku open
```

or

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Documentation

For more information about using Play and Scala on Heroku, see these Dev Center articles:

- [Play and Scala on Heroku](https://devcenter.heroku.com/categories/language-support#scala-and-play)

