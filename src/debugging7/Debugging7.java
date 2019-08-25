/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debugging7;

import CONSTANTS.Constants;
import DAO.Client;
import DAO.DetailOrder;
import DAO.Order;
import java.io.IOException;

/**
 *
 * @author 1895314
 */
public class Debugging7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
            //Client 
            Client cl;
          /* cl = new Client();
            cl.insertClient(Constants.NO_CLIENT, Constants.NAME_CLIENT, Constants.TELE_CLIENT);
            cl = new Client(); 
            cl.updateClient(Constants.NEW_NAME_CLIENT, Constants.NO_CLIENT);
            cl = new Client();
            cl.deleteClient(Constants.ID_CLIENT);
          */  cl = new Client();
           cl.listClient();
         /*   cl = new Client();
            cl.singleClient(Constants.NO_CLIENT); */
            
          /*  //Order
            Order od = new Order();
            od.insertOrder(Constants.NO_ORDER, Constants.DATE_ORDER, Constants.NO_CLIENT);
            od.updateOrder(Constants.NO_ORDER, Constants.NEW_DATE_ORDER);
            od.deleteOrder(Constants.NO_ORDER);
            od.listOrder();
            od.singleOrder(Constants.NO_ORDER);
            
            //DetailOrder
            DetailOrder ddo = new DetailOrder();
            ddo.insertDetailOrder(Constants.NO_DETAIL_ORDER, Constants.NO_ITEM_DETAILORDER, Constants.QUANTITY_DO);
            */
    }   
}