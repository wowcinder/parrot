package com.voole.parrot.shared.tree;

import org.hibernate.event.PreCollectionRecreateEvent;
import org.hibernate.event.PreCollectionRecreateEventListener;
import org.hibernate.event.PreCollectionRemoveEvent;
import org.hibernate.event.PreCollectionRemoveEventListener;
import org.hibernate.event.PreCollectionUpdateEvent;
import org.hibernate.event.PreCollectionUpdateEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;

import com.voole.parrot.shared.EntityWithOrderChildren;

public class TrunkPerInsert implements PreInsertEventListener,
		PreUpdateEventListener, PreCollectionUpdateEventListener,
		PreCollectionRecreateEventListener, PreCollectionRemoveEventListener {

	private static final long serialVersionUID = 3553140078673897082L;

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		if (event.getEntity() instanceof EntityWithOrderChildren) {
			sortChildren((EntityWithOrderChildren) event.getEntity());
		}
		return false;
	}

	public void sortChildren(EntityWithOrderChildren entity) {
		entity.sortChildren();
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		if (event.getEntity() instanceof EntityWithOrderChildren) {
			sortChildren((EntityWithOrderChildren) event.getEntity());
		}
		return false;
	}

	@Override
	public void onPreRemoveCollection(PreCollectionRemoveEvent event) {
		if (event.getAffectedOwnerOrNull() instanceof EntityWithOrderChildren) {
			sortChildren((EntityWithOrderChildren) event
					.getAffectedOwnerOrNull());
		}
	}

	@Override
	public void onPreRecreateCollection(PreCollectionRecreateEvent event) {
		if (event.getAffectedOwnerOrNull() instanceof EntityWithOrderChildren) {
			sortChildren((EntityWithOrderChildren) event
					.getAffectedOwnerOrNull());
		}

	}

	@Override
	public void onPreUpdateCollection(PreCollectionUpdateEvent event) {
		if (event.getAffectedOwnerOrNull() instanceof EntityWithOrderChildren) {
			sortChildren((EntityWithOrderChildren) event
					.getAffectedOwnerOrNull());
		}
	}

}