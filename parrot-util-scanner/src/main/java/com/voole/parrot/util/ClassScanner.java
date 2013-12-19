/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

/**
 * @author XuehuiHe
 * @date 2013年12月19日
 */
public class ClassScanner {
	protected static final String RESOURCE_PATTERN = "/**/*.class";
	protected ResourcePatternResolver resourcePatternResolver;
	protected String[] scanPackages;
	protected List<Class<?>> clazzes;

	public ClassScanner(String... scanPackages) throws Exception {
		resourcePatternResolver = new PathMatchingResourcePatternResolver();
		this.scanPackages = scanPackages;
		this.clazzes = new ArrayList<Class<?>>();
		scanPackages();
	}

	protected void scanPackages() throws Exception {
		for (String pkg : scanPackages) {
			String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					+ ClassUtils.convertClassNameToResourcePath(pkg)
					+ RESOURCE_PATTERN;
			Resource[] resources = this.resourcePatternResolver
					.getResources(pattern);
			MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
					this.resourcePatternResolver);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					MetadataReader reader = readerFactory
							.getMetadataReader(resource);
					String className = reader.getClassMetadata().getClassName();
					Class<?> clazz = this.resourcePatternResolver
							.getClassLoader().loadClass(className);
					clazzes.add(clazz);
				}
			}
		}
	}

	public List<Class<?>> getClazzes() {
		return clazzes;
	}

	public void setClazzes(List<Class<?>> clazzes) {
		this.clazzes = clazzes;
	}

}
