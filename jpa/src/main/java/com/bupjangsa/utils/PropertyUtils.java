package com.bupjangsa.utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;


public class PropertyUtils {
    private static Configuration configuration;
    private static final String defaultPropertyPath = "properties/jpa-config.properties";

    static {
        configuration = PropertyUtils.getConfiguration(defaultPropertyPath);
    }

    public static String getString(String str) {
        return configuration.getString(str);
    }

    private static Configuration getConfiguration(String path) {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder builder = (new FileBasedConfigurationBuilder(PropertiesConfiguration.class))
                .configure(params.properties().setFileName(path));

        try {
            return (Configuration) builder.getConfiguration();
        } catch (ConfigurationException var7) {
            return null;
        }
    }

    /**
     * 프로퍼티 파일 정보 추가 로드.
     *
     * @param path 프로퍼티 경로
     */
    public static void add(String path) {

        Configuration addConfiguration = PropertyUtils.getConfiguration(path);
        Iterator<String> iterator = addConfiguration.getKeys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            configuration.setProperty(key, addConfiguration.getProperty(key));

        }

    }

    /**
     * <pre>
     * 기능 명 : Integer Type Property value 조회.
     * 기능 용도 : Integer Type의 Property valu를 조회하는 기능
     * </pre>
     *
     * @param str 조회할 값에 대한 key
     * @return 해당 값. 값이 없는 경우 0 리턴
     */
    public static int getInt(String str) {
        if(StringUtils.isNotBlank(configuration.getString(str))) {
            return configuration.getInt(str);
        }
        return 0;
    }

}
