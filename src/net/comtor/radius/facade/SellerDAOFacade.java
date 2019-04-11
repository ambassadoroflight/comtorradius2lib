package net.comtor.radius.facade;

import net.comtor.aaa.helper.PasswordHelper2;
import net.comtor.radius.element.Seller;
import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;

/**
 *
 * @author juandiego@comtor.net
 * @since Jan 24, 2018
 */
public class SellerDAOFacade extends ComtorDaoElementLogicFacade<Seller, String> {

    @Override
    public void create(Seller seller) throws ComtorDaoException {
        encryptPassword(seller);

        super.create(seller);
    }

    @Override
    public void edit(Seller seller) throws ComtorDaoException {
        editPassword(seller);

        super.edit(seller);
    }

    private void encryptPassword(Seller seller) throws ComtorDaoException {
        final String salt = PasswordHelper2.getHelper().createSalt();

        seller.setSalt(salt);
        seller.setPassword(PasswordHelper2.getHelper().encryption(seller.getPassword(), salt));
    }

    private void editPassword(Seller seller) throws ComtorDaoException {
        if (seller.getPassword().equals("nochange")) {
            Seller currentCustomer = find(seller.getLogin());
            seller.setPassword(currentCustomer.getPassword());
            seller.setSalt(currentCustomer.getSalt());

            return;
        }

        encryptPassword(seller);
    }

    @Deprecated
    public String validateAccess(String loginSeller, String passwd) throws ComtorDaoException {

        Seller seller = find(loginSeller);
        if (seller == null) {
            return "Usuario no existe";
        }

        if (!seller.getPassword().equals(passwd)) {
            return "Contrasena invalida";
        }

        return null;
    }

}
