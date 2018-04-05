package com.loto.e3mall.fastdsf;

import com.loto.e3mall.common.utils.FastDFSClient;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;


public class Test01_FastDFS {

	// 上传文件 - 使用StorageClient
	@Test
	public void testUpload() throws Exception {
		// 创建一个配置文件（文件名任意，内容是tracker服务器的地址）

		// 使用全局对象加载配置文件（使用绝对路径）
		// ClientGlobal.init("D:/workspaces/JavaEE/e3-manager-web/src/main/resources/conf/client.conf");
		ClientGlobal.init("/Users/td/Documents/IntelliJ_IDEA/JavaWeb06_5.0_e3mall/e3mall_manager_web/src/main/resources/conf/client.conf");

		// 创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();

		// 通过TrackClient获得一个TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();

		// 创建一个StrorageServer的引用，可以是null
		StorageServer storageServer = null;

		// 创建一个StorageClient，参数需要TrackerServer和StrorageServer
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);

		// 使用StorageClient上传文件
		// String[] strings = storageClient.upload_file("D:/Documents/Pictures/images/2f2eb938943d.jpg", "jpg", null);
		String[] strings = storageClient.upload_file("/Users/td/Pictures/TD/T.jpg", "jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}
	}

	// 上传文件 - 使用FastDFSClient工具类
	@Test
	public void testFastDfsClient() throws Exception {
		// 加载配置文件（使用绝对路径）
		// FastDFSClient fastDFSClient = new FastDFSClient("D:/workspaces/JavaEE/e3-manager-web/src/main/resources/conf/client.conf");
		FastDFSClient fastDFSClient = new FastDFSClient("/Users/td/Documents/IntelliJ_IDEA/JavaWeb06_5.0_e3mall/e3mall_manager_web/src/main/resources/conf/client.conf");

		String string = fastDFSClient.uploadFile("/Users/td/Pictures/TD/T.jpg");
		System.out.println(string);
	}
}
