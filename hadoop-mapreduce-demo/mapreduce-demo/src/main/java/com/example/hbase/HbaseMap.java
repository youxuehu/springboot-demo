package com.example.hbase;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;
import java.util.List;
/**
 *  * 继承的是hbase中提供的TableMapper类，其实这个类也是继承的MapReduce类。 后边跟的两个泛型参数指定类型是mapper输
 *  * 出的数据类型，该类型必须继承自Writable类， 例如可能用到的put和delete就可以。 需要注意的是要和initTableMapperJob
 *  * 方法指定的数据类型一直。 该过程会自动从指定hbase表内一行一行读取数据进行处理。
 *  *
 */
public class HbaseMap  extends TableMapper<Text, IntWritable> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context)
            throws IOException, InterruptedException {
        System.out.println(new String(key.get())+"---->"+value.size());
        List<Cell> cells = value.listCells();
        for (Cell cell : cells) {
            System.out.print("RowName:"+new String(CellUtil.cloneRow(cell)));
            System.out.print("--->Timetamp:"+cell.getTimestamp());
            System.out.print("--->column Family:"+new String(CellUtil.cloneFamily(cell)));
            System.out.print("---->cell Name:"+new String(CellUtil.cloneQualifier(cell)));
            System.out.println("---->cell value:"+new String(CellUtil.cloneValue(cell)));
        }
        context.write(new Text("aa"),new IntWritable(1));
    }
}