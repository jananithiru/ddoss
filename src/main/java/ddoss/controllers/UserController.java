package ddoss.controllers;

import ddoss.Content;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import ddoss.models.User;
import ddoss.models.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.android.gcm.server.*;

/**
 * Class UserController
 */
@Controller
public class UserController {

	public static final String API = "AIzaSyAoIOcTtiYWAbnu08ZZ-iH34831Slblm08";

	private ConcurrentHashMap<String, String> mapStore = new ConcurrentHashMap<String, String>();

	@Autowired
	private UserDao _userDao;

	/**
	 * Create a new user with an auto-generated id and regid and ipaddr as
	 * passed values.
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	public String create(String regid, String ipaddr) {
		try {
			// User user = new User(ipaddr, regid);
			// _userDao.create(user);
			
			if (ipaddr == null || regid == null || 
					ipaddr.isEmpty() || regid.isEmpty())
				return "Error creating the user! ";
			
			mapStore.put(ipaddr, regid);

		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created!";
	}

	/**
	 	* Retrieve the regid for the victim user provided the IP
	 */
	@RequestMapping(value = "/notifyUser")
	@ResponseBody
	public String notifyUser(String ipaddr) {
		String regid;
		try {
			// User user = _userDao.getByIpAddr(ipaddr);
			// regid = String.valueOf(user.getRegid());

			regid = mapStore.containsKey(ipaddr) ? mapStore.get(ipaddr) : null;

			if (regid == null) {
				return "User not found with IP: " + ipaddr;
			}

			Content content = new Content();
			Sender sender = new Sender(API);

			content.addRegId(regid);
			content.addContent("DDOSS", "DEMO");

			Message msg = new Message.Builder()
					.addData("message", "Stop Hacking!")
					.addData("port", "6000").build();

			Result result = sender.send(msg, regid, 3);

			System.out.println("RegId:" + regid + " IP:" + ipaddr + "\n");

			System.out.println("Result: " + result.toString());

		} catch (Exception ex) {
			return "User not found: " + ex.toString();
		}
		return "The user regid is: " + regid;
	}

	// /**
	// * Delete the user with the passed id.
	// */
	// @RequestMapping(value = "/delete")
	// @ResponseBody
	// public String delete(long id) {
	// try {
	// User user = new User(id);
	// _userDao.delete(user);
	// } catch (Exception ex) {
	// return "Error deleting the user: " + ex.toString();
	// }
	// return "User succesfully deleted!";
	// }

	// /**
	// * Update the regid and the ipaddr for the user indentified by the passed
	// * id.
	// */
	//
	// @RequestMapping(value = "/update")
	// @ResponseBody
	// public String updateipaddr(long id, String regid, String ipaddr) {
	// try {
	// User user = _userDao.getById(id);
	// user.setRegid(regid);
	// user.setIpaddr(ipaddr);
	// _userDao.update(user);
	// } catch (Exception ex) {
	// return "Error updating the user: " + ex.toString();
	// }
	// return "User succesfully updated!";
	// }

} // class UserController
