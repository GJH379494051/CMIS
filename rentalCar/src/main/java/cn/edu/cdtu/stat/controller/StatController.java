package cn.edu.cdtu.stat.controller;

import cn.edu.cdtu.bus.domain.Customer;
import cn.edu.cdtu.bus.domain.Rent;
import cn.edu.cdtu.bus.service.CustomerService;
import cn.edu.cdtu.bus.service.RentService;
import cn.edu.cdtu.bus.utils.ExportRentUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 统计分析
 */
@Controller
@RequestMapping("/stat/")
public class StatController {

    @Resource
    private CustomerService customerService;
    @Resource
    private RentService rentService;

    /**
     * 导出出租单数据
     * @param rentid
     */
    @GetMapping("exportRent")
    public ResponseEntity<Object> exportRent(String rentid){
        //根据出租单号查询出租单信息
        Rent rent = rentService.queryRentByRentId(rentid);
        //根据身份证号查询客户信息
        Customer customer = customerService.queryCustomerByIdentity(rent.getIdentity());
        String fileName=customer.getCustname()+"-的出租单.xls";
        String sheetName=customer.getCustname()+"出租单";
        ByteArrayOutputStream bos = ExportRentUtils.exportRent(rent,customer,sheetName);

        try {
            //处理文件名乱码
            fileName= URLEncoder.encode(fileName,"UTF-8");
            //创建 封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            headers.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<Object>(bos.toByteArray(),headers, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
