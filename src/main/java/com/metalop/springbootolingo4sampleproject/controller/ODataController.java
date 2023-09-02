package com.metalop.springbootolingo4sampleproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.metalop.springbootolingo4sampleproject.service.EdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEdmProvider;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.debug.DefaultDebugSupport;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ODataController {

    @Autowired
    CsdlEdmProvider edmProvider;

    @Autowired
    EntityCollectionProcessor processor;

    @Autowired
    private ApplicationContext applicationContext;
    @RequestMapping("/DemoService.svc/*")
    public void process(HttpServletRequest request, HttpServletResponse response) {
        OData odata = OData.newInstance();

        ServiceMetadata edm = odata.createServiceMetadata(edmProvider,
                new ArrayList<>());
        ODataHttpHandler handler = odata.createHandler(edm);
        handler.register(processor);
        handler.register(new DefaultDebugSupport());
        handler.process(new HttpServletRequestWrapper(request) {
            @Override
            public String getServletPath() {
                return "/DemoService.svc";
            }
        }, response);
    }
}
