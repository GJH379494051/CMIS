package cn.edu.cdtu.sys.controller;

import cn.edu.cdtu.sys.domain.User;
import cn.edu.cdtu.sys.domain.UserVo;
import cn.edu.cdtu.sys.service.UserService;
import cn.edu.cdtu.sys.utils.SysConstant;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * @return 跳转到登录页面的方法
     */
    @GetMapping("/toLogin")
    public String toLogin(){
        return "system/main/login";
    }

    /**
     * 生成验证码： 生成一个图片，以io流的方式输出到前台
     * 参数：session （验证码生成后要放到session）    response  获取输出流
     * 返回什么：空
     */
    @GetMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //1.使用工具类生成图片
        //参数116 宽度  37高度  4 四个字符  5条干扰线
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(116, 37, 4, 5);

        //2.把生成的code 验证码放到session中
        session.setAttribute("code", captcha.getCode());

        //3.以流的方式输出
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(captcha.getImage(), "JPEG", os);
    }

    /**
     * 登录
     * @param userVo
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(UserVo userVo, HttpSession session, Model model){
        String sessionCode = session.getAttribute("code").toString();
        if (sessionCode.equals(userVo.getCode())) {
            User user = userService.login(userVo);
            if (!Objects.isNull(user)) {
                // 保存登录用户信息到session
                session.setAttribute("user", user);
                return "system/main/index";
            }else {
                // 用户名或密码错误
                model.addAttribute("error", SysConstant.USER_LOGIN_ERROR_MSG);
                return "system/main/login";
            }
        }else {
            // 验证码错误
            model.addAttribute("error", SysConstant.USER_LOGIN_CODE_ERROR_MSG);
            return "system/main/login";
        }
    }


}
