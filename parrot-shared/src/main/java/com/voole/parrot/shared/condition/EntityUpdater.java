package com.voole.parrot.shared.condition;

import java.io.Serializable;

public abstract class EntityUpdater<E extends Serializable> {
	public abstract void invoke(E old, E e);
}
