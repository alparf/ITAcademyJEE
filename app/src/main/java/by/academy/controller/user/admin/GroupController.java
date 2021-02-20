package by.academy.controller.user.admin;

import by.academy.constant.ServletProperties;
import by.academy.controller.JsonController;
import by.academy.facade.GroupFacade;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/GroupController")
public class GroupController extends JsonController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(mapper.writeValueAsString(GroupFacade.getAll()));
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> props = getRequestParameters(req);
        if (null != props) {
            Long coachId = Long.parseLong(props.get(ServletProperties.COACH_ID));
            String groupName = props.get(ServletProperties.GROUP_NAME);
            GroupFacade.newGroup(groupName, coachId);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> props = getRequestParameters(req);
        if (null != props) {
            Long groupId = Long.parseLong(props.get(ServletProperties.GROUP_ID));
            GroupFacade.remove(groupId);
        }
    }
}
