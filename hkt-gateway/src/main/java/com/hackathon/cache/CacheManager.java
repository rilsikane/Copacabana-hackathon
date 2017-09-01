package com.hackathon.cache;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.cache.annotation.Cacheable;

import java.util.HashMap;
import java.util.Map;



//import com.ibm.websphere.cache.DistributedMap;

public class CacheManager {

//	private static Cache<String, Object> cache;
	
//	private DistributedMap cacheObject = null;
	private static HashMap  cacheObject = null;
	
	static{
		cacheObject = new HashMap();
	}

	private static class CacheManagerHolder {
		public static final CacheManager INSTANCE = new CacheManager();
	}



	public void destroy() {
//		if (cache != null) {
//			cache.stop();
//			cache.destroy();
//			cache = null;
//		}
	}

//	public void start() {
//		try {
//			cache.create();
//			cache.start();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
//	private Cache<String, Object> init() {
//      try{
//    	  if (cache == null) {
//    		  System.out.println("cache null");
//              CacheFactory<String, Object> cacheFactory = new DefaultCacheFactory<String, Object>();
//               
//               boolean devMode = ConfigurationReader.getInstance().getConfiguration().getBoolean("env.devMode", true);
//               if(devMode){
//                   cache = cacheFactory.createCache(); 
//               } else {
//                   InputStream in = CacheManager.class.getResourceAsStream(CacheConstants.CACHE_CONFIG);
//                   try {
//                       cache = cacheFactory.createCache(in, false);
//                   } catch (ConfigurationException ee) {
//   					ee.printStackTrace();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//           
//                   IOUtils.closeQuietly(in);
//               }            
//          }
//      }catch(Exception ex){
//    	  ex.printStackTrace();
//      }
//        return cache;
//    }
	
	public void init() {
//		super.init();

		try {
				InitialContext ctx = new InitialContext();
//				cacheObject = (Map) ctx.lookup("cache/tokencache");
				cacheObject = new HashMap();
		} catch (NamingException e) {
				e.printStackTrace();
		} 
		
//		if(cacheObject==null){
//			cacheObject = new HashMap<String, Object>();
//		}
	}
	
	public void putCache(String key, Object value) {
		cacheObject .put(key, value);
	}

	@Cacheable(value="cacheManager")
	public Object getCache(String key) {
		return cacheObject .get(key);
	}
	
	public void  remove(String key) {
	cacheObject.remove(key);
}	
	

//	public void putCache(Fqn<String> fqn, String key, Object value) {
//		cache.put(fqn, key, value);
//	}
//
//	public Map<String, Object> getCacheData(Fqn<String> fqn) {
//		return cache.getData(fqn);
//	}
//
//	public Node<String, Object> getNode(Fqn<String> fqn) {
//		return cache.getNode(fqn);
//	}
//
//	public Object getCache(Fqn<String> fqn, String key) {
//		return cache.get(fqn, key);
//	}
//
//	public Object remove(Fqn<String> fqn, String key) {
//		return cache.remove(fqn, key);
//	}
//
//	public Cache<String, Object> getCache() {
//		return cache;
//	}
}
