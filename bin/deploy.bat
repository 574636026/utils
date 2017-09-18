echo off

cd %~dp0

cd ..

echo [INFO]修改版本打包脚本 请输入需要升级的版本:

set /p newVersion= 

if "%newVersion%" NEQ "" goto version

goto package

:version

echo [INFO]输入%newVersion% 开始替换版本

call mvn clean versions:set -DnewVersion=%newVersion%

echo [INFO]请检查是否所有子模块都升级版本成功 新版本为%newVersion%

pause

:package
echo [INFO]开始打包...

call mvn clean deploy -DskipTests=true

echo [INFO]打包结束

pause