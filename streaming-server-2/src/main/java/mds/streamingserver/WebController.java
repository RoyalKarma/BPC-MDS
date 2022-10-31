package mds.streamingserver;

import mds.streamingserver.components.MyResourceHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;
import java.io.File;
import java.io.IOException;

@Controller
public class WebController {

    private MyResourceHttpRequestHandler handler;
    @Autowired
    public WebController(MyResourceHttpRequestHandler handler){
        this.handler=handler;
    }
    private final static File MP4_FILE = new File("D:\\MDS\\files\\ffmpeg\\bbb_1080p.mp4");

    @GetMapping("video")
    public String video(){
        return "videoMP4";
    }
    @GetMapping(path = "file", produces = "video/mp4")
    @ResponseBody
    public FileSystemResource file(){
        return new FileSystemResource(MP4_FILE);
    }
    @GetMapping("byterange")
    public void byterange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, MP4_FILE);
        handler.handleRequest(request,response);
    }

    @GetMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping(path = "/player", method = {RequestMethod.GET, RequestMethod.POST})
    public String player(Model model, @RequestParam String url, @RequestParam(defaultValue = "1000") String sirka,@RequestParam  boolean autoPlay, @RequestParam boolean muted  ){
        System.out.println(url);
        if(url == ""){
           return "error";
       }
        model.addAttribute("url", url);
        model.addAttribute("sirka", sirka);
        model.addAttribute("muted", muted ? "true" : "false");
        model.addAttribute("autoplay", autoPlay ? "true" : "false");
        return "player";
    }
    private final static String HLS_PATH = "D:\\MDS\\files\\streams\\HLS\\";
    private  final  static  String DASH_PATH = "D:\\MDS\\files\\streams\\MPEG-DASH\\";


    @RequestMapping(value =  {"/dash/{file}", "/hls/{file}", "/hls/{quality}/{file}"}, method = RequestMethod.GET)
    public  void adaptive_streaming(@PathVariable String file,
                                    @PathVariable(required = false) String quality,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException {

        File STREAM_FILE = null;
        String handle = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        switch(handle){
            case "/dash/{file}":
                STREAM_FILE = new File(DASH_PATH + file);
                break;

            case "/hls/{file}":
                STREAM_FILE = new File(HLS_PATH + file);
                break;

            case "/hls/{quality}/{file}":
                STREAM_FILE = new File(HLS_PATH + quality + file);
                break;

        }
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request,response);

    }

    @RequestMapping(value = "dashPlayer", method = {RequestMethod.GET, RequestMethod.POST})
    public String dashPlayer(@RequestParam String url, Model model){
        model.addAttribute("url", url);
        return "dashPlayer";
    }


    @GetMapping("gallery")
    public String gallery(){
        return "gallery";
    }

}
