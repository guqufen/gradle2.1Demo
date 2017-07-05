@echo off
mvn clean package -Pprod -Dmaven.test.skip=true
pause