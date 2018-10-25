package cdn;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

/**
 * @Description: 七牛云cdn上传文件
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/23 11:39
 */
public class CDNTest {

	public static void main(String[] args) {
		try {
			uploadFileToQINIU();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * qiniu云上传文件
	 *
	 * @throws Exception
	 */
	public static void uploadFileToQINIU() throws Exception {
		Config.ACCESS_KEY = CDNConstans.ACCESS_KEY;
		Config.SECRET_KEY = CDNConstans.SECRET_KEY;
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		// 请确保该bucket已经存在
		String bucketName = "";
		PutPolicy putPolicy = new PutPolicy(bucketName);
		String upToken = putPolicy.token(mac);
		System.out.println("upToken:" + upToken);
		PutExtra extra = new PutExtra();
		String key = "";
		String localFile = "C:\\Users\\zhongqionghua\\Desktop\\1.jpg";
		PutRet ret = IoApi.putFile(upToken, key, localFile, extra);
		System.out.println(JSONObject.toJSONString(ret));
	}
}
