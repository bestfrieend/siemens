# Siemens

# 1. Build instructions:
To Build the project open command line in the project directory and run the following command:

	gradlew build

This will generate the executable package and also will run the code coverage report under build/reports/jacoco/test/html/index.html
current code coverage is 90%

# 2. Run the project
to run the project run the project with 
    
    docker-compose up

# 3. Additional information:
Swagger URI: http://localhost:8080/swagger-ui.html
application.properties should be updated for Mongo DB configurations before executing the service name should be updated to "mongo"
