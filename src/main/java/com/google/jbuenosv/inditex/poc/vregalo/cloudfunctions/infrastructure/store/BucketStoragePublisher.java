package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.store;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoOutputResult;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.infrastructure.util.ConfigLoader;
import java.nio.charset.Charset;

import java.util.Properties;
import java.util.logging.Logger;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;

/**
 * Created by jbuenosv@google.com
 */
public class BucketStoragePublisher {

    public static final Logger logger = Logger.getLogger(BucketStoragePublisher.class.getName());

    private Properties props;

    /**
     * Private constructor
     */
    public BucketStoragePublisher() {
    }

    /**
     * Stores the video analysis result
     * @param video video analysis result
     */
    public void publish(VideoOutputResult video) {
        String projectName ;
        String bucketName;

        try {
            projectName = ConfigLoader.getInstance().getEnv(ConfigLoader.getInstance().getProperty("gcp.project.env.name"));
            bucketName =  ConfigLoader.getInstance().getEnv(ConfigLoader.getInstance().getProperty("video.output.store.bucketname.env.name"));
            logger.info("The message has to be published into [" + projectName + "," + bucketName + "].");

            Storage storage = StorageOptions.newBuilder().setProjectId(projectName).build().getService();
            BlobId blobId = BlobId.of(bucketName, video.getFileName());

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                                .setContentType("application/json")
                                .build();

            storage.create(blobInfo,video.getResult().getBytes(Charset.forName("UTF-8")));

            logger.info("The message has been published to bucket [" + bucketName + "].");
        }
        catch (Exception e) {
            logger.severe("Unable to publish the message due to: " + e.getMessage());
            throw e;
        }

    }

}
