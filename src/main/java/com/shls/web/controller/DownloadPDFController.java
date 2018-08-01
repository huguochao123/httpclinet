package com.shls.web.controller;

import com.shls.db.po.AbsProject;
import com.shls.db.po.Client;
import com.shls.db.po.Receivables;
import com.shls.db.service.AbsProjectService;
import com.shls.db.service.ClientService;
import com.shls.db.service.ReceivablesService;
import com.shls.utils.CommonUtils;
import com.shls.utils.ZipUtil;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/download")
public class DownloadPDFController extends BaseController {
    public CookieStore cookieStore = new BasicCookieStore();

    private  static final String TYPE = "&type=1";

    @Value("${url}")
    public String url;

    @Value("${loginUrl}")
    public String loginUrl;

    @Value("${loginVlidataCode}")
    public String loginVlidataCode;

    @Value("${vlidataCode}")
    public String vlidataCode;

    @Value("${queryByName}")
    public String queryByName;

    @Value("${identifyCode}")
    public String identifyCode;

    @Value("${sessionId}")
    public String sessionId;

    @Value("${downloadPDF}")
    public String downloadPDF;


    private final int MAX_CONN_TOTAL = 10;           // 连接池中的最大连接数
    private final int MAX_CONN_PER_ROUTE = 10;       // 连接同一个route最大的并发数

    private final int CONNECT_REQUEST_TIMEOUT = 60000; // 从连接池中获取可用连接最大超时时间 单位：毫秒
    private final int CONNECT_TIMEOUT = 60000;         // 连接目标url最大超时 单位：毫秒
    private final int SOCKET_TIMEOUT = 60000;          // 等待响应（读数据）最大超时 单位：毫秒


    RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT) // 单位为毫秒
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build();

    public CloseableHttpClient httpClient = HttpClients.custom()
            .setDefaultCookieStore(cookieStore)
            .setMaxConnTotal(MAX_CONN_TOTAL)
            .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
            .setDefaultRequestConfig(requestConfig)
            .build();

    @Value("${userName}")
    public String userName;

    @Value("${enterTheDownload}")
    public String enterTheDownload;

    @Value("${downloadJPG}")
    public String downloadJPG;
    @Autowired
    AbsProjectService absProjectService;
    @Autowired
    ReceivablesService ReceivablesService;
    @Autowired
    ClientService ClientService;


    /**
     * @author hujunhao 2018/04/12
     * 根据供应商名获取文件
     * @param response
     * @param name
     * @param sDate
     */
    @RequestMapping(value = "/getGYSName" , method = RequestMethod.GET)
    public void getGYSName(HttpServletResponse response, @RequestParam String name,@RequestParam String sDate){
        if(CommonUtils.isDate(sDate) == false){
            if(!sDate.equals("no")){
                throw  new RuntimeException("时间格式不正确");
            }
        }
        //模拟登陆获取登陆的cookie
        String cookie = getCookie();
        //登陆成功后的页面
        loginIsTrue(cookie);
        Document documents = getElements(null,cookie,name);
        Map map = null;
        try {
            if (documents != null) {
                map = getRegno(cookie, documents,sDate);
            } else {
                throw new RuntimeException("登陆超时!");
            }
            if (map != null) {
                wrapDownloadHttpResponse(name + ".zip", response);
                ZipUtil.compress(map, response.getOutputStream());
            } else {
                throw new RuntimeException(name+"下没有pdf文件");
            }
        }catch (IOException e){
            e.getMessage();
        }
    }

    /**
     * @author hujunhao 2018/04/12
     * 根据项目id获取文件
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping(value = "/getGYSId" , method = RequestMethod.GET)
    public void getGYSId(HttpServletResponse response, @RequestParam long id) throws  Exception{
        AbsProject absProject = absProjectService.findExistingById(id);
        List<Receivables> Receivables = ReceivablesService.createQuery().eq("projectId",absProject.getId()).list();
        List<Client> list = new ArrayList();
        Set set = new HashSet();
        //模拟登陆获取登陆的cookie
        String cookie = getCookie();
        //登陆成功后的页面
        loginIsTrue(cookie);
        for (Receivables r:Receivables) {
               list.add(ClientService.findById( r.getSupplierId()));
        }

        List error = new ArrayList();

        List<Client> listClient = new ArrayList();

        for(Client client:list){
            if(set.add(client.getName())){
                listClient.add(client);
            }
        }
        for (Client client : list){
           /* if(client!=null){
                Map map = null;
                Document documents = getElements(client,cookie,"");
                if(documents!=null) {
                    map = getRegno(cookie, documents);
                }else{
                    throw  new RuntimeException("登陆超时!");
                }
                if(map != null){
                    wrapDownloadHttpResponse(client.getName()+".zip", response);
                    ZipUtil.compress(map, response.getOutputStream());
                    throw new RuntimeException(error+"");
                }else{
                    //throw new RuntimeException("没有供应商!!");
                    error.add(client.getName()+" 没有pdf文件！");
                }
            }*/
        }
    }

    //获取供应商regno
    public Map getRegno(String cookie,Document documents,String sDate){
        Elements elements1  = documents.getElementsByTag("script");
        int count = 0;
        List<String> lists = new ArrayList();
        for(Element element : elements1){
            ++count;
            if(count > 4) {
                String[] data = element.data().toString().split("var summaryData = eval");
                String[] arr = data[1].split("function initSummary()");
                String src = arr[0].trim().substring(8);
                if(sDate.equals("no")) {
                    String regex = "ar.*]]";
                    Matcher matcher = Pattern.compile(regex).matcher(src);
                    String ret = "";
                    while (matcher.find()) {
                        ret += matcher.group(0);
                    }
                    String s1 = "{" + ret + "}}";
                    String regexs = "[0-9-()()]{20}";
                    Matcher matcherOne = Pattern.compile(regexs).matcher(s1);
                    while (matcherOne.find()) {
                        String str1 = matcherOne.group(0);
                        lists.add(str1);
                    }
                }else{
                    lists = getRegNo(src,sDate);
                }
            }
        }
        Map map = enterTheDownload(lists,cookie);
        if(map != null){
            return map;
        }
        return null;
    }
    //根据regNo来进入下载页面，获取下载文件跟下载附件
    public Map<String,byte[]> enterTheDownload(List<String> list,String cookie){
        CloseableHttpResponse response2 = null;
        try{
            if(list != null || list.size()>0){
                Map<String,byte[]> map = new HashMap<String,byte[]>();
                for (String st: list){
                    HttpGet httpGet = new HttpGet(enterTheDownload+st+TYPE);
                    response2 = httpClient.execute(httpGet);
                    String viewfile = EntityUtils.toString(response2.getEntity());
                    Document document = Jsoup.parse(viewfile);
                    Elements elements = document.getElementsByTag("a");
                    int count=0;
                    for(Element element : elements){
                        ++count;
                        String aStr = element.toString();
                        byte[] datas = null;
                        if(count == 1 && count != 0){
                            String regnoT = aStr.split("download")[1].split(";")[0];
                            String regno = regnoT.substring(2,regnoT.length()-2);
                            datas = donwloan(regno,cookie,"PDF","");
                            map.put(regno+".pdf",datas);
                        }else if(count >1 && count != 0){
                            String tachT = aStr.split("downloadattach")[1].split(";")[0];
                            String[] tachT2 =  tachT.substring(2,tachT.length()-2).split("','");
                            String saveName = tachT2[0];
                            String shwoName = tachT2[1];
                            datas = donwloan(saveName+"!"+shwoName,cookie,"JPG",st);
                            map.put(shwoName,datas);
                        }
                    }
                }
                return map;
            }else{
                return null;
            }
        }catch (Exception e){
            e.getMessage();
        }finally {
            try {
                response2.close();
            }catch (Exception e){
                e.getMessage();
            }
        }
        return null;
    }
    //根据时间找出对应的regNo
    public List getRegNo(String str,String sDate){
        List lists1 = new ArrayList();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String [] str1 = str.split("d:");
        String str3 = str1[1].split("}")[0];
        String str4 = str3.substring(1,str3.length()-1);
        List<List<String>> list = new ArrayList<List<String>>();

        String[] str5 = str4.split("],");
        for (String s1:str5){
            List lists = new ArrayList();
            String s2=s1.substring(1,s1.length());
            if(s2.contains("]")){
                s2=s2.substring(0,s2.length()-1);
            }
            String s3[] = s2.split(",");
            for (String s4:s3){
                lists.add("'"+s4.substring(1,s4.length()-1)+"'");
            }
            list.add(lists);
        }
        List<List> list2 = new ArrayList();
        for(List list1 : list){
            int count = 0;
            for(Object st : list1){
                ++count;
                if(count==3){
                    String date = st.toString().split(" ")[0].substring(1);
                    try {
                        Date dt1 =df.parse(date);
                        Date dt2 =df.parse(sDate);
                        if(dt2.getTime()<dt1.getTime()){
                            list2.add(list1);
                        }
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
                if(count == 5) {
                    count = 0;
                }
            }
        }
        for(List list3 : list2){
            int count = 0;
            for(Object obj : list3){
                ++count;
                if(count == 2){
                    String regno = obj.toString();
                    String regNumber = regno.substring(1,regno.length()-1);
                    lists1.add(regNumber);
                }
                if(count == 5) {
                    count = 0;
                }
            }
        }
        return lists1;
    }

    //进入第二次验证码的页面
    public Document getElements(Client client,String cookie,String name){

        CloseableHttpClient httpClient1 = HttpClients.createDefault();
        try {
            //获取第二次验证码
            String codeVlidatas = yan(httpClient1, vlidataCode, cookie);
            Connection con = Jsoup.connect(queryByName);
            //con.cookie("cookie", cookie);
            con.header("cookie",cookie);
            con.data("debttype", "1000");
            con.data("cert_no", "");
            if(name.equals("name")){
                con.data("name", client.getName());
            }else{
                con.data("name", name);
            }
            con.data("validateCode", codeVlidatas);
            con.header("Connection", "Keep-Alive");
            con.header("Content-Length", "11000");
            con.timeout(30000);
            Document documents = con.post();
            String str = documents.toString();
            //System.out.println(str);
            if (str.contains(" 校验码错误 ")) {
                throw new RuntimeException("验证码错误");
            }
            if(str.contains("用户连接超时，请重新")){
                throw new RuntimeException("用户连接超时，请重新");
            }
            return documents;
        }catch (Exception e){
            e.getMessage();
        }finally {
            try{
                httpClient1.close();
            }catch (IOException e){
                e.getMessage();
            }
        }
        return null;
    }


    //模拟登陆
    public String getCookie() {
        //获取验证码
        String codeVlidata = yan(httpClient, loginVlidataCode, null);
        //创建请求方式
        HttpPost httpPost = new HttpPost(url);
        //设置发送的请求参数
        List<NameValuePair> dlbd = new ArrayList();
        dlbd.add(new BasicNameValuePair("userCode", userName));
        dlbd.add(new BasicNameValuePair("password", password()));
        dlbd.add(new BasicNameValuePair("num", "登记证明编号"));
        dlbd.add(new BasicNameValuePair("validateCode", codeVlidata));
        dlbd.add(new BasicNameValuePair("showpassword", "密码"));
        dlbd.add(new BasicNameValuePair("paper", "1"));
        dlbd.add(new BasicNameValuePair("conone", "1"));
        dlbd.add(new BasicNameValuePair("type", ""));
        dlbd.add(new BasicNameValuePair("papercode", "校验码"));
        CloseableHttpResponse httpResponse = null;
        try {
            //将参数绑定到post中
            httpPost.setEntity(new UrlEncodedFormEntity(dlbd));
            httpResponse = httpClient.execute(httpPost);
            String stri = "";
            //获取登陆成功的cookie
            List<Cookie> cookies = cookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                stri += cookies.get(i).getName() + "=" + cookies.get(i).getValue() + "; ";
            }

            return stri;
        }catch (Exception e){
            e.getMessage();
        }finally {
            try{
                httpResponse.close();
            }catch (IOException e){
                e.getMessage();
            }
        }
        return null;
    }

    //验证码
    public String yan(CloseableHttpClient httpClient,String code,String checkCookie){
        HttpGet getVerifyCode = new HttpGet(code);//验证码get
        if(checkCookie!=null)
            getVerifyCode.setHeader("cookie",checkCookie);
        InputStream inputStream = null;
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(getVerifyCode);//获取验证码
            inputStream = response.getEntity().getContent();
            byte[] data=toByteArray(inputStream);

            BASE64Encoder encoder = new BASE64Encoder();
            String datas = encoder.encode(data);
            HttpPost httpPost = new HttpPost(identifyCode);

            List<NameValuePair> list = new ArrayList();
            list.add(new BasicNameValuePair("data",datas));
            httpPost.setEntity(new UrlEncodedFormEntity(list));

            CloseableHttpResponse httpResponses = httpClient.execute(httpPost);
            String a = EntityUtils.toString(httpResponses.getEntity());
            JSONObject jsonObject = JSONObject.fromObject(a);

            return (String)JSONObject.fromObject(jsonObject.get("data")).get("pic_str");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //密码加密
    public  String password() {
        String md = md5Encode("tjhexin908");
        String md5 = md5Encode(md.toUpperCase()+ session());
        String password = md5.toUpperCase();
        return password;
    }
    //MD5加密
    public  String md5Encode(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = null;
        try {
            byteArray = inStr.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for(int i=0;i<md5Bytes.length;i++){
            int val = md5Bytes[i] & 0xff;
            if(val<16){
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    //获取sessionId
    public  String session(){
        HttpGet httpGet1 = new HttpGet(sessionId);
        CloseableHttpResponse httpResponse1 = null;
        try {
            httpResponse1 = httpClient.execute(httpGet1);
            String ddd = EntityUtils.toString(httpResponse1.getEntity());
            Document document = Jsoup.parse(ddd);
            Elements elements = document.getElementsByTag("script");
            int count = 0;
            for (Element element: elements) {
                ++count;
                if(count>5 && count<7) {
                    String el = element.data().toString();
                    String[] arr = el.split("sessionId=");
                    String[] arr1 = arr[1].split(";");
                    String sessionId = arr1[0].substring(1,arr1[0].length()-1);
                    return sessionId;
                }
            }
        }catch (Exception e){
            e.getMessage();
        }finally {
            try{

                httpResponse1.close();
            }catch (IOException e){
                e.getMessage();
            }
        }
        return null;
    }
    //验证码转byte[]
    public byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024*4];
        int n=0;
        while ( (n=in.read(buffer)) !=-1) {
            out.write(buffer,0,n);
        }
        return out.toByteArray();
    }

    //下载pdf文件
    public byte[] donwloan(String st,String cookie,String isPDF,String regno){
        //Map<String,byte[]> map = new HashMap();
        CloseableHttpClient httpClient3 = null;
        CloseableHttpResponse response2 = null;
        HttpPost httpPost2 = null;
        try{
            InputStream inputStream = null;
            httpClient3 = HttpClients.createDefault();
            if(isPDF.equals("PDF")){
                List<NameValuePair> data = new ArrayList<NameValuePair>();
                httpPost2 = new HttpPost(downloadPDF);
                httpPost2.setHeader("cookie",cookie);
                data.add(new BasicNameValuePair("regno",st));
                data.add(new BasicNameValuePair("type","1"));
                httpPost2.setEntity(new UrlEncodedFormEntity(data));
            }else{
                List<NameValuePair> data = new ArrayList<NameValuePair>();
                String[] name = st.split("!");
                httpPost2 = new HttpPost(downloadJPG);
                httpPost2.setHeader("cookie",cookie);
                data.add(new BasicNameValuePair("regno",st));
                data.add(new BasicNameValuePair("type","1"));
                data.add(new BasicNameValuePair("save_name",name[0]));
                data.add(new BasicNameValuePair("show_name",name[1]));
                httpPost2.setEntity(new UrlEncodedFormEntity(data));
            }
            response2 = httpClient3.execute(httpPost2);
            inputStream = response2.getEntity().getContent();
            byte[] datas =toByteArray(inputStream);
            return datas;
        }catch (Exception e){
            e.getMessage();
        }finally {
            try {
                response2.close();
                httpClient3.close();
            }catch (Exception e){
                e.getMessage();
            }
        }
        return null;
    }

    /**
     *
     * @author hujunhao
     *
     * 登陆成功后的网址
     * @param cookie 登录之后获得的cookie
     */
    public boolean loginIsTrue(String cookie){
        HttpGet httpGet = new HttpGet(loginUrl);
        httpGet.addHeader(new BasicHeader("Cookie",cookie));
        httpGet.setHeader("Connection","Keep-Alive");
        httpGet.setHeader("Content-Length","11100");
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(httpGet);
            String s = EntityUtils.toString(response.getEntity());
            Document document = Jsoup.parse(s);
            Elements elements = document.getElementsByTag("script");
            if(elements.size()<2){
                throw new RuntimeException("验证码输入错误！");
            }
        }catch (Exception e){
            e.getMessage();
        }finally {
            try{
                response.close();
            }catch (IOException e){
                e.getMessage();
            }
        }
        return true;
    }
}
