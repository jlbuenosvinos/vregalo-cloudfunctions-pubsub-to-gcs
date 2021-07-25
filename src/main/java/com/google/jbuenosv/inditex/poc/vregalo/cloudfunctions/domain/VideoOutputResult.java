package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.exception.FromPubSubToGcsCloudFunctionException;

import java.io.Serializable;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Created by jbuenosv@google.com
 */
public class VideoOutputResult implements Serializable {

    public static final Logger logger = Logger.getLogger(VideoOutputResult.class.getName());

    private String fileName;
    private String transportResult;
    private String result;

    public VideoOutputResult(String transportResult) {
        this.transportResult = transportResult;
        this.result = new String(Base64.getDecoder().decode(transportResult));
        getFileNameFromPayLoad();
    }

    public void setTransportResult(String transportResult) {
        this.transportResult = transportResult;
        this.result = new String(Base64.getDecoder().decode(transportResult));
        getFileNameFromPayLoad();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResult() {
        return this.result;
    }

    @Override
    public String toString() {
        return "VideoOutputResult {" +
                "fileName='" + fileName + '\'' +
                ", transportResult='" + transportResult + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    private void getFileNameFromPayLoad() {
        JsonNode nameNode;
        JsonNode jsonItem;

        if (this.result != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(this.result);
                nameNode = rootNode.path("output_file_name");
                setFileName(nameNode.textValue());
            } catch (Exception e) {
                logger.severe("Unable to set the file name due to [" + e.getMessage() + "]");
                throw new FromPubSubToGcsCloudFunctionException(e);
            }
        } else {
            throw new FromPubSubToGcsCloudFunctionException("Unable to set the file name.");
        }

    }

}
