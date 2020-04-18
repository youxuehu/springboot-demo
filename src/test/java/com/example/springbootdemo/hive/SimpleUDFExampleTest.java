package com.example.springbootdemo.hive;

import com.example.springbootdemo.common.hive.udf.SimpleUDFExample;
import org.apache.hadoop.io.Text;
import org.junit.Assert;
import org.junit.Test;

public class SimpleUDFExampleTest {

    @Test
    public void testUDF() {
        SimpleUDFExample example = new SimpleUDFExample();
        Assert.assertEquals("Hello world", example.evaluate(new Text("world")).toString());
    }
}
