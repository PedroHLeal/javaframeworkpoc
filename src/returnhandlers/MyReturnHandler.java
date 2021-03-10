package returnhandlers;

import annotations.Provider;
import apis.MyTestDto;
import handlers.Response;
import handlers.ReturnHandler;

import java.io.NotActiveException;

@Provider
public class MyReturnHandler implements ReturnHandler<NotActiveException> {
    @Override
    public Response transform(NotActiveException e) {
        Response r = new Response();
        r.message = "Mensagem do transformador de mensagem: " + e.getMessage();
        r.code = "200";
        return r;
    }
}
