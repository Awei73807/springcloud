package com.springcloud.service;

import com.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

//降级~
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {

    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept get(Long deptno) {
                return new Dept()
                        .setDeptno(deptno)
                        .setDname("id=>" + deptno + ",没有对应的信息，客户端拱了降级的信息，这个服务现在已经关闭~")
                        .setDb_source("no this database mysql");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
