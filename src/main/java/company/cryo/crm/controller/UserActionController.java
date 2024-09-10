package company.cryo.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import company.cryo.crm.service.UserActionService;
import company.cryo.crm.model.UserAction;

import java.util.List;

@Controller
public class UserActionController {

    private final UserActionService userActionService;

    @Autowired
    public UserActionController(UserActionService userActionService) {
        this.userActionService = userActionService;
    }

    @PreAuthorize("hasAuthority('RESPONSABLE')")
    @GetMapping("/listUserActions")
    public String listUserActions(Model model) {
    	try {
    		List<UserAction> userActions = userActionService.getAllUserActions();
    		 model.addAttribute("userActions", userActions);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/errorPage";
		}
        
       
        return "listUserActions";
    }

    @PreAuthorize("hasAuthority('RESPONSABLE')")
    @GetMapping("/userAction/{id}")
    public String viewUserAction(@PathVariable("id") Integer id, Model model) {
    	
    	try {
    		UserAction userAction = userActionService.getUserActionById(id).orElse(null);
    		if (userAction == null) {
                model.addAttribute("message", "User action not found.");
                return "redirect:/listUserActions";
            }
    		  model.addAttribute("userAction", userAction);
    	       return "/viewUserAction";
    	       
		} catch (Exception e) {
			model.addAttribute("message", "ERROR: "+e.getMessage());
			return "redirect:/listUserActions";
		}
      
    }
}
