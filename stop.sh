#!/bin/bash

pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `

sudo kill "${pid}"