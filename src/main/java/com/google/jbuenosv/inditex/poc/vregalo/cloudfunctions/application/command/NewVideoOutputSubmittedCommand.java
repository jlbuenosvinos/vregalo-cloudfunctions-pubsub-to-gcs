package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.command;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoOutputResult;

/**
 * Created by jbuenosv@google.com
 */
public class NewVideoOutputSubmittedCommand implements Command {

    private VideoOutputResult videoOutputResult;

    public VideoOutputResult getVideoOutputResult() {
        return videoOutputResult;
    }

    public void setVideoOutputResult(VideoOutputResult videoOutputResult) {
        this.videoOutputResult = videoOutputResult;
    }

}
