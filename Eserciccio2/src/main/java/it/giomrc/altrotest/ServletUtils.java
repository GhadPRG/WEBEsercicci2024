package it.giomrc.altrotest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ServletUtils {

    @RequestMapping(value = "/views/**", method = {RequestMethod.GET, RequestMethod.POST})
    public String templateHandler(HttpServletRequest request) {
        String resource = request.getRequestURI().substring("/views/".length());
        System.out.println("Original resource: " + resource);

        // Controlla se la stringa contiene ".html"
        int htmlIndex = resource.indexOf(".html");
        if (htmlIndex == -1) {
            throw new IllegalArgumentException("URL does not contain a valid .html template");
        }

        resource = resource.substring(0, htmlIndex);
        System.out.println("Processed resource: " + resource);

        return resource;
    }
}
