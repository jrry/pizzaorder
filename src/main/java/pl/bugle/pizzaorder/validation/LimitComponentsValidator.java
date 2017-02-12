package pl.bugle.pizzaorder.validation;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;

/**
 *
 * @author Jarosław Pawłowski
 */

@FacesValidator("limitComponentsValidator")
public class LimitComponentsValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Integer minLimit = Integer.parseInt((String) component.getAttributes().get("minLimit"));
        ResourceBundle resourceBundle = context.getApplication().getResourceBundle(context, "msgs");
        SelectCheckboxMenu myComponent = (SelectCheckboxMenu) component;
        if (((String[]) myComponent.getSubmittedValue()).length < minLimit) {
            FacesMessage msg = new FacesMessage(MessageFormat.format(resourceBundle.getString("SelectValidator"), minLimit));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
