package mds.uvod;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("basic")
public class BasicController {
    @GetMapping
    public String testBasic(){
        return "<h2><b>Hello World!</b></h2>";
    }
    @GetMapping("list")
    public List<String> testList(){
        return List.of("hello","World");
    }
    @GetMapping("test1")
    public String testParam1(@RequestParam String name){
        return String.format("Hello %s", name);
    }

    @RequestMapping(value = "test2", method = {RequestMethod.POST, RequestMethod.GET})
    public String testParam2(@RequestParam(defaultValue = "user") String name){
        return String.format("Hello %s", name);
    }
    @GetMapping("test3")
    public String testParam3(@RequestParam(name = "n", defaultValue = "user") String name){
        return String.format("Hello %s", name);
    }
    @GetMapping("test4")
    public String testParam4(@RequestParam(defaultValue = "user") String name,
                             @RequestParam(defaultValue = "-1") int id){
        return String.format("Hello %s your id is %d", name, id);
    }
    @GetMapping("test5")
    public String testParam5(@RequestParam List<String> id){
        String w ="";
        for (String s : id) {
            w+= s+ "<br>";

        }
        return w;
    }
    @RequestMapping("form")
    public String helloForm(){
        String html =
                "<html>" +
                    "<body>" +
                        "<form 'method='post' action = 'test2'>" +
                            "<input type = 'text' name = 'name' />?"+
                        "<input type = 'submit' value = 'Hello' />?"+
                        "</form>" +
                    "</body>" +
                "</html>";
        return html;
    }
}
