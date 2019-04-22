#!/bin/sh

APP_HOME=/usr/local/app/csc-istp-util-sms
APP_NAME=csc-istp-util-sms
PID_FILE=${APP_HOME}/${APP_NAME}.pid

if [ ! -f "$PID_FILE" ] || ! kill -0 $(cat "$PID_FILE"); then
echo "$APP_NAME is already stopped..."
else
echo "stopping $APP_NAME"
PID=$(cat "$PID_FILE")
kill -9 $PID
rm -f "$PID_FILE"
echo "$APP_NAME is stopped."
fi

