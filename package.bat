@echo off
mvn clean package  -Dspring.profiles.active=dev  -Dmaven.test.skip=true
pause