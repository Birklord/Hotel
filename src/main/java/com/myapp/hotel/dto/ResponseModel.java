package com.myapp.hotel.dto;

import com.myapp.hotel.model.Customer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ResponseModel {
    private boolean valid;
    public List<BaseModel> data = new ArrayList<>();
    private String responseCode;
    private String responseMessage;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<BaseModel> getData() {
        return data;
    }

    public void setData(List<BaseModel> data) {
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
