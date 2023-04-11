package Security;
import java.util.ArrayList;

import Admin.admin;

public class userLogin {
    protected String username;
    protected String password;
    public userLogin(String userTest, String passTest)
    {
        this.username = userTest;
        this.password = passTest;
    }
    
    public boolean[] authenticate(ArrayList<admin>adminList)
    {
            boolean coruse = false;
            boolean corpass = false;
            for(admin ad : adminList)
            {
                if (ad.get_User().equals(username))
                {
                    coruse = true;
                    if(ad.get_Password().equals(password))
                    {
                        corpass = true;
                        break;
                
                    }
                } 
    
            }
            boolean[] result={coruse,corpass};
            return result;
    }
}
