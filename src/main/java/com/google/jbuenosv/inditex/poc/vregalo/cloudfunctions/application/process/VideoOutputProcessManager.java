package com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.application.process;

import com.google.jbuenosv.inditex.poc.vregalo.cloudfunctions.domain.VideoOutputResult;

/**
 * Created by jbuenosv@google.com
 */
public interface VideoOutputProcessManager {

    void processVideo(VideoOutputResult videoOutputResult);

}
