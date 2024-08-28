Before running the project locally (in intelliJ)
=================================================
1. Comment out this portion of docker-compose.yml

   web:
    build: .
    ports:
      -"8080:8080"
    depends_on:
      -mysql
    environment:
      - DATABASE_HOST=db
      - DATABASE_USER=user
      - DATABASE_PASSWORD=user
      - DATABASE_NAME=products
      - DATABASE_PORT=3306

2. replace line 4 of application.properties to this

	spring.datasource.url=jdbc:mysql://localhost:3306/products



Docker-compose
================
NOTE: UNDO THE CHANGES YOU HAVE MADE WHEN RUNNING LOCALLY BEFORE DOING THIS

1. remove existing image (if step 4 does not reflect your code changes)
        docker rmi <image id>

2. mvn clean install -DskipTests
3. docker-compose down
4. open a new terminal
5. docker-compose up

NOTE: remember to "docker-compose down" in first terminal to shut down your docker containers instead of ctrl-c in second terminal


SONARQUBE -- SETUP ONLY
=========
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:8-community

1 Run docker command above
2 Go to localhost:9000 user and password both admin
3 When see sonarqube dashboard add a project.
4 Itll ask for a key put whatever you want there
5 itll ask for a token. add some text then hit generate
6 itll then give you and option for the build hit maven
7 Itll auto generate an execution code. copy that(save it as Im unsure if we can get it again)
8 Run the code in the directory with your pom. 
9 Return to sonarqube and you should see results



Update Sonarqube
1. run mvn clean install - this will run all tests. If limiter on jenkins is remvoed it will take a long time. I recommend re implementing it to save you time
2. Run the execution code from step 7 above

To generate test reports
1. run mvn clean install  - this will run all tests. If limiter on jenkins is remvoed it will take a long time. I recommend re implementing it to save you time
2. run mvn jacoco:report
3. Run the execution code from step 7 above




