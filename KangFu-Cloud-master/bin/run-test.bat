@echo off
echo.
echo [��Ϣ] ʹ��Jar��������Test���̡�
echo.

cd %~dp0
cd ../kangfu-test/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 -jar %JAVA_OPTS% kangfu-test.jar

cd bin
pause