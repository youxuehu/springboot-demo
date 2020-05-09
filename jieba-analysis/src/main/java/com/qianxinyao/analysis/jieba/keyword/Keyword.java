package com.qianxinyao.analysis.jieba.keyword;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Tom Qian
 * @email tomqianmaple@outlook.com
 * @github https://github.com/bluemapleman
 * @date Oct 20, 2018
 */
public class Keyword implements Comparable<Keyword>
{
	private BigDecimal tfidfvalue;
	private String name;
	/**
	 * @return the tfidfvalue
	 */
	public BigDecimal getTfidfvalue()
	{
		return tfidfvalue;
	}

	/**
	 * @param tfidfvalue the tfidfvalue to set
	 */
	public void setTfidfvalue(BigDecimal tfidfvalue)
	{
		this.tfidfvalue=tfidfvalue;
	}


	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}


	public Keyword(String name,BigDecimal tfidfvalue)
	{
		this.name=name;
		// tfidf值只保留3位小数
		BigDecimal divide = tfidfvalue.multiply(new BigDecimal(10000)).divide(new BigDecimal(10000));
		this.tfidfvalue=divide;
	}
	
	/**
	 * 为了在返回tdidf分析结果时，可以按照值的从大到小顺序返回，故实现Comparable接口
	 */
	@Override
	public int compareTo(Keyword o)
	{
		BigDecimal subtract = this.tfidfvalue.subtract(o.tfidfvalue);

		return subtract.equals(new BigDecimal(0))?-1:1;
	}

	/**
	 * 重写hashcode方法，计算方式与原生String的方法相同
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tfidfvalue.doubleValue());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Keyword other = (Keyword) obj;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
//		if (Double.doubleToLongBits(tfidfvalue) != Double.doubleToLongBits(other.tfidfvalue))
//			return false;
		return true;
	}
	
	
	
}
