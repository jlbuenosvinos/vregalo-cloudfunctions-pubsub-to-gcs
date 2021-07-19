package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.command;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.exception.FromPubSubToGcsCloudFunctionException;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoOutputResult;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.store.BucketStoragePublisher;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.util.UUIDGenerator;

import java.util.logging.Logger;

/**
 * Created by jbuenosv@google.com
 */
public class NewVideoOutputSubmittedCommandHandler implements  CommandHandler {

    public static final Logger logger = Logger.getLogger(NewVideoOutputSubmittedCommandHandler.class.getName());

    private UUIDGenerator uuidGenerator;
    private BucketStoragePublisher publisher;

    /**
     * Default constructor
     */
    public NewVideoOutputSubmittedCommandHandler() {
        this.uuidGenerator = new UUIDGenerator();
        this.publisher = new BucketStoragePublisher();
    }

    /**
     * Executes the command
     * @param command command to be executed
     */
    @Override
    public void execute(Command command) {
        NewVideoOutputSubmittedCommand videoOutputSubmittedCommand = (NewVideoOutputSubmittedCommand)command;
        VideoOutputResult video = videoOutputSubmittedCommand.getVideoOutputResult();

        try {
            logger.info("Ready to process the video output.");
            // String internalId = publisher.publish(video);
            publisher.publish(video);
            logger.info("Result [" + video.getResult()  + "]");
            logger.info("Video output has been processed.");
        }
        catch(Exception e) {
            logger.severe("Unable to process the video ouput.");
            throw new FromPubSubToGcsCloudFunctionException(e);
        }

    }

}
