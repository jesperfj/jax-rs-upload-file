## JAX-RS with Grizzly

JAX-RS is a great way to build RESTful services in Java. This is a very quick and simple example of a REST service that lets you upload a file and later download it.

## Build and Run

Build the code with:

    $ mvn package

The POM file uses the [appassembler plugin](http://mojo.codehaus.org/appassembler/appassembler-maven-plugin/) to generate a wrapper script, so it's very simple to run the app. Simply execute:

    $ sh target/bin/app
    Starting grizzly...
    Oct 25, 2011 10:06:44 AM com.sun.grizzly.Controller logVersion
    INFO: Starting Grizzly Framework 1.9.18-i - Tue Oct 25 10:06:44 PDT 2011
    Jersey started with WADL available at http://localhost:9998/application.wadl.

(On Windows use target/bin/app.bat instead)

## Upload a file

    $ curl http://localhost:9998/blob -F "file=@myfile.ext;filename=myfile.ext"

will upload the file `myfile.ext` from current directory.

## Download the file

    $ curl -O http://localhost:9998/blob/myfile.ext

will save the file you just uploaded in the current directory

## Deploying to Heroku

Assuming you're already set up with Heroku, all you need to do to is:

1. heroku create --stack cedar
2. git push heroku master

It's that simple. 

Note that files uploaded to Heroku using this app will get stored on the ephemeral disk space of the dyno receiving the file. These files are lost each time dynos are restarted, so you have a bit more homework to do before you have your own personal dropbox service.

