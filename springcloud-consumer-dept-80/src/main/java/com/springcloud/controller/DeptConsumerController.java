package com.springcloud.controller;

import com.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    //理解：消费者，不应该有service层

    //Ribbon.我们这里的地址，应该是一个变量，所有要通过服务名来访问
    //Ribbon 和 Eureka 整合以后，客户端可以直接调用，不用关心IP地址和端口号
    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    //RestTemplate ... 供我们直接调用就可以了，注册到spring中
    @Autowired //(url,实体类 Map ,Class<T> responseType)
    private RestTemplate restTemplate; //提供了多种便捷访问远程http服务的方法，简单restful服务模板

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list",List.class);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add",dept,boolean.class);
    }

    @RequestMapping("/consumer/dept/get/{deptno}")
    public Dept get(@PathVariable("deptno") Long deptno){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + deptno,Dept.class);
    }
}
