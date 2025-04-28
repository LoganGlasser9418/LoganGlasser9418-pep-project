package Service;
import Model.Account;
import DAO.AccountDAO;
import DAO.MessageDAO;

import java.util.List;

public class AccountService {
    public AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }


    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    } 

    public Account addAccount(Account account) {
        return accountDAO.insertAccount(account);
    }

    public Account loginAccount(Account account) {
        return accountDAO.loginAccount(account);
    }
}
