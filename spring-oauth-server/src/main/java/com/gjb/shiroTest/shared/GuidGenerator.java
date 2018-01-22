package com.gjb.shiroTest.shared;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

import java.util.UUID;
/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:
 * @Company: ucfgroup.com
 * @Created 2018-01-15
 */


/**
 * 随机数生成处理工具
 *
 * @author Shengzhao Li
 */
public abstract class GuidGenerator {

    private static RandomValueStringGenerator defaultClientSecretGenerator = new RandomValueStringGenerator(32);

    /**
     * private constructor
     */
    private GuidGenerator() {
    }

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static String generateClientSecret() {
        return defaultClientSecretGenerator.generate();
    }
}
