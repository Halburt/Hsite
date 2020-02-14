package com.halburt.hsite.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

 /**
  * Desc: 动态数据源实现读写分离
  * @author Administrator
  *
  */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private WeightRandom dataSourceRandom;//加权随机数据源
    @Override
    public void afterPropertiesSet() {
        if (this.dataSourceRandom == null) {
           throw new IllegalArgumentException("Property 'dataSourceRandom' is required");
        }
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {

    	DynamicDataSourceGlobal  dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();
    	
        if(dynamicDataSourceGlobal == null   ||  dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
            return getWriteDataSourceKey();
        }
        return getReadDataSourceKey();
    }
	//获取随机写数据源
	public Object getWriteDataSourceKey(){
		Object obj = dataSourceRandom.randomWrite();
//    	logger.warn(  "WRITE:"+obj);
		return obj;
	}
	//获取随机读数据源
	public Object getReadDataSourceKey(){ 
		Object obj = dataSourceRandom.randomRead();
//		logger.warn("read:"+obj);
		return obj;
	}

	public WeightRandom getDataSourceRandom() {
		return dataSourceRandom;
	}

	public void setDataSourceRandom(WeightRandom dataSourceRandom) {
		this.dataSourceRandom = dataSourceRandom;
	}
	 
 
	
}
 