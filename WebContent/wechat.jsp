<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>  
<%@page import="java.io.IOException"%>  
<%@page import="java.io.InputStreamReader"%>  
<%@page import="java.io.BufferedReader"%>  
<%@page import="java.io.Reader"%>  
<%@page import="java.security.MessageDigest"%>  
<%@page import="java.util.Arrays"%>  
<%@page import="pac.Auto_reply" %>
	<%  
        //WeiXinHandler为内部类不能使用非final类型的对象  
        final String TOKEN="shallwe";
        final HttpServletRequest final_request=request;
        final HttpServletResponse final_response=response;  
    %>  
    <%   
    class WeiXinHandler{  
        public void valid(){  
            String echostr=final_request.getParameter("echostr");
            if(null==echostr||echostr.isEmpty()){  
                responseMsg();  
            }else{  
                if(this.checkSignature()){  
                    this.print(echostr);  
                }else{  
                    this.print("error");                                                                                                                                                                                                                                                                                                                                           
                }  
            }  
        }  
        //自动回复内容  
        public void responseMsg(){  
            String postStr=null;  
            try{  
                postStr=this.readStreamParameter(final_request.getInputStream()); 
            }catch(Exception e){  
                System.out.println("get xml string error!"); 
            }  
            //System.out.println(postStr);  
            if (null!=postStr&&!postStr.isEmpty()){
            	Auto_reply now = new Auto_reply(postStr); 
                this.print(now.get_reply());
            }else {  
                this.print("");  
            }  
        }  
        //微信接口验证  
        public boolean checkSignature(){  
            String signature = final_request.getParameter("signature");  
            String timestamp = final_request.getParameter("timestamp");  
            String nonce = final_request.getParameter("nonce");  
            String token=TOKEN;  
            String[] tmpArr={token,timestamp,nonce};  
            Arrays.sort(tmpArr);  
            String tmpStr=this.ArrayToString(tmpArr);  
            tmpStr=this.SHA1Encode(tmpStr);  
            if(tmpStr.equalsIgnoreCase(signature)){  
                return true;  
            }else{  
                return false;  
            }  
        }  
        //向请求端发送返回数据  
        public void print(String content){  
            try{  
                final_response.getWriter().print(content);  
                final_response.getWriter().flush();  
                final_response.getWriter().close();  
            }catch(Exception e){  
                  
            }  
        }  
        //数组转字符串  
        public String ArrayToString(String [] arr){  
            StringBuffer bf = new StringBuffer();  
            for(int i = 0; i < arr.length; i++){  
             bf.append(arr[i]);  
            }  
            return bf.toString();  
        }  
        //sha1加密  
        public String SHA1Encode(String sourceString) {  
            String resultString = null;  
            try {  
               resultString = new String(sourceString);  
               MessageDigest md = MessageDigest.getInstance("SHA-1");  
               resultString = byte2hexString(md.digest(resultString.getBytes()));  
            } catch (Exception ex) {  
            }  
            return resultString;  
        }  
        public final String byte2hexString(byte[] bytes) {  
            StringBuffer buf = new StringBuffer(bytes.length * 2);  
            for (int i = 0; i < bytes.length; i++) {  
                if (((int) bytes[i] & 0xff) < 0x10) {  
                    buf.append("0");  
                }  
                buf.append(Long.toString((int) bytes[i] & 0xff, 16));  
            }  
            return buf.toString().toUpperCase();  
        }  
        //从输入流读取post参数  
        public String readStreamParameter(ServletInputStream in){  
            StringBuilder buffer = new StringBuilder();  
            BufferedReader reader=null;  
            try{  
                reader = new BufferedReader(new InputStreamReader(in,"UTF-8")); //very important 
                String line=null;  
                while((line = reader.readLine())!=null){  
                    buffer.append(line);  
                }  
            }catch(Exception e){  
                e.printStackTrace();  
            }finally{  
                if(null!=reader){  
                    try {  
                        reader.close();  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
            return buffer.toString();  
        }  
    }  
    %>  
    <%  
        WeiXinHandler handler=new WeiXinHandler();  
        handler.valid();  
    %>  