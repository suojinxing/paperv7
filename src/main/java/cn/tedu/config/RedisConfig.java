package cn.tedu.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Setter
@Configuration
@PropertySource("classpath:/properties/redis.properties")
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
	/*
	 * redis集群配置
	 */
	private String[] nodes;

	// jedisCluster集合要多例的,但是参数set创建单例的交给spring管理比较好
	@Bean("clusterNodes")
	public Set<HostAndPort> getClusterNodes() {
		Set<HostAndPort> clusterNodes = new HashSet<>();
		for (String node : nodes) {
			String[] hostAndPort = node.split(":");
			String host = hostAndPort[0];
			int port = Integer.valueOf(hostAndPort[1]);
			clusterNodes.add(new HostAndPort(host, port));
		}
		return clusterNodes;
	}

	@Bean
	@Scope("prototype")
	public JedisCluster getJedis(@Qualifier("clusterNodes") Set<HostAndPort> clusterNodes) {
		JedisCluster jedisCluster = new JedisCluster(clusterNodes);
		return jedisCluster;
	}
}
