package com.example.springbootdemo.handler.job;

import com.alibaba.fastjson.annotation.JSONType;

import javax.xml.bind.annotation.XmlSeeAlso;

//@XmlClassExtractor(JobClassExtractor.class)
@XmlSeeAlso({SqlJob.class, StopJob.class})
@JSONType(seeAlso = {
        OdpsJob.class,
        YarnJob.class,
        SqlJob.class,
        StopJob.class,
        ShellJob.class,
        PythonJob.class,
        LaunchContrainerJob.class,
        KubemakerJob.class
})
public interface Job {
}
