package com.move.telegrambot.bussines;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.move.telegrambot.model.dto.BilleterasUserDto;
import com.move.telegrambot.model.dto.TasaSunacripDto;
import com.move.telegrambot.model.mapper.GeneralMapper;
import com.move.telegrambot.repository.sunacrip.BIlleterasUserRepo;
import com.move.telegrambot.repository.sunacrip.SunacripUserRepo;
import com.move.telegrambot.repository.sunacrip.TasaSunacripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SunacripBusiness {
    
    @Autowired
    GeneralMapper mapper;

    @Autowired
    SunacripUserRepo sunacripUserRepo;

    @Autowired
    BIlleterasUserRepo bIlleterasUserRepo;

    @Autowired
    TasaSunacripRepo tasaSunacripRepo;

    @Transactional
    public long countUserRegister(){
        return this.sunacripUserRepo.count();
    }

    @Transactional
    public List<BilleterasUserDto> countBilleterasByMoneda(String moneda){
        return this.bIlleterasUserRepo.findAll().stream()
        .map(this.mapper::billeteraToDto)
        .filter(x -> x.getMoneda().equals(moneda))
        .collect(Collectors.toList());
    }

    @Transactional
    public List<BilleterasUserDto> countBilleteraAll(){
        return this.bIlleterasUserRepo.findAll().stream()
        .map(this.mapper::billeteraToDto)
        .collect(Collectors.toList());
    }
    
    @Transactional
    public List<TasaSunacripDto> tasaAll(){
        return this.tasaSunacripRepo.findAll().stream()
        .map(this.mapper::tasaSunacripToDto)
        .collect(Collectors.toList());
    }
    
}
