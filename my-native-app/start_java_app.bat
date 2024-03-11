
set JAVA_HOME=C:\Program Files\java\jdk-17
REM set JAVA_HOME=C:\Program Files\eclipse\plugins\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_17.0.2.v20220201-1208\jre
set PATH=%JAVA_HOME%\bin
cd "%~dp0"
cd target 
java -jar my-native-app.jar
pause