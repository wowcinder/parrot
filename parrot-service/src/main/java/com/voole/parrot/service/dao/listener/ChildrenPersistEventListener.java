package com.voole.parrot.service.dao.listener;

import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.event.PersistEvent;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.event.def.DefaultPersistEventListener;
import org.springframework.stereotype.Component;

import com.voole.parrot.shared.entity.EntityWithOrderChildren;

@Component("childrenPersistEventListener")
public class ChildrenPersistEventListener extends DefaultPersistEventListener
		implements PreUpdateEventListener {

	private static final long serialVersionUID = -2857656969946586046L;

	@SuppressWarnings("rawtypes")
	@Override
	public void onPersist(PersistEvent event, Map createdAlready)
			throws HibernateException {
		Object t = event.getObject();
		if (t instanceof EntityWithOrderChildren) {
			((EntityWithOrderChildren) t).sortChildren();
		}
		super.onPersist(event, createdAlready);
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		Object t = event.getEntity();
		if (t instanceof EntityWithOrderChildren) {
			((EntityWithOrderChildren) t).sortChildren();
		}
		return false;
	}

}
