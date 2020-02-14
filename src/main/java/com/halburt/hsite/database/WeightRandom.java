package com.halburt.hsite.database;
 
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
 
 /**
  * 随机权重访问
  * 
  * 
  * 　如有4个元素A、B、C、D，权重分别为1、2、3、4，随机结果中A:B:C:D的比例要为1:2:3:4。

　　总体思路：累加每个元素的权重A(1)-B(3)-C(6)-D(10)，
则4个元素的的权重管辖区间分别为[0,1)、[1,3)、[3,6)、[6,10)。然后随机出一个[0,10)之间的随机数。落在哪个区间，则该区间之后的元素即为按权重命中的元素。

　　实现方法：

利用TreeMap，则构造出的一个树为:
　　　　B(3)
　　　 /      \
  /         \
  A(1)     D(10)
             /
            /
         C(6)

然后，利用treemap.tailMap().firstKey()即可找到目标元素。

当然，也可以利用数组+二分查找来实现。
  * @author Administrator
  *
  * @param <K>
  * @param <V>
  */
public class WeightRandom  {
 
//	private String randomKey ;//随机方式 。后期可以根据randomKey设置不同的随机算法
	
	private TreeMap<Double, WeightNumber> readWeightMap = new TreeMap<Double, WeightNumber>();
	
	private TreeMap<Double, WeightNumber> wirteWeightMap = new TreeMap<Double, WeightNumber>();

    private static final Logger logger = LoggerFactory.getLogger(WeightRandom.class);
    
    public WeightRandom(List<WeightNumber> list) {
        Preconditions.checkNotNull(list, "list can NOT be null!");
        for (WeightNumber pair : list) {
        	if(pair.getFlag().contains( DynamicDataSourceGlobal.READ.name())){
                double lastWeight = this.readWeightMap.size() == 0 ? 0 : this.readWeightMap.lastKey().doubleValue();//统一转为double
                this.readWeightMap.put(pair.getWeight().doubleValue() + lastWeight, pair );//权重累加
        	}
        	if(pair.getFlag().contains( DynamicDataSourceGlobal.WRITE.name())){
                double lastWeight = this.wirteWeightMap.size() == 0 ? 0 : this.wirteWeightMap.lastKey().doubleValue();//统一转为double
                this.wirteWeightMap.put(pair.getWeight().doubleValue() + lastWeight, pair );//权重累加
        	}
        	
        }
    }
    
    /**
     * 随机出读数据源
     * @return
     */
    public String  randomRead() {
        double randomWeight = this.readWeightMap.lastKey() * ThreadLocalRandom.current() .nextDouble(); 
        SortedMap<Double, WeightNumber> tailMap = this.readWeightMap.tailMap(randomWeight, false);
        return this.readWeightMap.get(tailMap.firstKey()).getKey();
    }
    /**
     * 随机出写数据源
     * @return
     */
    public String randomWrite() {
        double randomWeight = this.wirteWeightMap.lastKey()* ThreadLocalRandom.current() .nextDouble(); 
        SortedMap<Double, WeightNumber> tailMap = this.wirteWeightMap.tailMap(randomWeight, false);
        return this.wirteWeightMap.get(tailMap.firstKey()).getKey();
    }

//	public String getRandomKey() {
//		return randomKey;
//	}
//
//	public void setRandomKey(String randomKey) {
//		this.randomKey = randomKey;
//	}
    
    
}
