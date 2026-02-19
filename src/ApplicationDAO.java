import java.sql.Connection;
import java.sql.PreparedStatement;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.List;
public class ApplicationDAO {
    // Inserting single job application
    public void addApplication(String company_name, String job_role, LocalDate applied_date, String status, String reply){
        String query = "INSERT INTO applications (company_name, job_role, applied_date, status, reply) VALUES (?,?,?,?,?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, company_name);
            ps.setString(2, job_role);
            ps.setDate(3, java.sql.Date.valueOf(applied_date));
            ps.setString(4, status);
            ps.setString(5, reply);
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
    // Deletng Single Job Application
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
            ps.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // Updating Single Job Application
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
    // View All Appications
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
                LocalDate date = rs.getDate("applied_date").toLocalDate();
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
    //this below method are for batch processing 
    public void addApplications(List<Application> apps){
        
       String query = """
    INSERT INTO applications
    (company_name, job_role, applied_date, status, reply)
    VALUES (?, ?, ?, ?, ?)
    """;

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(query);
            for(Application app : apps){
                ps.setString(1,app.getCompant_Name());
                ps.setString(2,app.getJob_Role());
                ps.setDate(3, java.sql.Date.valueOf(app.getApplied_Date()));
                ps.setString(4, app.getStatus());
                ps.setString(5,app.getReply());
                ps.addBatch();
            }
             ps.executeBatch();
            con.commit();
            System.out.println("Applications added Sucessfully");
            }catch(Exception e){
                try{
                    if(con != null){
                        con.rollback();
                        System.out.println("Transcation is RolledBack");
                    }
                }catch(Exception ex){
                        ex.printStackTrace();
                    }
                e.printStackTrace();
            }finally{
                try{
                    if(con != null){
                        con.setAutoCommit(true);
                        con.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    }
    // updating applications using batch processing 
    public void updateApplication(List<Application> apps){
        String query = """
                UPDATE applications
                SET status = ?
                WHERE id = ?
                """;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(query);
            for(Application app : apps){
                ps.setString(1, app.getStatus());
                ps.setInt(2, app.getId());
                ps.addBatch();
            }

            ps.executeBatch();
            con.commit();
            System.out.println("Uodated Succesfully!");
        }catch(Exception e){
            try{
                if(con != null){
                    con.rollback();
                    System.out.println("Transcation Stoped");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                if(ps != null) ps.close();
                if(con != null){
                    con.setAutoCommit(true);
                    con.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    // Delete using Batch processing 
    public void deleteApplication(List<Application> apps){
        String query = """
                DELETE FROM applications
                WHERE id = ?
                """;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(query);
            for(Application app : apps){
                ps.setInt(1, app.getId());
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            System.out.println("Deleted Succesfully");
        }catch(Exception e){
            try{
                if(con != null){
                    con.rollback();
                    System.out.println("Transcation Deleted");       
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                if(ps != null) ps.close();
                if(con != null){
                    con.setAutoCommit(true);
                    con.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
