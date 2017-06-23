@echo off
mvn clean package -Dspring.profiles.active=www -Dmaven.test.skip=true
pause