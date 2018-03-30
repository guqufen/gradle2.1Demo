@echo off
mvn clean package -Ptestlkl -Dmaven.test.skip=true
pause