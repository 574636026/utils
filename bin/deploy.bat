echo off

cd %~dp0

cd ..

echo [INFO]�޸İ汾����ű� ��������Ҫ�����İ汾:

set /p newVersion= 

if "%newVersion%" NEQ "" goto version

goto package

:version

echo [INFO]����%newVersion% ��ʼ�滻�汾

call mvn clean versions:set -DnewVersion=%newVersion%

echo [INFO]�����Ƿ�������ģ�鶼�����汾�ɹ� �°汾Ϊ%newVersion%

pause

:package
echo [INFO]��ʼ���...

call mvn clean deploy -DskipTests=true

echo [INFO]�������

pause