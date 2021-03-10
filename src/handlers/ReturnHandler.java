package handlers;

public interface ReturnHandler<T> {
    public Response transform(T t);
}
