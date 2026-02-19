import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class App {
    public static void main(String[] args) throws Exception {
        ApplicationDAO dao = new ApplicationDAO();
        // Date date = Date.valueOf("2026-02-17");
        // dao.addApplication("IBM", "SDE-Intern", date, "Interview", "Waiting for response");
        // dao.deleteApplication(2);
        // dao.updateApplication("Under-Review", 1);
        // dao.viewallApplications();
        List<Application> apps = new ArrayList<>();
        apps.add(new Application("TCS","FRESHER",LocalDate.now(),"Applied","No-Reply"));
        dao.addApplications(apps);
    }
}
