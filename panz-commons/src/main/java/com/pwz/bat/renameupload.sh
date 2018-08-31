#!/bin/bash
time=`date "+%Y-%m-%d-%H:%M:%S"`
name=$1
if [ "${name}" == "t" ]; then
   name="jy-toubiao"
elif [ "${name}" == "z" ]; then
   name="jy-zhaobiao"
else
   name="jy-fuwu"
fi

cd /home/weblogic/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain/upload/
echo ${name}".war" ${name}${time}"pwz.war"
mv  ${name}".war" ${name}${time}"pwz.war"
