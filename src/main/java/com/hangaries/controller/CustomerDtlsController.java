package com.hangaries.controller;

import com.hangaries.model.CustomerDtls;
import com.hangaries.service.customerDtlsService.Impl.CustomerDtlsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CustomerDtlsController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerDtlsController.class);

    @Autowired
    private CustomerDtlsServiceImpl customerDtlsService;

    @PostMapping("saveCustomerDtls")
    @ResponseBody
    public ResponseEntity<CustomerDtls> saveCustomerDtls(@RequestBody CustomerDtls customerDtls) throws Exception {
        CustomerDtls retCustomerDtls=null;
        try {
            logger.info("save customer details::");
            retCustomerDtls=customerDtlsService.saveCustomerDtls(customerDtls);
            return new ResponseEntity<CustomerDtls>(retCustomerDtls, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<CustomerDtls>(retCustomerDtls, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getCustomerAddressDtlsByMobNum")
    @ResponseBody
    public ResponseEntity<List<CustomerDtls>> getCustomerAddressDtlsByMobNum(@RequestParam("mobno") String mobnum) throws Exception {
        List<CustomerDtls> customerDtlsList = new ArrayList<CustomerDtls>();
        try {
            customerDtlsList = customerDtlsService.getCustomerAddressDtlsByMobNum(mobnum);
            return new ResponseEntity<List<CustomerDtls>>(customerDtlsList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<List<CustomerDtls>>(customerDtlsList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("deleteAddressByDeliveryType")
    @ResponseBody
    public ResponseEntity<String> deleteAdressByDeliveryTpe(String mobnum, String delType) throws Exception {
        List<CustomerDtls> customerDtlsList = new ArrayList<CustomerDtls>();
        try {
            customerDtlsService.deleteAddressByType(mobnum, delType);
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}