@echo off
mvn clean package -Pprop -Dmaven.test.skip=true
pause