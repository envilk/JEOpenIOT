cd C:\Users\klivne\eclipse-workspace\JEOpenIOT
mvn install
cd C:\glassfish5\bin
asadmin start-domain
asadmin start-database
asadmin deploy C:\Users\klivne\eclipse-workspace\JEOpenIOT\target\JEOpenIOT.war