/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.importfrom.ctype;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class CtypeColumnTypeAdapter extends TypeAdapter<CtypeColumn> {

	public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
		@SuppressWarnings("unchecked")
		@Override
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			if (CtypeColumn.class.isAssignableFrom(type.getRawType())) {
				return (TypeAdapter<T>) new CtypeColumnTypeAdapter(gson);
			}
			return null;
		}
	};
	private final Gson gson;

	public CtypeColumnTypeAdapter(Gson gson) {
		this.gson = gson;
	}

	@Override
	public void write(JsonWriter out, CtypeColumn value) throws IOException {
	}

	@Override
	public CtypeColumn read(JsonReader in) throws IOException {
		JsonToken token = in.peek();
		if (token.equals(JsonToken.BEGIN_OBJECT)) {
			return gson.getDelegateAdapter(FACTORY,
					TypeToken.get(AttachmentsCtypeColumn.class)).read(in);
		} else if (token.equals(JsonToken.STRING)) {
			String str = gson.fromJson(in, String.class);
			SimpleCtypeColumn column = new SimpleCtypeColumn();
			column.setColumn(str);
			return column;
		}
		return null;
	}

}