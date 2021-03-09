package com.gjozef84.randomnumbergenerator.controller;

import com.gjozef84.randomnumbergenerator.service.RandomNumberGeneratorFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/generate")
@RequiredArgsConstructor
@Slf4j
public class NumberGeneratorController {

    private final RandomNumberGeneratorFacade randomNumberGeneratorFacade;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getGeneratedNumber() {
        return randomNumberGeneratorFacade.generateRandomNumber();
    }
}
