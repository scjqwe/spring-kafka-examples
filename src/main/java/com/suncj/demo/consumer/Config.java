package com.suncj.demo.consumer;

/**
 * 消费者配置项 <br>
 * 作者：suncj<br>
 * 版本：1.0<br>
 * 创建日期：2018年3月21日<br>
 */
public class Config {
	public static final String BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers";

	public static final String GROUP_ID_CONFIG = "group.id";

	public static final String ENABLE_AUTO_COMMIT_CONFIG = "enable.auto.commit";

	public static final String AUTO_COMMIT_INTERVAL_MS_CONFIG = "auto.commit.interval.ms";

	public static final String SESSION_TIMEOUT_MS_CONFIG = "session.timeout.ms";

	public static final String KEY_DESERIALIZER_CLASS_CONFIG = "key.deserializer";

	public static final String VALUE_DESERIALIZER_CLASS_CONFIG = "value.deserializer";

	public static final String AUTO_OFFSET_RESET_CONFIG = "auto.offset.reset";

}
