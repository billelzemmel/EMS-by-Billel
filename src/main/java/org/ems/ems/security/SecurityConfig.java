package org.ems.ems.security;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ems.ems.models.User;
import org.ems.ems.services.UserService;

@AutoApplySession // Maintain session after login
@LoginToContinue(loginPage = "/login", errorPage = "/views/login-error.jsp")
@ApplicationScoped
public class SecurityConfig implements HttpAuthenticationMechanism {

    @Inject
    private UserService userService;

    @Inject
    private  CustomIdentityStore identityStoreHandler;


    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) throws AuthenticationException {


        if (context.isAuthenticationRequest()) {
            System.out.println("validateRequest called  --> isAuthenticationRequest");
            String username = request.getParameter("email");
            String password = request.getParameter("password");
            System.out.println("Authentication attempt with email: " + username);

            CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(username, password));

            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
                System.out.println("Authentication successful for: " + username);
                User user = userService.getUserByEmail(username);
                System.out.println("User set in session: " + user.getName());
                request.getSession().setAttribute("loggedInUser", user);
                return AuthenticationStatus.SUCCESS;
            } else {
                System.out.println("Authentication failed for: " + username);
                return AuthenticationStatus.SEND_FAILURE;
            }
        }

        return context.doNothing();
    }
}
