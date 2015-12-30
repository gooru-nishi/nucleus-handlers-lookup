Nucleus Lookup
================

This is the Lookup handler for Project Nucleus. 

This project contains just one main verticle which is responsible for listening for Lookup address on message bus. 

DONE
----
* Configured listener
* Provided a initializer and finalizer mechanism for components to initialize and clean up themselves
* Created a data source registry and register it as component for initialization and finalization
* Provided Hikari connection pool from data source registry
* Processor layer is created which is going to take over the message processing from main verticle once message is read
* Logging and app configuration
* Transformer layer working for positive cases
* DB layer to actually do the operations
* Transformer layer implementation so that output from DB layer could be transformed and written back to message bus
* Incorporate ActiveJdbc into project and build

TODO
----
* There are other lookups in form of enums in DB. Need to implement that. They are moments of learning, depth of knowledge,
   audience, 21st century skills
* Handle exceptions for the transformers

To understand build related stuff, take a look at **BUILD_README.md**.


