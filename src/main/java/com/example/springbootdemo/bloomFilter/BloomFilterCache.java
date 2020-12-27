package com.example.springbootdemo.bloomFilter;

/**
 * @author created by NianTianlei
 * @createDate on 2018年6月24日 下午7:56:49
 */
public class BloomFilterCache {
	public static BloomFilter bloomFilter;

	public static void main(String[] args) {
		BloomFilter bloomFilter = new BloomFilter();
		Long mobilePhone = 13000000000L;
		for (int i = 0; i <= 1000000000; i++) {
			bloomFilter.add(mobilePhone + "");
			System.out.println(mobilePhone);
			mobilePhone += 1;
		}
	}
}
