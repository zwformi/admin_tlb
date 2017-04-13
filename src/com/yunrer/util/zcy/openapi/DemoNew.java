package com.yunrer.util.zcy.openapi;

import org.json.JSONObject;

import com.yunrer.util.zcy.openapi.http.HttpMethod;
import com.yunrer.util.zcy.openapi.zoss.model.ZcyOssBusiEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZCY政采云网络有限公司
 *   政采云开放平台api调用demo
 *   新版本:政采云推荐您使用本调用方式
 * update on 20160920
 */
public class DemoNew {
//    private static final String API_GATEWAY = "http://121.196.217.18:9002/";
//   private static final String SECRET  = "F4Cbc4nnKMJg";
//    private static final String APP_KEY = "354232";
	
	private static final String API_GATEWAY = "http://118.178.149.133:9002";
	private static final String SECRET = "qa7mVz1DDN";
	private static final String APP_KEY = "909669";
    public static void main(String[] args) throws Exception {
//        doGet();
//        doPost();
        //doPostBinary();
//        doPostMutipart();
    	
    }
    public  static void doGet() throws Exception {
        String uri = "/open/zcy.category.get?root=0&depth=4";

        ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(APP_KEY,SECRET,API_GATEWAY);
        zcyOpenRequest.setUri(uri);
        zcyOpenRequest.setMethod(HttpMethod.GET);
        /*发送http request*/
        String result = ZcyOpenClient.excute(zcyOpenRequest);
        /*打印返回结果*/
        System.out.println(result);
    }

    public  static void doPost() throws Exception {
        String uri = "/open/zcy.category.brand.get";

        ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(APP_KEY,SECRET,API_GATEWAY);
        zcyOpenRequest.setUri(uri);
        zcyOpenRequest.setMethod(HttpMethod.POST);

        /*组装Body参数*/
        Map<String, Object> bodyMap = new HashMap<String, Object>();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categoryId", 21);
        jsonObject.put("enBrandName","hp");
//        jsonObject.put("depth",3);
        bodyMap.put("_data_", jsonObject.toString());
        zcyOpenRequest.setParamMap(bodyMap);

        /*发送http request*/
        String result = ZcyOpenClient.excute(zcyOpenRequest);

        /*打印返回结果*/
        System.out.println(result);
    }

    public  static void doPostBinary() throws Exception {
        String uri = "/test/category.back.tree";

        ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(APP_KEY,SECRET,API_GATEWAY);
        zcyOpenRequest.setUri(uri);
        zcyOpenRequest.setMethod(HttpMethod.POST_BINARY);
        /*组装Body参数*/
        Map<String, Object> bodyMap = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("root", 0);
        jsonObject.put("depth", 3);
        bodyMap.put("_data_", jsonObject.toString());

        zcyOpenRequest.setParamMap(bodyMap);

        /*发送http request*/
        String result = ZcyOpenClient.excute(zcyOpenRequest);

        /*打印返回结果*/
        System.out.println(result);
    }

    public  static void doPostMutipart() throws Exception {
        String uri = "/open/zcy.zoss.file.upload";//上传附件

        ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(APP_KEY,SECRET,API_GATEWAY);
        zcyOpenRequest.setUri(uri);
        zcyOpenRequest.setMethod(HttpMethod.POST_MUTIPART);

        /*组装Body参数*/
        Map<String, Object> bodyMap = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileName", "redis使用范例.png");
        jsonObject.put("bizCode", ZcyOssBusiEnum.Others.getBusiCode());//附件类型:必须在可选枚举值内,传入其它值将上传失败
        jsonObject.put("type","application/octet-stream");
        jsonObject.put("description","redis使用范例.png");
        bodyMap.put("_data_", jsonObject.toString());

        zcyOpenRequest.setParamMap(bodyMap);

        /*构建文件输入流,POST_MUTIPART方式上传文件,文件输入流必填*/
        File file = new File("/var/tmp/redis使用范例.png");
        InputStream fileInputStream = new FileInputStream(file);

        zcyOpenRequest.setInputStream(fileInputStream);

        /*发送http request*/
        String result = ZcyOpenClient.excute(zcyOpenRequest);

        /*打印返回结果*/
        System.out.println(result);
    }
}
