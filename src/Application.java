import java.time.LocalDate;

public class Application {
    private int id;
    private String company_name;
    private String job_role;
    private LocalDate applied_date;
    private String status;
    private String reply;
    // The below constructor thsat is for insert with out id
    public Application(String company_name,String job_role,LocalDate applied_date,String status,String reply){
        this.company_name = company_name;
        this.job_role = job_role;
        this.applied_date = applied_date;
        this.status = status;
        this.reply = reply;
    }
    // the below constructor is for update and delte rows with help of id
    public Application(int id,String company_name,String job_role,LocalDate applied_date,String status,String reply){
        this.id = id;
        this.company_name = company_name;
        this.job_role = job_role;
        this.applied_date = applied_date;
        this.status = status;
        this.reply = reply;
    }
    // Getter Funcations
    public int getId() { return id;}
    public String getCompant_Name(){return company_name;}
    public String getJob_Role(){return job_role;}
    public LocalDate getApplied_Date(){return applied_date;}
    public String getStatus(){return status;}
    public String getReply(){return reply;}
}
