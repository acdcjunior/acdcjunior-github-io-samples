package io.github.acdcjunior.jsfexample.web.authentication;

import io.github.acdcjunior.jsfexample.domain.User;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>This class manages the login page for JSF.<br />
 * It allows logging in and out, as wel as retrieving the current user (if any) and more.</p>
 *
 * <p>It also works as a substitute for the <code>j_security_check/j_username/j_password</code> form (which is buggy!). Having
 * this bean, our login form can be pure JSF, allowing a more fine-grained control and all (such as embedding
 * validation/error messages in the form itself; custom {@link User} instances building and so on).</p>
 */
@ManagedBean
@ViewScoped
public class LoginManager {

    private static final String HOME_PAGE = "/";
    private static final String PAGE_AFTER_LOGOUT = HOME_PAGE; // Another good option is the login page back again

    private static final String SESSION_USER_VARIABLE_NAME = "user";

    private String username;
    private String password;
    private String forwardUrl;


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @PostConstruct
    public void init() {
        this.forwardUrl = extractRequestedUrlBeforeLogin();
    }

    private String extractRequestedUrlBeforeLogin() {
        ExternalContext externalContext = externalContext();
        String requestedUrl = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
        if (requestedUrl == null) {
            return externalContext.getRequestContextPath() + HOME_PAGE;
        }
        String queryString = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
        return requestedUrl + (queryString == null ? "" : "?" + queryString);
    }

    private ExternalContext externalContext() {
        return facesContext().getExternalContext();
    }

    private FacesContext facesContext() {
        return FacesContext.getCurrentInstance();
    }


    /**
     * Performs user login accordingly to the username/password set.
     *
     * @throws IOException from {@link ExternalContext#redirect(String)}
     */
    public void login() throws IOException {
        ExternalContext externalContext = externalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(username, password);
            externalContext.getSessionMap().put(SESSION_USER_VARIABLE_NAME, new User(username));
            externalContext.redirect(forwardUrl);
        } catch (ServletException e) {
            /*
             * The ServletException is thrown if the configured login mechanism does not support
             * username password authentication, or if a non-null caller identity had already been
             * established (prior to the call to login), or if validation of the provided username and password fails.
             */
            String loginErrorMessage = e.getLocalizedMessage();
            facesContext().addMessage(null, new FacesMessage(loginErrorMessage));
        }
    }


    /**
     * Invalidates the current session, effectively logging out the current user.
     *
     * @throws IOException from {@link ExternalContext#redirect(String)}
     */
    public void logout() throws IOException {
        ExternalContext externalContext = externalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + PAGE_AFTER_LOGOUT);
    }


    /**
     * Makes the current logged in available through EL: #{loginManager.user}. Notice as the user is also placed in
     * the session map (), it also is available through #{user}.
     *
     * @return The currently logged in {@link User}, or {@code null} if no user is logged in.
     */
    public User getUser() {
        FacesContext context = facesContext();
        ExternalContext externalContext = context.getExternalContext();
        return (User) externalContext.getSessionMap().get("user");
    }


    /**
     * Verifies if there is a currently logged in user.
     *
     * @return {@code true} if there's a logged in {@link User}, {@code false} otherwise.
     */
    public boolean isUserLoggedIn() {
        return getUser() != null;
    }


    /**
     * Verifies if the currently logged in user, if exists, is in the given ROLE.
     *
     * @param role The ROLE to verify if the user has.
     * @return {@code true} if the user is logged in and has the given ROLE. {@code false} otherwise.
     */
    public boolean isUserInRole(String role) {
        FacesContext context = facesContext();
        ExternalContext externalContext = context.getExternalContext();
        return externalContext.isUserInRole(role);
    }

}