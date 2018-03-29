@echo off
mvn clean package -Pprodlkl -Dmaven.test.skip=true
pause