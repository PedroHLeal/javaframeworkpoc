package apis;

import annotations.Api;
import annotations.EndPoint;
import handlers.Response;

import java.io.NotActiveException;

@EndPoint
public class MyEndpoint {
    @Api(path = "test")
    public void teste() throws NotActiveException {
        throw new NotActiveException("erro");
    }

    @Api(path = "test2")
    public String teste2() {
        return "teste sem excecao";
    }
}
