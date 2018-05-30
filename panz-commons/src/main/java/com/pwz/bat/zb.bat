

echo %date% %time%

cd entity
call svn update
call mvn clean
call mvn package

call :build jy-core
::call :build security
::call :build zhulong-common
::call :build jy-fuwu
call :build jy-zhaobiao
::call :build jy-toubiao


echo %date% %time%
pause

:build
cd ..
cd %1
call svn update
call  mvn clean
call mvn package
goto:eof


