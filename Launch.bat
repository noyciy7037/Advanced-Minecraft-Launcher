@echo off
cd %minecraftdir%
%command%
rd /s /q "%minecraftdir%\bin\%libuuid%"
echo result:%ERRORLEVEL%
pause