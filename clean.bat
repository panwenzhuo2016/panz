

echo %date% %time% ----------------start---------------- >> E:\idea\panz\log.txt

cd panz-back
call mvn clean   >> E:\idea\panz\log.txt

call :build panz-commons
call :build panz-core
call :build panz-security
call :build panz-web


echo success
echo %date% %time% ----------------end---------------- >> E:\idea\panz\log.txt
pause

:build
cd ..
cd %1
call  mvn clean   >> E:\idea\panz\log.txt
goto:eof


