package org.example.controller;

import org.example.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("person/info")
public class InfoController
{
    @Autowired
    InfoService infoService;
}
