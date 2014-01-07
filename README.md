
# Windlass

Currently, a very rough pass at some ideas I've had about a web application framework layer built on top of spray.

It's not even close to being done yet.  In fact, most of it isn't tested as it's really just a spike at the moment.

## Trying It Out

```
$ git clone git@github.com:tstone/Windlass.git
$ cd windlass/src/examples/simple
$ sbt run
```

Then open a browser to http://localhost:9000/test.

### TODO

  - Figure out a better REPL cycle for the example app.  Copying the .jar to `/lib` every time is stupid.
  - Figure out the `Request`/`Response` models for real
  - Setup Specs2 and write some tests for things
  - Flush out what `Controller` actually has on it
  - Setup some kind of template rendering integration
  - Learn more about chunking/streaming and how that can be made simple