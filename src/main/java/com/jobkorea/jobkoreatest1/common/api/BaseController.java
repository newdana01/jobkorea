package com.jobkorea.jobkoreatest1.common.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class BaseController {
    
}
