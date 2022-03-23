#!/bin/bash
#jdk环境变量
#export JAVA_HOME=/usr/java/jdk1.8.0_211-amd64
#export JAVA_BIN=$JAVA_HOME/bin
#export PATH=$PATH:$JAVA_HOME/bin
#export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
#export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
date=`date +%Y%m%d%H%M%S`
CODE_PATH=~/project/rock-stock
APP_NAME=rock-stock.jar
echo "开始拉取最新代码"
#首先进行编译
cd ${CODE_PATH}
git pull origin master
echo "开始编译"
mvn clean install -Dmaven.test.skip=true -P test
cd target/
#执行复制
mv rock-stock-0.0.1-SNAPSHOT.jar ${APP_NAME}
cp -r ${APP_NAME} ~/project/log
echo "开始进行复制"
logline=`cat ~/project/log/console.log | wc -l`
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
cd ~/project/log
is_exist
if [ $? -eq "0" ]; then
echo "${APP_NAME} is already running. pid=${pid} ."
else
nohup java -jar ${APP_NAME} > ~/project/log/console.log 2>&1 &
echo "程序已启动..."
sleep 1;
tail -10f ~/project/log/console.log | sed '/Starting Quartz Scheduler now/Q'
cat ~/project/log/console.log | sed -n ''"$logline"',${/Starting Quartz Scheduler now/, +3p}'
fi
is_exist
if [ $? -eq "0" ]; then
echo "${APP_NAME} is running. pid is ${pid} "
else
echo "${APP_NAME} is not running."
fi
exit