package xgt.easy.webservice;

public abstract class Handler {
    private Handler next;
    public abstract Object handle(final Object request) throws IllegalAccessException;

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }
}
