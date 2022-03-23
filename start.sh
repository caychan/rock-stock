#!/bin/bash

#项目路径（自定义）
CODE_PATH=~/project/rock-stock
APP_NAME=rock-stock.jar
echo "开始拉取最新代码"
#首先进行编译
cd ${CODE_PATH}
git pull origin master
echo "开始编译"
mvn clean package -Dmaven.test.skip=true
cd target/
#执行复制
mv rock-stock-0.0.1-SNAPSHOT.jar ${APP_NAME}
cp -r ${APP_NAME} ~/project
echo "开始进行复制"

#检查程序是否在运行
is_exist(){
pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
#如果不存在返回1,存在返回0
if [ -z "${pid}" ]; then
return 1
else
return 0
fi
}

is_exist
if [ $? -eq "0" ]; then
kill -9 $pid
else
echo "${APP_NAME} is not running"
fi


is_exist
if [ $? -eq "0" ]; then
kill -9 $pid
else
echo "${APP_NAME} is not running"
fi

nohup java -jar ${APP_NAME} > ~/project/console.log 2>&1 &

is_exist
if [ $? -eq "0" ]; then
echo "${APP_NAME} is already running. pid=${pid} ."
else
echo "${APP_NAME} is not running"
fi

#exit