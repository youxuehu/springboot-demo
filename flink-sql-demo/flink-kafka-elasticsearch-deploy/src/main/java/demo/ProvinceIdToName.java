package demo;

import org.apache.flink.table.functions.ScalarFunction;

/**
 * 根据id查询省份名称
 */
public class ProvinceIdToName extends ScalarFunction {
    static String[] provinces = new String[]{"北京", "上海", "杭州", "深圳", "江西", "重庆", "西藏"};
    public String eval(Integer provinceId) {
        System.err.println(String.format("[%d] to [%s]", provinceId, provinces[provinceId]));
        return provinces[provinceId];
    }
}