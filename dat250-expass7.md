# Expass 7

### Dockerized application: PostgreSQL
The first thing I did was to download a docker image of PostgreSQL. I did so with a command line "docker pull postgres". And then I ran it and determined which port to expose and using default environment variables to set.
After I did that I checked that it was running with "docker ps". Then I connected the database to a SQL-client DBeaver. And created a user for JPA client. Then I had to add some dependency in "build.gradle.kts" and in "persistence.xml".
Then I had to run the test again, but it failed. To fix it I had to create the tables again, but with the JPA client user with granted permission.

### Building own dockerized application
I wrote my own dockerfile. Using the example from lecture 14, it gave clues to how to set it up. Then I had to use docker build and then docker run to check if it worked.