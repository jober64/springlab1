package hello;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InfoController {
	
	@Autowired
	ServletContext context;

    //@RequestMapping("/greeting")
    @RequestMapping("/")
    public String greeting(Model model) {
    //public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", context.getServerInfo());
        
        return "greeting";
        //return context.getServerInfo();
    }

}
