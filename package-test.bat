@echo off
mvn clean package -Ptest -Dmaven.test.skip=true
pause