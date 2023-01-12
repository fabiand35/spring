package org.openapitools.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.owasp.encoder.Encode;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.openapitools.model.Error;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiUtil {
    public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            res.getWriter().print(example);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static final ObjectMapper mapper = new ObjectMapper();

    public static void setErrorResponse(NativeWebRequest req, String string) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//            String message =  mapper.writeValueAsString(string);
            String message = toJson(string);
            res.getWriter().print(Encode.forHtmlContent(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \n");
        sb.append("\"id\": \"").append(message).append("\", \n");
        sb.append("\"name\": \"").append("\" \n");
        sb.append("}");
        return sb.toString();
    }
}
