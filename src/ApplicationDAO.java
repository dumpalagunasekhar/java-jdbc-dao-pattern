import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
public class ApplicationDAO {
    public void addApplication(String company_name, String job_role, Date applied_date, String status, String reply){
        String query = "INSERT INTO applications (company_name, job_role, applied_date, status, reply) VALUES(?,?,?,?,?)";
    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,company_name);
        ps.setString(2, job_role);
        ps.setDate(3,applied_date);
        ps.setString(4, status);
        ps.setString(5,reply);

        int rows = ps.executeUpdate();

        if(rows > 0){
            System.out.println("Application added successfully!");
        }
        ps.close();
        con.close();
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    public void deleteApplication(int id){
        String query = "DELETE FROM applications WHERE id = ?";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if(rows > 0){
                System.out.println("Applaication Deleted Successfully!");
            }else{
                System.out.println("No Record Found");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateApplication(String status,int id){
        String query = """
                UPDATE applications
                SET status = ?
                WHERE id = ?
                """;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if(rows > 0){
                System.out.println("Application updated Successfuly!");
            }else{
                System.out.println("No Record Found");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void viewallApplications(){
        String query = """
                SELECT *
                FROM applications
                """;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String company = rs.getString("company_name");
                String role = rs.getString("job_role");
                Date date = rs.getDate("applied_date");
                String status = rs.getString("status");
                String reply = rs.getString("reply");
                System.out.println(id + " | " + company + " | " + role + " | " + date + "|" + status + "|" + reply);
            }
             rs.close();
             ps.close();
             con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
