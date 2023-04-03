import java.util.ArrayList;

public class patient {
    private int id;
    private String name;
    private String occupation;
    private String email;
    private String gender;
    private String tele;
    private String symp;
    private String blood;
    private String add;
    private String docseen;
    private String date;
    private double paid;
    
    public patient(int id, String name, String occupation, String email, String gender, String tele, String symp, String blood,
    String add,String docseen,double paid,String date){
        this.id = id;
        this.name = name;
        this.occupation  = occupation;
        this.email = email;
        this.gender = gender;
        this.tele = tele;
        this.symp  =symp;
        this.date = date; 
        this.blood = blood;
        this.add = add;
        this.docseen = docseen;
        this.paid = paid;
        get_sym();
    }

    public int getid()
    {
        return this.id;
    }

    public String getname()
    {
        return this.name;
    }

    public String get_group()
    {
        return this.blood;
    }

    public String getgender()
    {
        return this.gender;
    }
    public String email()
    {
        return this.email;
    }

    public String tele()
    {
        return this.tele;
    }

    public String get_docseen()
    {
        return this.docseen;
    }

    public String getoc()
    {
        return this.occupation;
    }

    public double get_Paid()
    {
        return this.paid;
    }

    public String get_Date()
    {
        return this.date;
    }

    public String get_address()
    {
        return this.add;
    }

    public String get_tele()
    {
        return this.tele;
    }

    public String[] get_sym()
    {

        
        String[] arrOfStr = this.symp.split(",");
        

    

        return arrOfStr;
    }

    public String get_symm()
    {
        return symp;
    }
    
    
    
}
