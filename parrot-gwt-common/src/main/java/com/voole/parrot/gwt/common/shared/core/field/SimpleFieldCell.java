package com.voole.parrot.gwt.common.shared.core.field;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.sencha.gxt.cell.core.client.form.TriggerFieldCell;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;

public abstract class SimpleFieldCell<T> extends TriggerFieldCell<T> {
	private boolean expanded;
	private Window picker;

	public SimpleFieldCell() {
	}

	@Override
	protected void onBlur(com.google.gwt.cell.client.Cell.Context context,
			XElement parent, T value, NativeEvent event,
			ValueUpdater<T> valueUpdater) {
		super.onBlur(context, parent, value, event, valueUpdater);
	}

	@Override
	protected void onFocus(com.google.gwt.cell.client.Cell.Context context,
			XElement parent, T value, NativeEvent event,
			ValueUpdater<T> valueUpdater) {
		super.onFocus(context, parent, value, event, valueUpdater);
	}

	public void collapse(final Context context, final XElement parent) {
		if (!expanded) {
			return;
		}

		expanded = false;

		picker.hide();
		fireEvent(context, new CollapseEvent());
	}

	@Override
	protected void onTriggerClick(Context context, XElement parent,
			NativeEvent event, T value, ValueUpdater<T> updater) {
		super.onTriggerClick(context, parent, event, value, updater);
		if (!isReadOnly() && !isDisabled()) {
			if (GXT.isChrome() && lastParent != null && lastParent != parent) {
				getInputElement(lastParent).blur();
			}
			expand(context, parent, value, updater);
		}
	}

	protected void expand(com.google.gwt.cell.client.Cell.Context context,
			final XElement parent, T value, ValueUpdater<T> updater) {
		if (expanded) {
			return;
		}
		this.expanded = true;
		boolean focused = focusedCell != null;
		saveContext(context, parent, null, updater, value);
		if (!focused) {
			focusedCell = null;
		}
		final Window picker = getPicker();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				picker.show();
				picker.focus();
			}
		});

		fireEvent(context, new ExpandEvent());
	}

	protected Window getPicker() {
		if (picker == null) {
			picker = createPicker();
			picker.addHideHandler(new HideHandler() {
				@Override
				public void onHide(HideEvent event) {
					collapse(lastContext, lastParent);
				}
			});
		}
		return picker;
	}

	protected abstract Window createPicker();

	@Override
	public SimplePropertyEditor<T> getPropertyEditor() {
		return (SimplePropertyEditor<T>) super.getPropertyEditor();
	}

	protected boolean isFocusClick(XElement parent, XElement target) {
		boolean result = parent.isOrHasChild(target)
				|| (picker != null && (picker.getElement().isOrHasChild(target)));
		return result;
	}

	public boolean isExpanded() {
		return expanded;
	}

	@Override
	protected void onNavigationKey(Context context, Element parent, T value,
			NativeEvent event, ValueUpdater<T> valueUpdater) {
		if (event.getKeyCode() == KeyCodes.KEY_DOWN && !isExpanded()) {
			event.stopPropagation();
			event.preventDefault();
			onTriggerClick(context, parent.<XElement> cast(), event, value,
					valueUpdater);
		}
	}

	public void doTriggerValue(T t) {
		getPropertyEditor().setEntity(t);
		String s = getPropertyEditor().render(t);
		getInputElement(lastParent).setValue(s);
		getInputElement(lastParent).focus();
		triggerBlur(lastContext, lastParent, lastValue, lastValueUpdater);
	}
}