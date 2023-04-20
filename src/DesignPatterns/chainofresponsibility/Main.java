package DesignPatterns.chainofresponsibility;

enum RequestType {
    TYPE1, TYPE2
}
class Request {
    public RequestType type;
    public String data;

    public Request(RequestType type, String data) {
        this.type = type;
        this.data = data;
    }
}
abstract class RequestHandler {

    private RequestHandler nextHandler;
    public void handleRequest(Request request){
        if(canHandleRequest(request)){
            processRequest(request);
        }else if(this.nextHandler!=null){
            this.nextHandler.handleRequest(request);
        }else{
            System.out.println("Cannot Handle");
        }
    }

    public void setNextHandler(RequestHandler handler){
        nextHandler = handler;
    }

    protected abstract Boolean canHandleRequest(Request request);
    protected abstract void processRequest(Request request);

}

class Handler1 extends RequestHandler {

    @Override
    protected Boolean canHandleRequest(Request request) {
        return request.type.equals(RequestType.TYPE1);
    }

    @Override
    protected void processRequest(Request request) {
        System.out.println("Request of type 1 processed");
    }
}

class Handler2 extends RequestHandler {

    @Override
    protected Boolean canHandleRequest(Request request) {
        return request.type.equals(RequestType.TYPE2);
    }

    @Override
    protected void processRequest(Request request) {
        System.out.println("Request of type 2 processed");
    }
}

public class Main {

    private static RequestHandler getChainOfHandlers(){

        RequestHandler handler1 = new Handler1();
        RequestHandler handler2 = new Handler2();

        handler1.setNextHandler(handler2);

        return handler1;
    }

    public static void main(String[] args) {
        getChainOfHandlers().handleRequest(new Request(RequestType.TYPE2,"data"));
    }
}
