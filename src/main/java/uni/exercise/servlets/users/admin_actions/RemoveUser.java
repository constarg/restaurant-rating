package uni.exercise.servlets.users.admin_actions;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RemoveUser")
public class RemoveUser extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();
        String username = request.getParameter("username");

        try {
            if (queryManager.getFromDatabase(
                    username,
                    Queries.CHECK_IF_USER_IS_ADMIN.getQuery(),
                    dbConnection.getConnection(),
                    "app_admin",
                    "username"
            ).isEmpty()) {
                response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
            } else {
                queryManager.removeFromDB(
                        username,
                        Queries.REMOVE_USER.getQuery(),
                        dbConnection.getConnection(),
                        "rest_user"
                );
                response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
            }
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}
