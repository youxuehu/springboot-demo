FROM maven:3.5-jdk-8
ADD springboot-demo-0.0.1-SNAPSHOT.jar /springboot-demo-0.0.1-SNAPSHOT.jar
RUN wget https://mirror.bit.edu.cn/apache/zookeeper/zookeeper-3.6.2/apache-zookeeper-3.6.2-bin.tar.gz -O /usr/local/apache-zookeeper-3.6.2-bin.tar.gz && \
  tar -zxf /usr/local/apache-zookeeper-3.6.2-bin.tar.gz

# install conda
RUN wget https://repo.anaconda.com/miniconda/Miniconda3-latest-Linux-x86_64.sh
RUN bash Miniconda3-latest-Linux-x86_64.sh -b
# add miniconda to env path
ENV PATH="~/miniconda3/bin:${PATH}"
RUN . /root/.bashrc && \
    /opt/conda/bin/conda init bash && \
    conda activate mono && conda info --envs

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springboot-demo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

