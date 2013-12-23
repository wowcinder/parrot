package com.voole.parrot.service.dao;

import java.io.Serializable;

public interface EntityUpdater<E extends Serializable> extends Serializable {
	public void invoke(E old, E e);
}
