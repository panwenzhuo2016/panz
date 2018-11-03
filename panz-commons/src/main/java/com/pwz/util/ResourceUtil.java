package com.pwz.util;

import com.google.common.collect.Lists;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/23 22:57
 **/
public class ResourceUtil {
    public static Resource[] getResource(PathMatchingResourcePatternResolver resolver, String... args) throws IOException {
        List<Resource> a = Lists.newArrayList();
        List<Resource> all;
        Resource[] resources;
        for (int i = 0; i < args.length; i++) {
            resources = resolver.getResources(args[i]);
            all = Arrays.asList(resources);
            for (Resource resource : all) {
                a.add(resource);
            }
        }
        Resource[] result = new Resource[a.size()];
        for (int i = 0; i < a.size(); i++) {
            result[i] = a.get(i);
        }
        return result;
    }
}
