package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.exception.FromPubSubToGcsCloudFunctionException;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.process.VideoOutputProcessManager;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.process.VideoOutputProcessManagerImpl;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoOutputResult;

import java.util.logging.Logger;

/**
 * Created by jbuenosv@google.com
 */
public class FromPubSubToGcsCloudFunction implements BackgroundFunction<PubSubEvent> {

    public static final Logger logger = Logger.getLogger(FromPubSubToGcsCloudFunction.class.getName());

    @Override
    public void accept(PubSubEvent message, Context context) throws Exception {
        try {
            logger.info("Receive message event: " + message.toString());

            VideoOutputResult newOutputVideo = new VideoOutputResult(message.getData());
            VideoOutputProcessManager videoOutputProcessManager = new VideoOutputProcessManagerImpl();
            videoOutputProcessManager.processVideo(newOutputVideo);

            logger.info("End receive message event.");
        }
        catch(Exception e) {
            logger.severe("Unable to process the event message  [" + message.toString() + "]");
            throw new FromPubSubToGcsCloudFunctionException(e.getCause());
        }

    }

}