package com.voole.parrot.log.batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voole.parrot.log.transformer.CtypeLogTransformer;
import com.voole.parrot.log.transformer.JsonLogTransformer;
import com.voole.parrot.log.transformer.LogTransformer;
import com.voole.parrot.log.transformer.LogTransformerManager;
import com.voole.parrot.log.transformer.exception.LogTransformException;
import com.voole.parrot.log.transformer.exception.LogTransformInitException;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;
import com.voole.parrot.shared.hbasequery.HbaseLogRecord;
import com.voole.parrot.shared.hbasequery.HbaseLogRecordCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-test.xml")
public class LogModelBatchServiceTest {
	@Autowired
	private LogModelBatchService service;

	private LogTransformerManager transformerManager;
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public LogModelBatchServiceTest() {
		transformerManager = new LogTransformerManager();
	}

	@Test
	public void test() throws LogTransformInitException, LogTransformException,
			ParseException {
		LogModelVersion version = service.get("msg_apache_log", "0.0");
		LogTransformer logTransformer = new CtypeLogTransformer(version);
		String raw = "1388502526#10.1.100.116	10.1.100.116	2013-05-22 14:21:47	POST	/cms_apk_temp/getDesktop	?url=cms_apk_temp/getDesktop	200	101	http://platform.voole.com/cms_desktop_section?act=cms_desktop_section&menuid=309	Mozilla/5.0 (Windows NT 5.1; rv:22.0) Gecko/20100101 Firefox/22.0	platform.voole.com	66080";
		HbaseLogRecordCollection collection = logTransformer.transform(raw);
		for (Entry<String, List<HbaseLogRecord>> entry : collection.entrySet()) {
			Assert.assertEquals("msg_apache_log", entry.getKey());
			Assert.assertEquals(1, entry.getValue().size());
			HbaseLogRecord record = entry.getValue().get(0);
			Map<String, Object> data = record.getData();
			Assert.assertEquals(66080l, data.get("used_time"));
			Assert.assertEquals(sf.parse("2013-05-22 14:21:47"),
					data.get("request_time"));
			Assert.assertEquals(new Date(1388502526l * 1000), data.get("stamp"));
			Assert.assertEquals("/cms_apk_temp/getDesktop",
					data.get("request_uri"));
			Assert.assertEquals("?url=cms_apk_temp/getDesktop",
					data.get("url_query_string"));
			Assert.assertEquals("0.0", data.get("version"));
			Assert.assertEquals("10.1.100.116", data.get("user_ip"));
			Assert.assertEquals("POST", data.get("request_method"));
			Assert.assertEquals("10.1.100.116", data.get("server_ip"));
			Assert.assertEquals(101, data.get("response_len"));
			Assert.assertEquals(200, data.get("http_code"));
			Assert.assertEquals(
					"Mozilla/5.0 (Windows NT 5.1; rv:22.0) Gecko/20100101 Firefox/22.0",
					data.get("user_agent"));
			Assert.assertEquals("platform.voole.com", data.get("request_host"));
			Assert.assertEquals(
					"http://platform.voole.com/cms_desktop_section?act=cms_desktop_section&menuid=309",
					data.get("referer"));
		}
	}

	@Test
	public void test2() throws LogTransformInitException, LogTransformException {
		LogModelVersion version = service.get("msg_v3a_play_auth2", "0.0");
		LogTransformer logTransformer = new JsonLogTransformer(version);
		String json2 = "{\"reqno\":\"355375_1368517023431\",\"status\":\"-13\",\"resultdesc\":\"用户超出有效期\",\"play_url\":\"http://172.16.10.139:80/trends_index.jsp?key=355375_e8d3898e7ad7a22f9b96b484d1532e5e\",\"delaydeduct\":\"0\",\"delaytime\":\"180\",\"pid\":\"340009\",\"playtime\":{\"time\":\"0\",\"stime\":\"0\",\"etime\":\"300\"},\"adinfo\":{\"txtlist\":[{\"txtid\":[],\"txt\":\"优朋广告 优朋广告 优朋广告\",\"inserttime\":\"-1\",\"length\":\"20\"}],\"piclist\":[{\"pic\":\"http://imgadmin.voole.com/ishow/201108021635261001z211f\",\"length\":\"15\"}],\"videolist\":[{\"video\":\"31ed101dc6ecb5f1f9356e66d17794bf\",\"txt\":\"现在拿起遥控器按777有惊喜哦0\",\"inserttime\":\"0\",\"length\":\"15\"},{\"video\":\"31ed101dc6ecb5f1f9356e66d17794bf\",\"txt\":\"现在拿起遥控器按777有惊喜哦1\",\"inserttime\":\"0\",\"length\":\"15\"}]}}";
		String json = "1388502526#{\"serverip\":\"125.125.125.125\",\"startTime\":\"111\",\"endTime\":\"222\",\"request\":\"action=playauth2&oemid=153&uid=100405038&hid=bc83a7ec31be0000000000000000000000000000&mid=24534761&sid=1&fid=34a1a6a693d3b97f2987274890deb5bd&pid=101005&playtype=0&ext=&ip=110.98.184.248&format=0\",\"response\":"
				+ json2 + "}";
		HbaseLogRecordCollection collection = logTransformer.transform(json);
		Assert.assertEquals(4, collection.keySet().size());
		Assert.assertNotNull(collection.get("msg_v3a_play_auth2_txt_ad"));
		Assert.assertNotNull(collection.get("msg_v3a_play_auth2_pic_ad"));
		Assert.assertNotNull(collection.get("msg_v3a_play_auth2_video_ad"));
		Assert.assertNotNull(collection.get("msg_v3a_play_auth2"));
		// msg_v3a_play_auth2
		List<HbaseLogRecord> records = collection.get("msg_v3a_play_auth2");
		String key = records.get(0).getKey();
		Assert.assertEquals(1, records.size());
		Map<String, Object> data = records.get(0).getData();
		Assert.assertEquals(0, data.get("response_delaydeduct"));
		Assert.assertEquals(0l, data.get("response_stime"));
		Assert.assertEquals(new Date(1388502526l * 1000), data.get("stamp"));
		Assert.assertEquals(100405038l, data.get("request_uid"));
		Assert.assertEquals(111l, data.get("requestTime"));
		Assert.assertEquals(0, data.get("request_playtype"));
		Assert.assertEquals(1, data.get("request_sid"));
		Assert.assertEquals("125.125.125.125", data.get("serverIp"));
		Assert.assertEquals(
				"http://172.16.10.139:80/trends_index.jsp?key=355375_e8d3898e7ad7a22f9b96b484d1532e5e",
				data.get("response_play_url"));
		Assert.assertEquals("-13", data.get("response_status"));
		Assert.assertEquals(300l, data.get("response_etime"));
		Assert.assertEquals("0.0", data.get("version"));
		Assert.assertEquals("34a1a6a693d3b97f2987274890deb5bd",
				data.get("request_fid"));
		Assert.assertEquals(0l, data.get("response_time"));
		Assert.assertEquals("340009", data.get("response_pid"));
		Assert.assertEquals(180, data.get("response_delaytime"));
		Assert.assertEquals(24534761, data.get("request_mid"));
		Assert.assertEquals(101005, data.get("request_pid"));
		Assert.assertEquals(222l, data.get("responseTime"));
		Assert.assertEquals("bc83a7ec31be0000000000000000000000000000",
				data.get("request_hid"));
		Assert.assertEquals(153, data.get("request_oemid"));
		// msg_v3a_play_auth2_txt_ad
		records = collection.get("msg_v3a_play_auth2_txt_ad");
		Assert.assertEquals(1, records.size());
		data = records.get(0).getData();
		Assert.assertEquals("优朋广告 优朋广告 优朋广告", data.get("txt"));
		Assert.assertEquals(-1l, data.get("inserttime"));
		Assert.assertEquals(new Date(1388502526l * 1000), data.get("stamp"));
		Assert.assertEquals("0.0", data.get("version"));
		Assert.assertEquals(key + "_001", records.get(0).getKey());
		// msg_v3a_play_auth2_pic_ad
		records = collection.get("msg_v3a_play_auth2_pic_ad");
		Assert.assertEquals(1, records.size());
		data = records.get(0).getData();
		Assert.assertEquals(new Date(1388502526l * 1000), data.get("stamp"));
		Assert.assertEquals("0.0", data.get("version"));
		Assert.assertEquals(
				"http://imgadmin.voole.com/ishow/201108021635261001z211f",
				data.get("pic"));
		Assert.assertEquals(15l, data.get("length"));
		Assert.assertEquals(key + "_001", records.get(0).getKey());
		// msg_v3a_play_auth2_video_ad
		records = collection.get("msg_v3a_play_auth2_video_ad");
		Assert.assertEquals(2, records.size());
		for (int i = 0; i < records.size(); i++) {
			data = records.get(i).getData();
			Assert.assertEquals(new Date(1388502526l * 1000), data.get("stamp"));
			Assert.assertEquals("0.0", data.get("version"));
			Assert.assertEquals(0l, data.get("inserttime"));
			Assert.assertEquals(15l, data.get("length"));
			Assert.assertEquals("现在拿起遥控器按777有惊喜哦" + i, data.get("txt"));
			Assert.assertEquals("31ed101dc6ecb5f1f9356e66d17794bf",
					data.get("video"));
			Assert.assertEquals(key + "_00" + (i + 1), records.get(i).getKey());
		}
	}

	@Test
	public void test3() throws LogTransformInitException, LogTransformException {
		String raw = "1388502526#{\"serverip\":\"125.39.26.68\",\"startTime\":\"1370684605897\",\"endTime\":\"1370684607330\",\"request\":\"action=playauth2&uid=156335&oemid=183&hid=000102030405&mid=64354113&sid=1&fid=c8e45e4a631a7ab5b8a65c094a070976&pid=101001&playtype=0&ext=ZXBnaWQ9MTAwMTEwJmxvZ2lkPTAmc3BpZD0yMDEyMDYyOSZ2b3NwX2tleWlkPTcxODQyJmFyZWE9NjMwNiZ3bGFuPTEmdHZpZD0maXMzZD0wJnJhdGU9MTMwMCZzdGltZT0mZXRpbWU9JmV4dD1vaWQ6MTgzLGVpZDoxMDAxMTAsY29kZTpCMkNCT1hfcmVjb21tZW5lbmRlZCxwaWQ6MTAxMDAxJndpZHRoPTEyODAmZnBzPTI0JmhlaWdodD03MjAmbWVkaXVtdHlwZT1tM3U4Jm10eXBlPTAmUHJldmlldz0wJnRpbWU9MA%3D%3D&datedd=1370684427&version=1\",\"response\":{\"reqno\":\"156335_1370684605901\",\"status\":\"0\",\"resultdesc\":\"操作成功\",\"play_url\":\"http://125.39.26.68:80/trends_index.jsp?key=156335_c8e45e4a631a7ab5b8a65c094a070976\",\"delaydeduct\":\"0\",\"delaytime\":\"180\",\"pid\":\"101001\",\"playtime\":{\"priview\":\"0\",\"time\":\"0\",\"stime\":\"0\",\"etime\":\"0\"},\"adinfo\":[[{\"video\":\"149807eb850b3581818eb0fb0444deb8\",\"txt\":\"现在拿起遥控器按777有惊喜哦\",\"inserttime\":\"0\",\"length\":\"109\"}]]}}";
		LogModelVersion version = service.get("msg_v3a_play_auth2", "0.0");
		LogTransformer logTransformer = new JsonLogTransformer(version);
		HbaseLogRecordCollection collection = logTransformer.transform(raw);
		Assert.assertEquals(2, collection.keySet().size());
		Assert.assertNull(collection.get("msg_v3a_play_auth2_txt_ad"));
		Assert.assertNull(collection.get("msg_v3a_play_auth2_pic_ad"));
		Assert.assertNotNull(collection.get("msg_v3a_play_auth2_video_ad"));
		Assert.assertNotNull(collection.get("msg_v3a_play_auth2"));
		// msg_v3a_play_auth2
		List<HbaseLogRecord> records = collection.get("msg_v3a_play_auth2");
		String key = records.get(0).getKey();
		Assert.assertEquals(1, records.size());
		Map<String, Object> data = records.get(0).getData();
		Assert.assertEquals(0, data.get("response_delaydeduct"));
		Assert.assertEquals(0l, data.get("response_stime"));
		Assert.assertEquals(156335l, data.get("request_uid"));
		Assert.assertEquals(1370684605897l, data.get("requestTime"));
		Assert.assertEquals(0, data.get("request_playtype"));
		Assert.assertEquals(1, data.get("request_sid"));
		Assert.assertEquals("125.39.26.68", data.get("serverIp"));
		Assert.assertEquals(
				"http://125.39.26.68:80/trends_index.jsp?key=156335_c8e45e4a631a7ab5b8a65c094a070976",
				data.get("response_play_url"));
		Assert.assertEquals("0", data.get("response_status"));
		Assert.assertEquals(0l, data.get("response_etime"));
		Assert.assertEquals("c8e45e4a631a7ab5b8a65c094a070976",
				data.get("request_fid"));
		Assert.assertEquals(0l, data.get("response_time"));
		Assert.assertEquals("101001", data.get("response_pid"));
		Assert.assertEquals(180, data.get("response_delaytime"));
		Assert.assertEquals(64354113, data.get("request_mid"));
		Assert.assertEquals(101001, data.get("request_pid"));
		Assert.assertEquals(1370684607330l, data.get("responseTime"));
		Assert.assertEquals("000102030405", data.get("request_hid"));
		Assert.assertEquals(183, data.get("request_oemid"));
		Assert.assertEquals(new Date(1388502526l * 1000), data.get("stamp"));
		Assert.assertEquals("0.0", data.get("version"));
		// msg_v3a_play_auth2_video_ad
		records = collection.get("msg_v3a_play_auth2_video_ad");
		Assert.assertEquals(1, records.size());
		data = records.get(0).getData();
		Assert.assertEquals(new Date(1388502526l * 1000), data.get("stamp"));
		Assert.assertEquals("0.0", data.get("version"));
		Assert.assertEquals(0l, data.get("inserttime"));
		Assert.assertEquals(109l, data.get("length"));
		Assert.assertEquals("现在拿起遥控器按777有惊喜哦", data.get("txt"));
		Assert.assertEquals("149807eb850b3581818eb0fb0444deb8",
				data.get("video"));
		Assert.assertEquals(key + "_001", records.get(0).getKey());
	}

	@Test
	public void test4() throws IOException, LogTransformInitException,
			LogTransformException {
		URL url = this.getClass().getClassLoader()
				.getResource("testEntity.txt");
		FileReader fileReader = new FileReader(url.getFile());
		BufferedReader read = new BufferedReader(fileReader);
		String line = "";
		while ((line = read.readLine()) != null) {
			String[] strs = line.split("\\t");
			tests(strs[0], strs[1]);
		}
		read.close();
	}

	private void tests(String topic, String raw)
			throws LogTransformInitException, LogTransformException {
		Map<String, String> topicToTable = new HashMap<String, String>();
		topicToTable.put("3a_subscribe", "msg_v3a_order");
		topicToTable.put("3a_play_auth2", "msg_v3a_play_auth2");
		topicToTable.put("3a_pay_subscribe_3", "msg_v3a_3rd_payment");
		LogModelVersion version = service.get("msg_v3a_play_auth2", "0.0");
		LogTransformer logTransformer = transformerManager
				.getTransformer(version);
		HbaseLogRecordCollection collection = logTransformer.transform(raw);
		Assert.assertTrue(collection.keySet().size() > 0);
		Assert.assertTrue(collection.entrySet().iterator().next().getValue()
				.size() > 0);
		Assert.assertTrue(collection.entrySet().iterator().next().getValue()
				.get(0).getData().size() > 2);

	}
}
