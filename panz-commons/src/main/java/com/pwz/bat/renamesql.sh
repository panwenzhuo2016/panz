#!/bin/bash
time=`date "+%Y-%m-%d-%H:%M:%S"`
name=$1
cd /home/weblogic/local/java/filestorages/sqlfile/
mv  "table_trade_zs.sql" "table_trade_zs"${time}"pwz.sql"
mv  "table_trade_bj.sql" "table_trade_bj"${time}"pwz.sql"
