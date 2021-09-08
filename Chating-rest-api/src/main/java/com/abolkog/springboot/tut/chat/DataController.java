package com.abolkog.springboot.tut.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abolkog.springboot.tut.BaseController;
import com.abolkog.springboot.tut.model.entity.AppUser;
import com.abolkog.springboot.tut.model.entity.Todo;
import com.abolkog.springboot.tut.chat.*;

@RestController
@RequestMapping(value = "/api/v1/data")
public class DataController extends BaseController
{

    @GetMapping(value = {"", "/"})
    public ResponseEntity<Chart> getAllTodos() {
        
        // Get userName:
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = (AppUser) authentication.getPrincipal();
        System.out.println("login userName: " + appUser.getName());
        //
        Data d1 = new Data(1123700000, 31396.3, 36036.06, 35969.27, 39800, 34378.8426848829, 1327153972.8363984, 787645); 
        Data d2 = new Data(1223513600, 32396.3, 36036.06, 34761.54, 36155.17, 19244.28162474243, 684861591.1778022, 509178);
        
                
        Data d3 = new Data(1323600000, 33396.3, 36036.06, 35969.27, 39800, 34378.8426848829, 1327153972.8363984, 787645);
        Data d4 = new Data(1423513600, 34396.3, 36036.06, 34761.54, 36155.17, 19244.28162474243, 684861591.1778022, 509178);
        
        
        Data d5 = new Data(1523600000, 35396.3, 36036.06, 35969.27, 39800, 34378.8426848829, 1327153972.8363984, 787645);
        
        Data d6 = new Data(1623513600, 36396.3, 36036.06, 34761.54, 36155.17, 19244.28162474243, 684861591.1778022, 509178);
        
        
        Data d7 = new Data(1723600000, 37396.3, 36036.06, 35969.27, 39800, 34378.8426848829, 1327153972.8363984, 787645);
       
        Data d8 = new Data(1823513600, 38396.3, 36036.06, 34761.54, 36155.17, 19244.28162474243, 684861591.1778022, 509178);
        
        
        
        List<Data> data =  new ArrayList<Data>();
        data.add(d1);
        data.add(d2);
        data.add(d3);
        data.add(d4);
        data.add(d5);
        data.add(d6);
        data.add(d7);
        data.add(d8);
        
        //
        Chart chart = new Chart("market.btcusdt.kline.1day", "ok", 1623742944096L, data);
        
        return new ResponseEntity<>(chart, HttpStatus.OK);
    }  
    
}
