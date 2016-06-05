import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import org.littleshoot.proxy.*;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;


/**
 * Created by Dell on 2016/4/26.
 */
public class Test {
    public static int count=0;
    public static  void  main(String args[])
    {


        HttpFiltersSource filtersSource2 = new HttpFiltersSourceAdapter() {
            @Override
            public HttpFilters filterRequest(HttpRequest originalRequest) {
                return new HttpFiltersAdapter(originalRequest) {
                    @Override
                    public HttpResponse clientToProxyRequest(HttpObject httpObject) {
                        if (httpObject instanceof HttpRequest) {
                            HttpRequest req = (HttpRequest) httpObject;


                            String url = req.getUri();
                            System.out.println(url);
//                            if(url.contains("lyrs=s")){
//                                String aurl = url.replace("lyrs=s","lyrs=y");
//                                req.setUri( aurl);
//                                System.out.println(aurl);
//                            }

                        }
                        return super.clientToProxyRequest(httpObject);
                    }
                };
            }
        };


        DefaultHttpProxyServer.bootstrap()
                .withPort(8800)

// .withManInTheMiddle(new SelfSignedMitmManager())
                .withFiltersSource(filtersSource2)
                .start();



    }

}
