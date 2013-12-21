/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.menu.editor;

import java.text.ParseException;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.sencha.gxt.cell.core.client.form.TriggerFieldCell;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.TriggerField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.voole.parrot.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class AuthorityField extends TriggerField<Authority> {

	public AuthorityField() {
		super(new AuthorityFieldCell(), new AuthorityPropertyEditor());
		setEditable(false);
		redraw();
	}

	@Override
	public AuthorityFieldCell getCell() {
		return (AuthorityFieldCell) super.getCell();
	}

	@Override
	public AuthorityPropertyEditor getPropertyEditor() {
		return (AuthorityPropertyEditor) super.getPropertyEditor();
	}

	public static class AuthorityPropertyEditor extends
			PropertyEditor<Authority> {
		private Authority authority;

		public void setAuthority(Authority authority) {
			this.authority = authority;
		}

		public Authority getAuthority() {
			return authority;
		}

		@Override
		public String render(Authority object) {
			if (object != null) {
				return object.getName();
			}
			return null;
		}

		@Override
		public Authority parse(CharSequence text) throws ParseException {
			if (authority != null) {
				return authority;
			}
			return null;
		}

	}

	public static class AuthorityFieldCell extends TriggerFieldCell<Authority> {

		private boolean expanded;
		private AuthorityPicker picker;

		public AuthorityFieldCell() {

		}

		@Override
		protected void onBlur(com.google.gwt.cell.client.Cell.Context context,
				XElement parent, Authority value, NativeEvent event,
				ValueUpdater<Authority> valueUpdater) {
			System.out.println("------------失去焦点-----------");
			super.onBlur(context, parent, value, event, valueUpdater);
		}

		@Override
		protected void onFocus(com.google.gwt.cell.client.Cell.Context context,
				XElement parent, Authority value, NativeEvent event,
				ValueUpdater<Authority> valueUpdater) {
			System.out.println("------------获得焦点-----------");
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
				NativeEvent event, Authority value,
				ValueUpdater<Authority> updater) {
			super.onTriggerClick(context, parent, event, value, updater);
			if (!isReadOnly() && !isDisabled()) {
				if (GXT.isChrome() && lastParent != null
						&& lastParent != parent) {
					getInputElement(lastParent).blur();
				}
				expand(context, parent, value, updater);
			}
		}

		private void expand(com.google.gwt.cell.client.Cell.Context context,
				final XElement parent, Authority value,
				ValueUpdater<Authority> updater) {
			if (expanded) {
				return;
			}
			this.expanded = true;
			boolean focused = focusedCell != null;
			saveContext(context, parent, null, updater, value);
			if (!focused) {
				focusedCell = null;
			}
			final AuthorityPicker picker = getPicker();

			// handle case when down arrow is opening menu
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {

				@Override
				public void execute() {
					picker.show();
					picker.focus();
				}
			});

			fireEvent(context, new ExpandEvent());
		}

		@Override
		public AuthorityPropertyEditor getPropertyEditor() {
			return (AuthorityPropertyEditor) super.getPropertyEditor();
		}

		protected boolean isFocusClick(XElement parent, XElement target) {
			boolean result = parent.isOrHasChild(target)
					|| (picker != null && (picker.getElement()
							.isOrHasChild(target)));
			System.out.println("----------------focus click result:" + result
					+ "---------------");
			return result;
		}

		public boolean isExpanded() {
			return expanded;
		}

		@Override
		protected void onNavigationKey(Context context, Element parent,
				Authority value, NativeEvent event,
				ValueUpdater<Authority> valueUpdater) {
			if (event.getKeyCode() == KeyCodes.KEY_DOWN && !isExpanded()) {
				event.stopPropagation();
				event.preventDefault();
				onTriggerClick(context, parent.<XElement> cast(), event, value,
						valueUpdater);
			}
		}

		public void doTriggerBlur2() {
			triggerBlur(lastContext, lastParent, lastValue, lastValueUpdater);
		}

		private AuthorityPicker getPicker() {
			if (picker == null) {
				picker = new AuthorityPicker();
				final Grid<Authority> grid = picker.getGrid();
				final TextButton clearBt = picker.getCleanBt();
				grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
					@Override
					public void onCellClick(CellDoubleClickEvent event) {
						Authority authority = grid.getStore().get(
								event.getRowIndex());
						getPropertyEditor().setAuthority(authority);
						String s = getPropertyEditor().render(authority);
						getInputElement(lastParent).setValue(s);
						getInputElement(lastParent).focus();
						doTriggerBlur2();
						picker.hide();
					}
				});

				picker.addHideHandler(new HideHandler() {
					@Override
					public void onHide(HideEvent event) {
						collapse(lastContext, lastParent);
					}
				});

				clearBt.addSelectHandler(new SelectHandler() {
					@Override
					public void onSelect(SelectEvent event) {
						Authority authority = null;
						getPropertyEditor().setAuthority(authority);
						String s = getPropertyEditor().render(authority);
						getInputElement(lastParent).setValue(s);
						getInputElement(lastParent).focus();
						doTriggerBlur2();
						picker.hide();
					}
				});
			}

			return picker;
		}
	}

}
