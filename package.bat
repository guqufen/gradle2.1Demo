@echo off
mvn clean package  -Pdev  -Dmaven.test.skip=true
pause