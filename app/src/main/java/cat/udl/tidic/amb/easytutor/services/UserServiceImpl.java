package cat.udl.tidic.amb.easytutor.services;

import cat.udl.tidic.amb.easytutor.dao.IUserDAO;
import cat.udl.tidic.amb.easytutor.dao.UserDAOImpl;
import retrofit2.Call;

public class UserServiceImpl implements UserServiceI {

    private IUserDAO userDAO = new UserDAOImpl();


    @Override
    public Call<Void> createTokenUser(String Auth){
        return userDAO.createTokenUser(Auth);
    }
}
