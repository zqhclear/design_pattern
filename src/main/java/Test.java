
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class Test {

	public static final Pattern pattern = Pattern.compile("S_R_(\\d+)|I_R_(\\d+)");

	public static void main(String[] args) throws IOException {
//		String code = "474554202f736f636b65742e696f2f3f45494f3d33267472616e73706f72743d706f6c6c696e6726743d313534313735343639353837332d3020485454502f312e310d0a486f73743a206c6f63616c686f73743a31303434330d0a436f6e6e656374696f6e3a206b6565702d616c6976650d0a4f726967696e3a20687474703a2f2f6c6f63616c686f73743a36333334320d0a557365722d4167656e743a204d6f7a696c6c612f352e30202857696e646f7773204e542031302e303b2057696e36343b2078363429204170706c655765624b69742f3533372e333620284b48544d4c2c206c696b65204765636b6f29204368726f6d652f37302e302e333533382e3737205361666172692f3533372e33360d0a4163636570743a202a2f2a0d0a526566657265723a20687474703a2f2f6c6f63616c686f73743a36333334322f636c69656e742f73736c2d6576656e742d696e6465782e68746d6c3f5f696a743d346a656e7632387161746e346f3935346f616a346735337639680d0a4163636570742d456e636f64696e673a20677a69702c206465666c6174652c2062720d0a4163636570742d4c616e67756167653a207a682d434e2c7a683b713d302e390d0a436f6f6b69653a20696f3d62376236323738632d376130332d346363312d626339312d3736616665613763616530323b20496465612d36396134303230313d35653264303931372d363264642d343161382d613063332d6461383565353634643838350d0a0d0a";
//		byte[] bytes = hexStr2Bytes(code);
//		System.out.println(new String(bytes));

		//查看cpus
		//System.out.println(Runtime.getRuntime().availableProcessors());

		//assert
//		Assert.isTrue(true, "这是真的");
//		Assert.isTrue(false, "假的");
//		System.out.println("直接抛出异常,代码不会往下走");

		//String.format
//		System.out.println(String.format("%n.%n", "table", "name"));

		// parrent
//		Matcher m = pattern.matcher("S_R_267S_R_401S_R_413+");
//		if(m.find()){
//			m.reset();
//		}else{
//			m = null;
//		}
//		if(m!=null  && m.find()){
//			String ruleId = null != m.group(1) ? m.group(1) : m.group(2);
//			System.out.println(ruleId);
//		}

		//游标重置
		//m.reset();

		//decode
//		String str = "ruleName=%E7%90%BC&ruleType=10&ruleRelation=min&passRatio=0&passCount=0&orgNo=sh_fk&state=10&expressions%5B0%5D.id=5704&expressions%5B0%5D.ruleId=425&expressions%5B0%5D.type=number&expressions%5B0%5D.action=score_rule&expressions%5B0%5D.result=260&expressions%5B0%5D.missResult=263&expressions%5B0%5D.originalExpression=%3Cdiv+class%3D%22express-tag%22+value%3D%22S_R_267%22+name%3D%22%E8%A5%BF%E7%93%9C%E4%BF%A1%E7%94%A8_%E5%8F%8D%E6%AC%BA%E8%AF%88%E6%98%AF%E5%90%A6%E5%91%BD%E4%B8%AD%E9%BB%91%E5%90%8D%E5%8D%95%E8%A7%84%E5%88%99%22+rid%3D%22267%22%3E%E8%A5%BF%E7%93%9C%E4%BF%A1%E7%94%A8_%E5%8F%8D%E6%AC%BA%E8%AF%88%E6%98%AF%E5%90%A6%E5%91%BD%E4%B8%AD%E9%BB%91%E5%90%8D%E5%8D%95%E8%A7%84%E5%88%99%3C%2Fdiv%3E%3Cdiv+class%3D%22express-tag%22+value%3D%22S_R_401%22+name%3D%22%E5%90%8C%E7%9B%BErule%22+rid%3D%22401%22%3E%E5%90%8C%E7%9B%BErule%3C%2Fdiv%3E%3Cdiv+class%3D%22express-tag%22+value%3D%22S_R_413%22+name%3D%22test123123123123123123%22+rid%3D%22413%22%3Etest123123123123123123%3C%2Fdiv%3E%3Cdiv+class%3D%22express-tag%22+value%3D%22%2B%22+name%3D%22%2B%22%3E%2B%3C%2Fdiv%3E&expressions%5B0%5D.expression=S_R_267S_R_401S_R_413%2B&decisionStatus=10&outScoreStatus=10&minScore=100&maxScore=200&ruleDecisions%5B0%5D.formula=%3C&ruleDecisions%5B0%5D.integral=50&ruleDecisions%5B0%5D.result=30&ruleResult=ald_result_score&id=425&productId=27&productLabelId=1";
//		String strDecode = URLDecoder.decode(str, "utf-8");
//
//		System.out.println(JSONObject.toJSONString(strDecode));
//		String[] strings = strDecode.split("&");
//		Map<String, String> propertyMap = new HashMap<>(64);
//		for(String property : strings){
//			String[] properties = property.split("=");
//			if(properties == null || properties.length < 2){
//				continue;
//			}
//			//为表达式
//			if(properties[0].contains("expressions")){
//
//			}
//
//			//
//			propertyMap.put(properties[0], properties[1]);
//		}
//		System.out.println(JSONObject.toJSONString(propertyMap));


		// >>>操作
//		int n = 15;
//		n |= n >>> 1;
//		System.out.println(n);

//		//convertUtils
//		System.out.println(ConvertUtils.convert("465465", Integer.class));
//
//		Stack stack = new Stack();
//		stack.peek();
//		stack.pop();


//		System.out.println(decryptFromBase64("BGxKtjbfzgi+lr5EB+LU6Q==", "testC1b6x@6aH$2dlw"));
//		System.out.println(decryptFromBase64("cJiZKo2M0HcKZdjgGmv/vQ==", "testC1b6x@6aH$2dlw"));

//		countParams();


//		forEachBreakTest();


		Map<Object, Object> map = new HashMap<Object, Object>(){{
			put(new A("name1", "phone1"), new A("name2", "phone2"));
		}};
		System.out.println(JSONObject.toJSONString(map));
	}


	private static void forEachBreakTest(){
		for (int i = 0; i <10 ; i++) {
			outer:for (int j = 0; j <10 ; j++) {
				System.out.println("this is test");
				break;
			}
		}
	}

	private static int countParams(String... params) {
		int count = 0;
		for (int i = 0; i < params.length; i++) {
			count++;
		}
		System.out.println("count:" + count);
		return count;
	}


	/**
	 * 加密
	 *
	 * @param base64Str
	 * @param password
	 * @return
	 */
	public static String decryptFromBase64(String base64Str, String password) {
		String result = "";

//		try {
//			result = new String(decrypt(Base64.decode(String.valueOf(base64Str.getBytes("UTF-8"))), password), "UTF-8");
//		} catch (UnsupportedEncodingException var4) {
//			var4.printStackTrace();
//		}
		return result;
	}

	/**
	 * 加密
	 *
	 * @param content
	 * @param password
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(2, key);
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (NoSuchAlgorithmException var9) {
		} catch (NoSuchPaddingException var10) {
			System.out.println("decrypt NoSuchPaddingException" + var10);
		} catch (InvalidKeyException var11) {
			System.out.println("decrypt InvalidKeyException" + var11);
		} catch (IllegalBlockSizeException var12) {
			System.out.println("decrypt IllegalBlockSizeException" + var12);
		} catch (BadPaddingException var13) {
			System.out.println("decrypt BadPaddingException" + var13);
		}
		return null;
	}


	/**
	 * byte转为hex串
	 *
	 * @param byteArr
	 * @return
	 */
	static String bytes2HexStr(byte[] byteArr) {
		if (null == byteArr || byteArr.length < 1) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (byte t : byteArr) {
			if ((t & 0xF0) == 0) {
				sb.append("0");
			}
			//t & 0xFF 操作是为去除Integer高位多余的符号位（java数据是用补码表示）
			sb.append(Integer.toHexString(t & 0xFF));
		}
		return sb.toString();
	}

	/**
	 * hex串转为byte
	 *
	 * @param hexStr
	 * @return
	 */
	static byte[] hexStr2Bytes(String hexStr) {
		if (null == hexStr || hexStr.length() < 1) {
			return null;
		}

		int byteLen = hexStr.length() / 2;
		byte[] result = new byte[byteLen];
		char[] hexChar = hexStr.toCharArray();
		for (int i = 0; i < byteLen; i++) {
			result[i] = (byte) (Character.digit(hexChar[i * 2], 16) << 4 | Character.digit(hexChar[i * 2 + 1], 16));
		}
		return result;
	}
}

class A {
	private String name;
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public A(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}
}

