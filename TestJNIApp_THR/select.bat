@ECHO OFF
@ECHO STARTUP App
@echo ��ǰĿ¼ %cd%
@ECHO ���û�������,ѭ����ǰĿ¼�µ�libĿ¼������jar�ļ�,������CLASSPATH
FOR %%F IN (%cd%\lib\*.jar) DO call :addcp %%F
goto extlibe
:addcp
SET CLASSPATH=%CLASSPATH%;%1
goto :eof
:extlibe
XCOPY .\src\com /E .\com
@ECHO ��ʾCLASSPATH
SET CLASSPATH 
@ECHO ����Ӧ�ó���
javac  -encoding utf-8 src/com/idcard/CBInterface.java
javac  -encoding GBK com/idcard/Demo.java
javac  -encoding GBK com/idcard/GlobalData.java
javac  -encoding GBK com/idcard/StringManager.java
javac  -encoding GBK com/turec/idcard/OCREngine.java
javac  -encoding GBK com/turec/idcard/OCREngine4.java
javac  -encoding GBK com/turec/idcard/OCREngine2.java
javac  -encoding GBK com/turec/idcard/OCREngine3.java
javac  -encoding GBK com/turec/idcard/OCRInter.java
javac  -encoding GBK com/turec/idcard/OCRMAIN.java
@echo "running"
:restart
java -server -Xms512m -Xmx1024m -XX:MaxNewSize=1024m com.turec.idcard.OCRMAIN
@echo "goto running!"
cd  /d %~dp0
rd /s/q .\com
mkdir com
pause
