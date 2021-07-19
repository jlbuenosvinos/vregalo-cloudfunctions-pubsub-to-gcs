package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.process;


import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.command.NewVideoOutputSubmittedCommand;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.command.NewVideoOutputSubmittedCommandHandler;
import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoOutputResult;

/**
 * Created by jbuenosv@google.com
 */
public class VideoOutputProcessManagerImpl implements VideoOutputProcessManager {

    NewVideoOutputSubmittedCommandHandler newVideoOutputSubmittedCommandHandler;
    NewVideoOutputSubmittedCommand videoOutputSubmittedCommand;

    /**
     * Default constructor
     */
    public VideoOutputProcessManagerImpl() {
        this.newVideoOutputSubmittedCommandHandler = new NewVideoOutputSubmittedCommandHandler();
        this.videoOutputSubmittedCommand = new NewVideoOutputSubmittedCommand();
    }

    @Override
    public void processVideo(VideoOutputResult videoOutputResult) {
        this.videoOutputSubmittedCommand.setVideoOutputResult(videoOutputResult);
        newVideoOutputSubmittedCommandHandler.execute(this.videoOutputSubmittedCommand);
    }

}
