#!/bin/sh

SERVICE=DieselnetSMSService
JAR_PATH=$PWD/build/libs/dieselnet_sms_service-1.0-SNAPSHOT.jar
PID_PATH=/tmp/DieselnetSMSService-pid

start()
{
    stop
    echo "\n"
    echo "Starting $SERVICE"
    nohup java -jar $JAR_PATH /tmp 2>> /dev/null >> /dev/null &
    echo $! > $PID_PATH
    echo "$SERVICE started: PID $!"
}

stop()
{
    if [ -f $PID_PATH ]; then
        PID=$(cat $PID_PATH);
        echo "$SERVICE stoping: PID $PID"
        kill $PID;
        echo "$SERVICE stopped: PID $PID"
        rm $PID_PATH
    fi
}

build_start()
{
    $PWD/gradlew build
    echo "\n"
    start
}

case $1 in
    build-start)
        build_start
    ;;
    start)
        start
    ;;
    stop)
        stop
    ;;
esac