# PasswordGenerator

Password Generator is a simply apllication that allows you to randomly generate numeric, text, alphanumeric and alphanumeric + symbols passwords from 8 to 20 characters.

## Deploying to Production

To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/passwordgenerator-1.0-SNAPSHOT.jar`
