package admin;

import ejb.Database;
import entity.Users;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import messages.Message;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@RequestScoped
public class UserEdit implements Serializable {
    @EJB
    private Database db;

    private List<Users> users;
    private List<Users> filteredUsers;
    private Users selectedUser;
    private final SelectItem[] levelOptions;
    private final static String[] levels;
    private Message msg;
    
    static {
        levels = new String[2];
        levels[0] = "ADMIN";
        levels[1] = "USER";
    }
    
    public UserEdit() {
        users = null;
        msg = new Message();
        selectedUser = new Users();
        levelOptions = createFilterOptions(levels);
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public SelectItem[] getLevelOptions() {
        return levelOptions;
    }

    private SelectItem[] createFilterOptions(String[] data)  {  
        SelectItem[] options = new SelectItem[data.length + 1];  
        options[0] = new SelectItem("", "Wybierz"); 
        for (int i = 0; i < data.length; i++) {
            options[i + 1] = new SelectItem(data[i], data[i]);  
        }
        return options;  
    }

    public List<Users> getUsers() {
        if (users == null) {
            users = db.getUsers();
        }
        return users;
    }

    public List<Users> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<Users> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }
    
    public void update() {
        db.updateUser(selectedUser);
        users = db.getUsers();
    }
    
    public void remove() {
        if (selectedUser.getUsername().compareTo("Admin") == 0) {
            msg.setError("GlobalAdminDelete");
        } else {
            db.removeUser(selectedUser);
            users = db.getUsers();
        }
    }
}
