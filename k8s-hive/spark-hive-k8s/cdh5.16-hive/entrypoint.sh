#!/bin/bash

echo "Injecting IP address in the config. POD_IP: ${POD_IP}"

sed -i'.bak' "s|hadoop-master\.hadoop|${POD_IP}|g" /etc/hadoop/conf/hdfs-site.xml
rm /etc/hadoop/conf/hdfs-site.xml.bak

sed -i'.bak' "s|hadoop-master\.hadoop|${POD_IP}|g" /etc/hadoop/conf/core-site.xml
rm /etc/hadoop/conf/core-site.xml.bak

sed -i'.bak' "s|hadoop-master\.hadoop|${POD_IP}|g" /etc/hadoop/conf/mapred-site.xml
rm /etc/hadoop/conf/mapred-site.xml.bak

sed -i'.bak' "s|hadoop-master\.hadoop|${POD_IP}|g" /etc/hadoop/conf/yarn-site.xml
rm /etc/hadoop/conf/yarn-site.xml.bak

echo "Setting hive.server2.enable.doAs to '${HIVE_DO_AS}'"
sed -i'.bak' "s|HIVE_DO_AS|${HIVE_DO_AS}|g" /etc/hive/conf/hive-site.xml
rm /etc/hive/conf/hive-site.xml.bak

supervisord -c /etc/supervisord.conf
