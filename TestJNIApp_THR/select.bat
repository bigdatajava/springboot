@ECHO OFF
@ECHO STARTUP App
@echo 当前目录 %cd%
@ECHO 设置环境变量,循环当前目录下的lib目录下所有jar文件,并设置CLASSPATH
FOR %%F IN (%cd%\lib\*.jar) DO call :addcp %%F
goto extlibe
:addcp
SET CLASSPATH=%CLASSPATH%;%1
goto :eof
:extlibe
XCOPY .\src\com /E .\com
@ECHO 显示CLASSPATH
SET CLASSPATH 
@ECHO 运行应用程序
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
