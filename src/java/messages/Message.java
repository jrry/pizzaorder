package messages;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jarosław Pawłowski
 */

public class Message {
    FacesContext context = FacesContext.getCurrentInstance();
    ResourceBundle resourceBundle = context.getApplication().getResourceBundle(context, "msgs");
    
    public void setInfo(String info) {
        FacesMessage message = new FacesMessage(resourceBundle.getString(info));
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, message);
    }
    
    public void setError(String error) {
        FacesMessage message = new FacesMessage(resourceBundle.getString(error));
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

}
