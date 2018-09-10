package pri.zjj.blog.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class ShiroConfig {
	private final Logger logger=LoggerFactory.getLogger(ShiroConfig.class);
	
	@Bean
	public SecurityManager securityManager(@Qualifier("redisCache") CacheManager redisCacheManager) {
		DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
		defaultWebSecurityManager.setCacheManager(new MemoryConstrainedCacheManager());
		defaultWebSecurityManager.setRealm(null);
		return defaultWebSecurityManager;
	}
	
	/**
	 * 凭证匹配器
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("SHA256");
		hashedCredentialsMatcher.setHashIterations(2);
		return hashedCredentialsMatcher;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器Map
		Map<String,String> filterChainDefinitionMap=new LinkedHashMap<String,String>();
		//配置不会被拦截的URL
		filterChainDefinitionMap.put("/static/**", "ano");
		//配置退出URL
		filterChainDefinitionMap.put("/logout", "logout");
		
		filterChainDefinitionMap.put("/**", "auth");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/loginSuccess");
		shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
		
		
		return shiroFilterFactoryBean;
	}
	
}
