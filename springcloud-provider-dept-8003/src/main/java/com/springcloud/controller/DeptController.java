package com.springcloud.controller;

import com.springcloud.dao.DeptDao;
import com.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptDao deptDao;
    //获取一些配置的信息，得到具体的微服务！
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public Boolean addDept(Dept dept){
        return deptDao.addDept(dept);
    }

    @GetMapping("/dept/get/{deptno}")
    public Dept getDept(@PathVariable("deptno") Long deptno){
        return deptDao.queryById(deptno);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptDao.queryAll();
    }

    @GetMapping("/dept/discovery")
    public Object discovery(){
        //获取微服务列表清单
        List<String> services = client.getServices();
        System.out.println("discovery=>service" + services);

        //得到具体的微服务信息,通过具体的服务id，applicationName;
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance:instances) {
            System.out.println(
                    instance.getHost() + "\t"+
                    instance.getPort() + "\t"+
                    instance.getUri() + "\t"+
                    instance.getServiceId()
            );
        }
        return this.client;
    }
}
