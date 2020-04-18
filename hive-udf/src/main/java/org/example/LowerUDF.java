package org.example;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class LowerUDF extends UDF{
    /**
     * 1. Implement one or more methods named "evaluate" which will be called by Hive.
     *
     * 2. "evaluate" should never be a void method. However it can return "null" if needed.
     */
    public Text evaluate(Text str){
        // input parameter validate
        if(null == str){
            return null ;
        }

        // validate
        if(StringUtils.isBlank(str.toString())){
            return null ;
        }

        // lower
        return new Text(str.toString().toLowerCase()) ;
    }

}
