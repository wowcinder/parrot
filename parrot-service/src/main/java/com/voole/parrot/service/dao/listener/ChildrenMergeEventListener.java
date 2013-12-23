/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.listener;

import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.event.MergeEvent;
import org.hibernate.event.def.DefaultMergeEventListener;
import org.springframework.stereotype.Component;

import com.voole.parrot.shared.entity.EntityWithOrderChildren;

/**
 * @author XuehuiHe
 * @date 2013年12月23日
 */
@Component("childrenMergeEventListener")
public class ChildrenMergeEventListener extends DefaultMergeEventListener {

	private static final long serialVersionUID = -1578827158661865240L;

	@Override
	public void onMerge(MergeEvent event,
			@SuppressWarnings("rawtypes") Map copiedAlready)
			throws HibernateException {
		Object t = event.getOriginal();
		if (t instanceof EntityWithOrderChildren) {
			((EntityWithOrderChildren) t).sortChildren();
		}
		super.onMerge(event, copiedAlready);
	}

}
