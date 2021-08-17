package com.springcloud.controller;

import com.springcloud.pojo.Dept;
import com.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    @Autowired
    private DeptClientService service = null;

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return this.service.queryAll();
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return this.service.addDept(dept);
    }

    @RequestMapping("/consumer/dept/get/{deptno}")
    public Dept get(@PathVariable("deptno") Long deptno){
        return this.service.get(deptno);
    }
}
