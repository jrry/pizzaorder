package pl.bugle.pizzaorder.validation;

import java.util.Hashtable;
import java.util.regex.Pattern;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Jarosław Pawłowski
 */

public class EmailValidator implements ConstraintValidator<Email, String> {
    private Pattern pattern;
    private static final String EMAIL = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private Hashtable<String, String> ht;
    private DirContext dc;
    
    @Override
    public void initialize(Email constraintAnnotation) {
        pattern = Pattern.compile(EMAIL);
        ht = new Hashtable<String, String>();
        ht.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            message(context, "{nullEmail}");
            return false;
        }
        if (!pattern.matcher(value).matches()) {
            message(context, "{invalidEmail}");
            return false;
        }
        String hostname[];
        hostname = value.split("@", 2);
        return checkHostEmail(hostname[1]);
    }
 
    private void message(ConstraintValidatorContext context, String text) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(text).addConstraintViolation();
    }
    
    private boolean checkHostEmail(String hostname) {
        try {
            dc = new InitialDirContext(ht);
            Attributes attrs = dc.getAttributes(hostname, new String[]{"MX"});
            Attribute mx = attrs.get("MX");
            return (mx != null);
        } catch (NamingException ex) {
            return false;
        }
    }
}
