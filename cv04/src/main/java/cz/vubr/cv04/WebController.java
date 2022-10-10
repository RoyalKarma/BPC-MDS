package cz.vubr.cv04;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {


    @GetMapping("myself")
    public String myself(){
        return "index";
    }
@GetMapping("bob")
    public String bob(Model model){
        model.addAttribute("name", "bob");
    return "template";
    }
@GetMapping("alice")
    public String alice(Model model){
        model.addAttribute("name", "alice");
    return "template";
    }

}
