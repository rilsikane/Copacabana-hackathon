package com.hackathon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class ConfigurationReader {

	private static CompositeConfiguration configuration;

	public static class ConfigurationReaderHolder {
		private static final ConfigurationReader INSTANCE = new ConfigurationReader();
	}

	public static ConfigurationReader getInstance() {
		return ConfigurationReaderHolder.INSTANCE;
	}

	private ConfigurationReader() {
	}

	public void init(String configurationFile) throws Exception {
		configuration = new CompositeConfiguration();
		configuration.addConfiguration(new PropertiesConfiguration(configurationFile));
		this.initSystemConfiguration();
	}

	public void init(InputStream configurationIn) throws Exception {
		configuration = new CompositeConfiguration();
		PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
		propertiesConfiguration.load(configurationIn);
		configuration.addConfiguration(propertiesConfiguration);
		this.initSystemConfiguration();
	}

	private void initSystemConfiguration() {
		configuration.addConfiguration(new SystemConfiguration());
		SystemConfiguration systemConfiguration = new SystemConfiguration();
		Iterator<String> systemConfigurationKeys = systemConfiguration.getKeys();
		if (systemConfigurationKeys != null) {
			while (systemConfigurationKeys.hasNext()) {
				String key = systemConfigurationKeys.next();
				configuration.setProperty(key, systemConfiguration.getString(key));
			}
		}
	}

	public void reload() {
		clear();
	}

	public void clear() {
		if (configuration != null) {
			configuration.clear();
			configuration = null;
		}
	}

	public CompositeConfiguration getConfiguration() {
		return configuration;
	}

	public static class AppConfigurations {
		private static Properties properties = null;

		public static void initConfiguration(InputStream in, boolean closeStream) throws IOException {
			if (properties == null) {
				properties = new Properties();
			}
			try {
				properties.load(in);
			} finally {
				if (closeStream) {
					IOUtils.closeQuietly(in);
					ObjectUtils.destroyObject(in);
				}
			}
		}

		public static Properties getConfiguration() {
			return properties;
		}

		public static String getConfigurationDirectory() {
			String configurationDirectory = System.getProperty(GWSConstants.CONFIG_KEY.CONFIGURATION_DIR);
			if (StringUtils.isNotEmpty(configurationDirectory)) {
				return configurationDirectory;
			} else {
				return properties.getProperty(GWSConstants.CONFIG_KEY.CONFIGURATION_DIR);
			}
		}
	}
}
