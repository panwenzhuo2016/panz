

echo %date% %time% ----------------start----------------  >> D:\zhengshi2\log.txt

cd entity
call svn update >> D:\zhengshi2\log.txt
call mvn clean >> D:\zhengshi2\log.txt
call mvn install >> D:\zhengshi2\log.txt

call :build jy-core
::call :build security
call :build zhulong-common
::call :build jy-fuwu
::call :build jy-zhaobiao
::call :build jy-toubiao
call :build jy-jobtask


echo success
echo %date% %time% ----------------end---------------- >> D:\zhengshi2\log.txt
pause

:build
cd ..
cd %1
call svn update >> D:\zhengshi2\log.txt
call  mvn clean >> D:\zhengshi2\log.txt
call mvn install >> D:\zhengshi2\log.txt
move target\%1-1.0.0-SNAPSHOT.war C:\Users\Bruin\Desktop\%1.war
goto:eof


