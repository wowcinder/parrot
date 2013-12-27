/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.importfrom.ctype;

import com.google.gson.GsonBuilder;

/**
 * @author XuehuiHe
 * @date 2013年12月27日
 */
public class Test {
	public static void main(String[] args) {
		String jsonStr = "";
		jsonStr += "{";
		jsonStr += "    \"topic\": \"s_live_tracker_channel\",";
		jsonStr += "    \"data\": {";
		jsonStr += "      \"channelCount\": \"channelCount\",";
		jsonStr += "      \"attachments\": {";
		jsonStr += "        \"data\": {";
		jsonStr += "          \"channelUserNum\": \"channelUserNum\",";
		jsonStr += "          \"channelId\": \"channelId\"";
		jsonStr += "        },";
		jsonStr += "        \"orders\": [";
		jsonStr += "          \"channelId\",";
		jsonStr += "          \"channelUserNum\"";
		jsonStr += "        ],";
		jsonStr += "        \"table\": \"msg_live_tracker_channel_srv\"";
		jsonStr += "      },";
		jsonStr += "      \"trackerId\": \"trackerId\"";
		jsonStr += "    },";
		jsonStr += "    \"orders\": [";
		jsonStr += "      \"trackerId\",";
		jsonStr += "      \"channelCount\",";
		jsonStr += "      \"attachments\"";
		jsonStr += "    ],";
		jsonStr += "    \"table\": \"msg_live_tracker_channel\"";
		jsonStr += "  }";
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapterFactory(CtypeColumnTypeAdapter.FACTORY);
	}
}
