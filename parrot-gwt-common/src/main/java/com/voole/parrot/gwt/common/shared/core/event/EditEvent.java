package com.voole.parrot.gwt.common.shared.core.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.voole.parrot.gwt.common.shared.GwtCallBack;
import com.voole.parrot.gwt.common.shared.core.event.EditEvent.EditHanlder;

public class EditEvent<T> extends GwtEvent<EditHanlder<T>> {
	private static Type<EditHanlder<?>> TYPE;

	public static Type<EditHanlder<?>> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<EditHanlder<?>>());
	}

	public interface EditHanlder<T> extends EventHandler {
		void onEdit(EditEvent<T> event);
	}

	private final T t;
	private final boolean isUpdate;
	private final GwtCallBack<T> callback;

	public EditEvent(T t, GwtCallBack<T> callback) {
		this(t, callback, false);
	}

	public EditEvent(T t, GwtCallBack<T> callback, boolean isUpdate) {
		this.t = t;
		this.isUpdate = isUpdate;
		this.callback = callback;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditHanlder<T>> getAssociatedType() {
		return (Type) getType();
	}

	@Override
	protected void dispatch(EditHanlder<T> handler) {
		handler.onEdit(this);
	}

	public T getTarget() {
		return t;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public GwtCallBack<T> getCallback() {
		return callback;
	}

}
