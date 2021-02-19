package by.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class JsonController extends HttpServlet {
    protected Map<String, String> getRequestParameters(HttpServletRequest req) {
        Map<String, String> props = null;
        try (BufferedReader br = req.getReader()) {
            StringBuilder body = new StringBuilder();
            while (br.ready()) {
                body.append(br.readLine());
            }
            ObjectMapper mapper = new ObjectMapper();
            props = mapper.readValue(body.toString(), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
