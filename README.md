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
	
4、包和类介绍

	package:xgt.easy.webservice.adapters
	处理通过webservice返回结果的适配器，目前只有一个简单实现类SimpleStringAdapter，就是简单的把反馈结果转换成String反馈回来。

	package:xgt.easy.webservice.annotation
	对Request的所有注解都定义在这个包下面
	classes:
	@Encode ： 可以注解在Class和field上面，在将request转换成url的时候会对被该类注解的对象进行urlencode编码
	@Filter ： 注解在Field上，value默认是在Encode操作前执行，也可以通过Action.AFTER_ENCODE来实现在Encode后执行拦截。
	@Ignore ： 注解在Field上，被该类注解的field将不参与url的构造。
	@Order  ： 注解在Field上，用于feild在url上面的顺序。
	@Path   ： 注解在Field上，被注解的field将会被用于url的path上。
	@Rename ： 注解在Field上，必须设置一个value，使用这个新的value代替field的name参与url的构造。
	@Skip   ： 注解在Class和Filed上，默认值是如果field的值为空，则跳过这个Field，让其不参与url构造。
	@SupperAvailable ： 注解在Class上，如果被注解后，该类的所有父类的field都将参与url的构造。
	@UrlParameter    ： 注解在Field上，如果是post请求时，被该类注解的field将会被强行用于参与url构造，而不参与request body的构造。
	
	package:xgt.easy.webservice.client
	该包下面只有一个SimpleClient类，该类实现了Client接口，并实现了doRequest方法，该类是线程安全的，如果需要自定Client可以通过继承该类。

	package：xgt.easy.webservice.exception
	该包下只有一个异常类

	package：xgt.easy.webservice.handler
	classes：
	EncodeHandler：用于对被@Encode注解的field或者类进行编码的系统默认实现类。
	FilterHandler：抽象类，用于对field的编码前后的值进行拦截处理，需要开发者自己实现该类的方法，并自行配置到handerChain里面去。
	HandlerFactory：抽象类，如果需要自定义HandlerChain，可以继承该类，去实现自己的handlerchain逻辑。
	RenameHandler：用于对被@Rename注解的的field进行重命名处理。
	RequestHandler：HandlerChain的头
	RequestHandlerChainFactory：系统默认的HandlerChain工厂，继承HandlerFactory。
	SkipHandler：用于被@Skip注解的逻辑处理


	
	

