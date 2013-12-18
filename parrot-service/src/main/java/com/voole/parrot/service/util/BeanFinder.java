package com.voole.parrot.service.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class BeanFinder implements BeanFactoryAware {

	private static BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory factory) throws BeansException {
		beanFactory = factory;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		if (null != beanFactory) {
			return (T) beanFactory.getBean(beanName);
		}
		return null;
	}

	public static <T> T getBean(Class<T> clazz) {
		if (null != beanFactory) {
			return (T) beanFactory.getBean(clazz);
		}
		return null;
	}

}
