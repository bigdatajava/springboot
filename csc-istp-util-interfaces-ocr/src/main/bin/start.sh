#!/bin/sh

APP_HOME=/usr/local/app/csc-istp-util-sms
APP_NAME=csc-istp-util-sms
PID_FILE=${APP_HOME}/${APP_NAME}.pid

if [ -f "$PID_FILE" ] && kill -0 $(cat "$PID_FILE"); then
echo "$APP_NAME is already running..."
exit 1
fi

echo "starting $APP_NAME ..."
nohup java -Xms256m -Xmx3g -Dfile.encoding=utf-8 \
				  -Dloader.path=${APP_HOME}/lib -jar ${APP_HOME}/${APP_NAME}.jar \
				  --spring.config.location=application.properties \
                  > ${APP_HOME}/${APP_NAME}.out 2> ${APP_HOME}/${APP_NAME}.err  &
echo $! > $PID_FILE
echo "$APP_NAME is started."
