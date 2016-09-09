package com.facade;
 
import javax.ejb.EJB;
import javax.ejb.Stateless;
 
import com.dao.UserDAO;
import com.model.User;
 
@Stateless
public class UserFacadeImp implements UserFacade {
 
    @EJB
    private UserDAO userDAO;
 
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }
    
    @Override
    public User update(User user) {
        validate(user);
 
        return userDAO.update(user);
    }
    
    private void validate(User user){
        String error = null;
 
        if (user == null){
            error = "user == null";
        }
 
        if (error != null){
            throw new IllegalArgumentException("Validation failed: " + error);
        }
    }
    
}