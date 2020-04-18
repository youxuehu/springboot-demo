package com.example.springbootdemo.common.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class SimpleUDFExample extends UDF {

    public Text evaluate(Text input) {
        return new Text("Hello " + input.toString());
    }
}
