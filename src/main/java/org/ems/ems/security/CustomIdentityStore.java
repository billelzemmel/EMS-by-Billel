package org.ems.ems.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import org.ems.ems.models.User;
import org.ems.ems.services.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Set;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {

    @Inject
    private UserService userService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        System.out.println("validate method");
        UsernamePasswordCredential usernamePassword = (UsernamePasswordCredential) credential;
        User user = userService.getUserByEmail(usernamePassword.getCaller());
        if (user == null) {
            System.out.println("User not found");
        }else {
            System.out.println("User found --> "+user.getEmail());
        }
        //System.out.println("User's stored hash: " + user.getPassword());
        //System.out.println("Input password: " + usernamePassword.getPasswordAsString());
        System.out.println("Password validation result: " + BCrypt.checkpw(usernamePassword.getPasswordAsString(), user.getPassword()));

        if (user != null && BCrypt.checkpw(usernamePassword.getPasswordAsString(), user.getPassword())) {
            System.out.println("Authentication OK : " + usernamePassword.getCaller());
            return new CredentialValidationResult(user.getEmail(), Set.of("USER")); // Assign role
        }
        System.out.println("Authentication failed " + usernamePassword.getCaller());
//        if (user == null) {
//            System.out.println("Authentication failed: User not found for email " + usernamePassword.getCaller());
//        } else {
//            System.out.println("User found: " + user.getEmail());
//            boolean isPasswordValid = BCrypt.checkpw(usernamePassword.getPasswordAsString(), user.getPassword());
//            System.out.println("Password validation result for " + usernamePassword.getCaller() + ": " + isPasswordValid);
//        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}

