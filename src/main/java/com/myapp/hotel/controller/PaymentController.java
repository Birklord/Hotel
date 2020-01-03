package com.myapp.hotel.controller;


import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.dto.ResponseModel;
import com.myapp.hotel.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RequestMapping("api/v1/payment")
@RestController
public class PaymentController {
    @Autowired
    private  PaymentService paymentService;
    @Autowired
    private Environment env;
    static Logger logger = Logger.getLogger(String.valueOf(PaymentController.class));

    @PostMapping("/save")
    public Boolean addCustomer(@Valid @NonNull @RequestBody PaymentRequest paymentRequest) {
        Boolean isSaved = paymentService.addPayment(paymentRequest);
        return isSaved;
    }
    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @PostMapping(value = "/pgredirect")
//    public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
//                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
//                                    @RequestParam(name = "ORDER_ID") String orderId) throws Exception {
//        PaymentRequest paymentRequest = new PaymentRequest();
//        ModelAndView modelAndView = new ModelAndView("redirect:" + paymentRequest.getPaytmUrl());
//        TreeMap<String, String> parameters = new TreeMap<>();
//        paymentRequest.getDetails().forEach((k, v) -> parameters.put(k, v));
//        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
//        parameters.put("EMAIL", env.getProperty("paytm.email"));
//        parameters.put("ORDER_ID", orderId);
//        parameters.put("TXN_AMOUNT", transactionAmount);
//        parameters.put("CUST_ID", customerId);
//        String checkSum = getCheckSum(parameters);
//        parameters.put("CHECKSUMHASH", checkSum);
//        modelAndView.addAllObjects(parameters);
//        return modelAndView;
//    }
//
//
//    @PostMapping(value = "/pgresponse")
//    public String getResponseRedirect(HttpServletRequest request, Model model) {
//
//        Map<String, String[]> mapData = request.getParameterMap();
//        TreeMap<String, String> parameters = new TreeMap<String, String>();
//        mapData.forEach((key, val) -> parameters.put(key, val[0]));
//        String paytmChecksum = "";
//        if (mapData.containsKey("CHECKSUMHASH")) {
//            paytmChecksum = mapData.get("CHECKSUMHASH")[0];
//        }
//        String result;
//
//        boolean isValideChecksum = false;
//        System.out.println("RESULT : "+parameters.toString());
//        try {
//            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
//            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
//                if (parameters.get("RESPCODE").equals("01")) {
//                    result = "Payment Successful";
//                } else {
//                    result = "Payment Failed";
//                }
//            } else {
//                result = "Checksum mismatched";
//            }
//        } catch (Exception e) {
//            result = e.toString();
//        }
//        model.addAttribute("result",result);
//        parameters.remove("CHECKSUMHASH");
//        model.addAttribute("parameters",parameters);
//        return "report";
//    }

//    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
//        PaymentRequest paymentRequest = new PaymentRequest();
//        return CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(paymentRequest.getMerchantKey(),
//                parameters, paytmChecksum);
//    }
//
//
//    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
//        PaymentRequest paymentRequest = new PaymentRequest();
//        return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(paymentRequest.getMerchantKey(), parameters);
//    }

    @GetMapping("/retrieveall")
    public ResponseModel getAllPayments() {
        ResponseModel responseModel = new ResponseModel();
        try{
            List<BaseModel> paymentList = paymentService.findAllPayment();
            //responseModel.data.addAll(customerList);
            responseModel.setData(paymentList);
            responseModel.setResponseCode("00");
            responseModel.setValid(true);
            responseModel.setResponseMessage("Success fetching customers");}
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            responseModel.setData(null);
            responseModel.setResponseCode("99");
            responseModel.setValid(false);
            responseModel.setResponseMessage("Failure fetching customers");
            logger.severe("Failure fetching customers");
        }

        return responseModel;
    }

    @GetMapping("retrievebyid/{id}")
    public ResponseModel getPaymentById(@PathVariable("id") Long id) {
        ResponseModel responseModel = new ResponseModel();
        try {
                responseModel.getData().add(paymentService.findPaymentById(id));
                responseModel.setResponseCode("00");
                responseModel.setValid(true);
                responseModel.setResponseMessage("Success fetching customers");
                logger.info("Succes fetching payment");
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            responseModel.setData(null);
            responseModel.setResponseCode("99");
            responseModel.setValid(false);
            responseModel.setResponseMessage("Failure fetching customers");
            logger.severe("Failure fetching payment");

        }
        return responseModel;
    }

}
