
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

## Project Philosophy

The overall goal of Windlass is to build a web framework layer over top of Spray that is focused
on building web applications.  It should make it easy to do the right thing and add friction around
doing the wrong thing.

#### No Magic, Just Scala

At all costs, avoid "magical" Scala DSL's.  It's better to implement something using "plain old" Scala, than to try and get clever with implicit classes and operators.  Things which deserve a DSL:  describing 3D objects, writing tests.  Things which don't deserve a DSL:  Configuring a web framework, describing an HTTP response.  A DSL is only warrented based on the frequency something may be used and the amount of expressitivity it requires.  Most libraries have the latter without the former.  Configuring happens once in a web app and doesn't need it's own language.

Implicit type conversion and implicit classes may be used in places where a decorator pattern would normally be used, but it should be the final resort, not the first thing reached for.

#### Exposed Wiring

Black boxes aren't allowed.  Instead, things should be wired up "manually".  At some future point in the project a scaffolding system can be used to reduce the boilerplate of this wiring.  A developer should be able to sit down with a Windlass project and understand the path that a request moves through the system.

#### Worse is Better

Ease of use will in most cases trump HTTP protocol support.  The framework should be focused and optimized around building a traditional request/response web application, and advanced support of HTTP streaming shouldn't be a factor.  These more advanced HTTP features would be supported by dropping down to Spray, rather than Windlass supporting them directly.  This area needs some development.

#### (Default) Batteries Included

It should be possible to get going out of the gate with almost minimal fuss.  However other options should be pluggable.

## Contributing

Contributions are accepted as pull requests.

Please follow the official Scala Style Guide along with the style recommendations in Twitter's Effective Scala.

### Sub-Components (that need designed and built):

#### HTTP Pipeline

Currently under development by myself.  Open to discussion about it.

#### Controllers

Need flushed out.  Don't have any strong opinions about how this should look.

#### Templating

Open to ideas about what should happen here.  I think there's room for inovation in templating and in making re-usable HTML components.  Send me an email and I'll shoot you back some high level ideas I think should be captured.  Aside from concept work, some kind of template engine adaptation system will be needed, where existing template engines can *easily* be plugged in to Windlass.

#### Asset Pipeline

There's work out there already with sbt-less and so forth.  This needs to be defined further and built out.

#### Asset Testing

A package to drop in javascript testing would be awesome.

#### Scaffolding system

There might be an existing solution already, but something where it's easy to create sbt commands that generate disk files.

#### Misc Stuff

  - There needs to be a better way to run the example app than to copy the .jar into /lib.  I'm not a Maven/Ivy expert but there's got to be a way to do that.
  - Specs2 needs to get setup for this project.  We aren't writing tests just yet as everything changes whenever I sit down at the keyboard.  The project is mostly a spike at this point.  Tests will come once we start solidifying on interfaces.