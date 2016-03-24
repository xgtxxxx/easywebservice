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
	<bean id="easyHttpClient" class="HttpSimpleClient"></bean>
	
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

	package：xgt.easy.webservice.httpclient
	httpclient的客户端实现。

	
5、扩展ResponseAdapter
	
	//该类是一个对webservice反馈结果的简单适配器，实际应用时可以把返回的数据类型转换成实际的应用对象。
	public class SimpleStringAdapter implements ResponseAdapter<String> {
	    public String convertTo(final ResponseInfo f) {
	        return StringUtils.toString(f.getBody());
	    }
	}

6、扩展RequestHandlerChainFactory

    public static final String SKIP_HANDLER = "skip_handler";
    public static final String FILTER_HANDLER = "filter_handler";
    public static final String ENCODE_HANDLER = "encode_handler";
    public static final String RENAME_HANDLER = "rename_handler";

    private Map<String,Handler> handlers = new HashMap<String,Handler>();
    
    public Handler build() {
        synchronized (lock){
            if(first==null){
                buildChain();
            }
        }
        return first;
    }

    private void buildChain(){
        first = new RequestHandler();
        Handler next = buildNext(first, handlers.get(SKIP_HANDLER)==null?new SkipHandler():handlers.get(SKIP_HANDLER));
        next = buildNext(next, handlers.get(FILTER_HANDLER));
        next = buildNext(next,handlers.get(ENCODE_HANDLER)==null?new EncodeHandler():handlers.get(ENCODE_HANDLER));
        next = buildNext(next,handlers.get(FILTER_HANDLER));
        buildNext(next,handlers.get(RENAME_HANDLER)==null?new RenameHandler():handlers.get(RENAME_HANDLER));
    }
    
    //在实际应用当中如果默认的一些handler无法满足需求，则可以通过RequestHandlerChainFactory的setHandlers和addHandler两个方法，把自定的
    //handler付给相应的key值，当然也可以用Spring的ioc对handlers进行注入。自定义Handler必须继承Handler基类。
    
    //如果实际应用中，通过RequestHandlerChainFactory构造的handlerchain无法满足需求时，你也可以自己去扩展HandlerFactory类，实现自己的
    //HandlerChain构造逻辑
    
7、Request扩展

	本应用中已经有两个类扩展了Request，分别为GetRequest和PostRequest，这两个类分别执行get请求和post请求。一般get请求和post请求就直接继承这两个类就可以了，一下是两个Request实例：
	
	//该类继承GetRequest请求，假如你设置的host=http://localhost:8080,ctx=/myapp,那么构造出来的url=http://localhost:8080/myapp/{cdin}/us?id=CustomerId&tenantId=tenantId123456
	
	public class etailRequest extends GetRequest {
	    @Path
	    private String cdin;
	    @Path
	    private String country = "us";
	    @Rename("id")
	    private String hashedCustomerId = "CustomerId";
	    private String tenantId = "tenantId123456";
	
	    public String getCdin() {
	        return cdin;
	    }
	
	    public void setCdin(String cdin) {
	        this.cdin = cdin;
	    }
	}
	
	//该类是一个post请求的request，如果host=http://localhost:8080,ctx=/myapp，那么url=http://localhost:8080/myapp?cdin={cdin}
	//priceSold和taxAmount分别是form data。Post提交的ContentType默认为application/x-www-form-urlencoded，你同样可以通过PostRequest的setApplicationType方法设置成你想要的请求类型，目前系统只提供了HttpClient对application/x-www-form-urlencoded和application/json的处理逻辑，其他类型需要你自己实现处理逻辑，如果采用HttpClient作为webservice请求框架，则可以直接继承RequestInfoHandler，并将该类的实例注入到HttpSimpleEntityAdapter的handlers里面。
	
	@SupperAvailable
	@Skip
	public class PurchaseRequest extends PostRequest {
	    @UrlParameter
	    private String cdin;
	    private Integer priceSold;
	    private Integer taxAmount;
	    //省略getter和setter
	}
	

8、扩展Client，HttpClient的实现

	本系统提供了一套简单的HttpClient的实现，Client <- SimpleClient <- HttpAbstractClient <- HttpSimpleClient
	
	HttpSimpleClient只是实现了简单的HttpClient的Get请求和Post请求，如果有更多的请求需要实现，则可以扩展HttpAbstractClient类，目前系统只提供了Post，Get，Put和Delete四种方式请求，如果需要更多的请求，则需要你去继承Client，对Client进行深度定制。
	
	//HttpAbstractClient该类的中可以注入自定义的HttpAbstractClientFactory和EntityAdapter，当然如果没有注入任何值，则系统默认使用HttpClientFactory和HttpSimpleEntityAdapter的实例。
	public abstract class HttpAbstractClient extends SimpleClient {
	
	    private HttpAbstractClientFactory httpClientFactory;
	
	    private EntityAdapter entityAdapter;
	    
	    ...
	}
	
	//HttpClientFactory主要是对构造的CloseableHttpClient进行一些基本的配置，如果给定的配置不够用，则可以自己去扩展HttpAbstractClientFactory类
	public final class HttpClientFactory extends HttpAbstractClientFactory {
	    private int socketTimeout;
	
	    private int connectionTimeout;
	
	    private int requestTimeout;
	
	    private int maxConnections;
	
	    private int defaultMaxConnections;
	
	    private HttpClientBuilder httpClientBuilder;
	
	    private Object lock = new Object();
	
	    public CloseableHttpClient build() {
	        synchronized (lock){
	            if(httpClientBuilder==null){
	                init();
	            }
	        }
	        return httpClientBuilder.build();
	    }
	    ...
	}

	//该类是将PostRequest的请求体转换成相应的ContentType类型，参见7。
	public class HttpSimpleEntityAdapter extends EntityAdapter {
	
	    private Map<String,RequestInfoHandler> handlers = new HashMap<String, RequestInfoHandler>();
	
	    public HttpEntity convertTo(RequestInfo from) {
	        return getHandler(from.getApplicationType()).convertTo(from.getFormData());
	    }
	    ...
	}

9、其他框架对Client的扩展，这里给出一个简单的SpringTemplete实现。

	public class SpringTemplateClient extends SimpleClient {
	    @Override
	    public ResponseInfo doGet(RequestInfo info) throws EasyWebserviceException {
	        RestTemplate restClient = new RestTemplate();
	        ResponseInfo response = new ResponseInfo();
	
	        final HttpHeaders headers = new HttpHeaders();
	        final List<ParameterPair> items = new Authentication().getAuthenticationHeaders();
	        for (final ParameterPair item : items) {
	            headers.add(item.getKey(), item.getStringValue());
	        }
	        final HttpEntity<String> entity = new HttpEntity<String>(headers);
	
	
	        response.setBody(restClient.exchange(info.getRequestUrl(), org.springframework.http.HttpMethod.GET, entity, String.class));
	        return response;
	    }
	
	    @Override
	    public ResponseInfo doPost(RequestInfo info) throws EasyWebserviceException {
	        return null;
	    }
	}
	
以上仅供参考，本应用还有很多需要改进的地方，如果你对本应用有兴趣，希望你能提出宝贵的改进意见。
