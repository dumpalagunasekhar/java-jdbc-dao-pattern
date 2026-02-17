// import java.sql.Date;s

public class App {
    public static void main(String[] args) throws Exception {
        ApplicationDAO dao = new ApplicationDAO();
        // Date date = Date.valueOf("2026-02-17");
        // dao.addApplication("IBM", "SDE-Intern", date, "Interview", "Waiting for response");
        // dao.deleteApplication(2);
        // dao.updateApplication("Under-Review", 1);
        dao.viewallApplications();
    }
}
