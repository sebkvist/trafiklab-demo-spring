
To run and test the application:
in the project root:
-to build:
mvn package
-to run:
java -jar target/<build-artifact>.jar

---Man kan köra ett test med mockdata på url:
===
http://localhost:8080/api/demo/mockinfo
- Detta får servern att anropa sig själv och ge en respons med mock-data.

---För att testa mot trafiklabs webbsida:
===
I propeties-filen:  src/main/resources/application.properties)
sätt ert nyckelvärde på property trafiklab.param.key=

Kör mot url:
http://localhost:8080/api/demo/mockinfo