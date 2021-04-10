FROM centos:6

RUN \
    set -xeu && \
    yum install -y \
        java-1.8.0-openjdk-devel \
        nc \
        wget \
        && \
    \
    # install supervisor
    yum install -y setuptools epel-release && \
    yum install -y python-pip && \
    pip install --upgrade pip==9.0.3 && \
    pip install --upgrade setuptools==36.8.0 && \
    pip install supervisor==3.4.0 && \
    \
    # install commonly needed packages
    yum install -y \
        net-tools `# netstat` \
        sudo \
        telnet \
        vim \
        && \
    # cleanup
    yum -y clean all && rm -rf /tmp/* /var/tmp/*

ENV PATH="/usr/local/bin:${PATH}"
ENV JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk.x86_64
ENV LANG=en_US.UTF-8

# Setup CDH repo, pin the CDH distribution to a concrete version
RUN wget -nv https://archive.cloudera.com/cdh5/redhat/6/x86_64/cdh/cloudera-cdh5.repo -P /etc/yum.repos.d \
  && rpm --import https://archive.cloudera.com/cdh5/redhat/6/x86_64/cdh/RPM-GPG-KEY-cloudera \
  && sed -i '/^baseurl=/c\baseurl=https://archive.cloudera.com/cdh5/redhat/6/x86_64/cdh/5.16.2/' /etc/yum.repos.d/cloudera-cdh5.repo

# Install Hadoop, Hive (w/ MySQL)
RUN \
    set -xeu && \
    yum install -y \
    hadoop-hdfs-namenode \
    hadoop-hdfs-secondarynamenode \
    hadoop-hdfs-datanode \
    \
    hadoop-mapreduce \
    \
    hadoop-yarn-resourcemanager \
    hadoop-yarn-nodemanager \
    \
    hadoop-client \
    hadoop-conf-pseudo \
    \
    hive \
    hive-metastore \
    hive-server2 \
    \
    mysql-server mysql-connector-java \
    \
  # Cleanup
  && yum -y clean all && rm -rf /tmp/* /var/tmp/* \
  && ln -s /usr/share/java/mysql-connector-java.jar /usr/lib/hive/lib/mysql-connector-java.jar

# Copy configuration files
COPY ./files /

# Run setup script
RUN /root/setup.sh \
  && rm -rf /tmp/* /var/tmp/*

# Setup sock proxy
RUN yum install -y openssh openssh-clients openssh-server && yum -y clean all
RUN ssh-keygen -t rsa -b 4096 -C "automation@teradata.com" -N "" -f /root/.ssh/id_rsa \
  && ssh-keygen -t rsa -b 4096 -N "" -f /etc/ssh/ssh_host_rsa_key \
  && ssh-keygen -t dsa -b 1024 -N "" -f /etc/ssh/ssh_host_dsa_key \
  && cp /root/.ssh/id_rsa.pub /root/.ssh/authorized_keys
RUN chmod 755 /root && chmod 700 /root/.ssh
RUN passwd --unlock root

# HDFS ports
EXPOSE 1004 1006 8020 9000 50010 50020 50070 50075 50470

# YARN ports
EXPOSE 8030 8031 8032 8033 8040 8041 8042 8088 10020 19888

# HIVE port
EXPOSE 9083 10000

# SOCKS port
EXPOSE 1180

COPY entrypoint.sh /entrypoint.sh
