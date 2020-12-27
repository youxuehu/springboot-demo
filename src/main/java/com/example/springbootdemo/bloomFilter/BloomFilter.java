package com.example.springbootdemo.bloomFilter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
* @author created by NianTianlei
* @createDate on 2018年6月24日 下午7:53:59
*/
public class BloomFilter implements Serializable {
	private static final long serialVersionUID = -1335864273732216980L;
	private final int[] seeds;
	private final int size;
	private final BitSet notebook;
	private final MisjudgmentRate rate;
	private final AtomicInteger useCount = new AtomicInteger(0);
	private final Double autoClearRate;

	private final static int DEFAULT_DATA_COUNT = 1000000;

	/**
	 * 默认中等程序的误判率：MisjudgmentRate.MIDDLE
	 * autoClearRate为null表示不自动清空数据，有利于提高性能
	 * @param dataCount
	 * 预期处理的数据规模，如预期用于处理1百万数据的查重，这里则填写1000000
	 */
	public BloomFilter(int dataCount) {
		this(MisjudgmentRate.MIDDLE, dataCount, null);
	}

	public BloomFilter() {
		this(DEFAULT_DATA_COUNT);
	}

	/**
	 * @param rate
	 *   一个枚举类型的误判率
	 * @param dataCount
	 *   预期处理的数据规模，拟实现百万级别
	 * @param autoClearRate
	 *   自动清空过滤器内部信息的使用比率，传null则表示不会自动清理，
	 *   当过滤器使用率达到100%时，则无论传入什么数据，都会认为在数据已经存在了
	 *   当希望过滤器使用率达到80%时自动清空重新使用，则传入0.8
	 */
	public BloomFilter(MisjudgmentRate rate, int dataCount, Double autoClearRate) {
		long bitSize = rate.seeds.length * dataCount;
		if (bitSize < 0 || bitSize > Integer.MAX_VALUE) {
			throw new RuntimeException("bisSize超界");
		}
		this.rate = rate;
		seeds = rate.seeds;
		size = (int) bitSize;
		notebook = new BitSet(size);
		this.autoClearRate = autoClearRate;
	}

	public void add(String data) {
		checkNeedClear();

		for (int i = 0; i < seeds.length; i++) {
			int index = hash(data, seeds[i]);
			setTrue(index);
		}
	}

	public boolean check(String data) {
		for (int i = 0; i < seeds.length; i++) {
			int index = hash(data, seeds[i]);
			if (!notebook.get(index)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 如果不存在就进行记录并返回false，如果存在了就返回true
	 * 
	 * @param data
	 * @return
	 */
	public boolean addIfNotExist(String data) {
		checkNeedClear();

		int[] indexs = new int[seeds.length];
		// 先假定存在
		boolean exist = true;
		int index;

		for (int i = 0; i < seeds.length; i++) {
			indexs[i] = index = hash(data, seeds[i]);

			if (exist) {
				if (!notebook.get(index)) {
					// 只要有一个不存在，就可以认为整个字符串都是第一次出现的
					exist = false;
					// 补充之前的信息
					for (int j = 0; j <= i; j++) {
						setTrue(indexs[j]);
					}
				}
			} else {
				setTrue(index);
			}
		}

		return exist;

	}

	private void checkNeedClear() {
		if (autoClearRate != null) {
			if (getUseRate() >= autoClearRate) {
				synchronized (this) {
					if (getUseRate() >= autoClearRate) {
						notebook.clear();
						useCount.set(0);
					}
				}
			}
		}
	}

	public void setTrue(int index) {
		useCount.incrementAndGet();
		notebook.set(index, true);
	}

	private int hash(String data, int seeds) {
		char[] value = data.toCharArray();
		int hash = 0;
		if (value.length > 0) {

			for (int i = 0; i < value.length; i++) {
				hash = i * hash + value[i];
			}
		}

		hash = hash * seeds % size;
		// 防止溢出变成负数
		return Math.abs(hash);
	}

	public double getUseRate() {
		return (double) useCount.intValue() / (double) size;
	}

	public void saveFilterToFile(String path) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static BloomFilter readFilterFromFile(String path) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			return (BloomFilter) ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 清空bl中的记录信息
	 */
	public void clear() {
		useCount.set(0);
		notebook.clear();
	}

	public MisjudgmentRate getRate() {
		return rate;
	}

	/**
	 * 分配的位数越多，误判率越低但是越占内存 
	 * 4个位误判率大概是0.14689159766308 
	 * 8个位误判率大概是0.02157714146322
	 * 16个位误判率大概是0.00046557303372 
	 * 32个位误判率大概是0.00000021167340
	 * 
	 * @author Niantianlei 2018年6月24日
	 */
	public enum MisjudgmentRate {
		// 这里要选取质数，能很好的降低错误率
		/**
		 * 每个字符串分配4个位
		 */
		VERY_SMALL(new int[] { 2, 3, 5, 7 }),
		/**
		 * 每个字符串分配8个位
		 */
		SMALL(new int[] { 2, 3, 5, 7, 11, 13, 17, 19 }),
		/**
		 * 每个字符串分配16个位
		 */
		MIDDLE(new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53 }),
		/**
		 * 每个字符串分配32个位
		 */
		HIGH(new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131 });

		private int[] seeds;

		private MisjudgmentRate(int[] seeds) {
			this.seeds = seeds;
		}

		public int[] getSeeds() {
			return seeds;
		}

		public void setSeeds(int[] seeds) {
			this.seeds = seeds;
		}

	}

	public static void main(String[] args) {
		BloomFilter fileter = new BloomFilter();
		System.out.println(fileter.addIfNotExist("abc13512345678"));
		System.out.println(fileter.check("abc13512345678"));
		System.out.println(fileter.addIfNotExist("c13512345678"));
		System.out.println(fileter.addIfNotExist("a13512345678"));
		fileter.saveFilterToFile("E:\\logs\\blFile.obj");
		fileter = readFilterFromFile("E:\\logs\\blFile.obj");
		System.out.println(fileter.getUseRate());
		System.out.println(fileter.addIfNotExist("1111111111111"));
	}
}