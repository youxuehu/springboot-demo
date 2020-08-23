#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
from pyspark import SparkConf, SparkContext
import sys
if __name__ == "__main__":
    if len(sys.argv) != 3:
        print "Usage: wordcount <file>"
        sys.exit(-1)
    sparkConf = SparkConf()\
        .setAppName('Python Spark WordCount')\
        .setMaster('local[2]')
    sc = SparkContext(conf=sparkConf)
    sc.setLogLevel('WARN')
    rdd = sc.textFile(sys.argv[1])
    word_count_rdd = rdd\
            .flatMap(lambda line: line.split(" "))\
                     .map(lambda word: (word, 1))\
                     .reduceByKey(lambda a, b: a + b)
    word_count_rdd.repartition(1).saveAsTextFile(sys.argv[2])
    sc.stop()
