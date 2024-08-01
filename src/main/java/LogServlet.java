import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logs")
public class LogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String LOG_FILE_PATH = "logs/app.log"; // Adjust path if necessary

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        try {
            // Read the log file content
            String logContent = new String(Files.readAllBytes(Paths.get(LOG_FILE_PATH)));
            out.write(logContent);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("Error reading log file.");
        } finally {
            out.close();
        }
    }
}
