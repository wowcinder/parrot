package com.voole.parrot.gwt.ui.shared.ctypelogmodel.field;

import com.voole.parrot.gwt.common.shared.core.field.SimplePropertyEditor;
import com.voole.parrot.gwt.common.shared.core.field.SimpleTriggerField;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class HbaseTableVersionField extends
		SimpleTriggerField<HbaseTableVersion> {

	public HbaseTableVersionField() {
		super(new HbaseTableVersionFieldCell(),
				new SimplePropertyEditor<HbaseTableVersion>() {
					@Override
					public String render(HbaseTableVersion version) {
						if (version != null) {
							String table = version.getTable().getName();
							return "table:" + table + ",version:"
									+ version.getVersion();
						}
						return null;
					}
				});
	}

}
