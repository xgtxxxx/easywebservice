Easywebservice 主要为简化项目中webservice的编码而产生的
说明：

1、项目依赖：参见pom.xml

2、本项目只提供了基础的HttpComponent的client，当然你可以根据需要对client进行任意扩展(后面会详细说明)

3、简单例子（通过http的Get方法请求百度首页）：

main方法启动

    main(){
         Client client = new HttpSimpleClient();
         Request request = new BaiduRequest();
         String html = client.doRequest(request,new SimpleStringAdapter());
         System.out.println(html);
    }

BaiduRequest:

	@SupperAvailable
	public class BaiduRequest extends Request {
          @Override
          public HttpMethod getHttpMethod() {
              return HttpMethod.GET;
           }
           @Override
           public String getHost() {
               return "http://www.baidu.com";
           }
	}
	
Spring集成：

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration({"classpath:spring-pooling-test.xml"})
    public class ExampleSpring {
        @Autowired
        private Client client;
        @Test
        public void test(){
            Request request = new BaiduRequest();
            String html = client.doRequest(request,new SimpleStringAdapter());
            System.out.println(html);
        }
    }
    
spring-pooling-test.xml：

	<context:component-scan base-package="xgt.easy.webservice"/>
	<bean id="easyHttpClient" class="xgt.easy.webservice.httpclient.client.HttpSimpleClient"></bean>

4、
