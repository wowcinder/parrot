/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.importfrom.json;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.voole.parrot.importfrom.json.model.JsonAttachmentsCtypeColumn;
import com.voole.parrot.importfrom.json.model.JsonCtypeColumn;
import com.voole.parrot.importfrom.json.model.JsonSimpleCtypeColumn;

public class JsonColumnTypeAdapter extends TypeAdapter<JsonCtypeColumn> {

	public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
		@SuppressWarnings("unchecked")
		@Override
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			if (JsonCtypeColumn.class.isAssignableFrom(type.getRawType())) {
				return (TypeAdapter<T>) new JsonColumnTypeAdapter(gson);
			}
			return null;
		}
	};
	private final Gson gson;

	public JsonColumnTypeAdapter(Gson gson) {
		this.gson = gson;
	}

	@Override
	public void write(JsonWriter out, JsonCtypeColumn value) throws IOException {
	}

	@Override
	public JsonCtypeColumn read(JsonReader in) throws IOException {
		JsonToken token = in.peek();
		if (token.equals(JsonToken.BEGIN_OBJECT)) {
			return gson.getDelegateAdapter(FACTORY,
					TypeToken.get(JsonAttachmentsCtypeColumn.class)).read(in);
		} else if (token.equals(JsonToken.STRING)) {
			String str = gson.fromJson(in, String.class);
			JsonSimpleCtypeColumn column = new JsonSimpleCtypeColumn();
			column.setColumn(str);
			return column;
		}
		return null;
	}

}