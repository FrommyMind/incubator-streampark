/*
 * Copyright 2019 The StreamX Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dockerjava.api.command;

import com.github.dockerjava.api.listener.BuildImageCallbackListener;
import com.github.dockerjava.api.model.BuildResponseItem;

/**
 * @author Al-assad
 */
public class HackBuildImageResultCallback extends BuildImageResultCallback {

    private final BuildImageCallbackListener listener;

    private static final String STEP_PREFIX = "Step";

    public HackBuildImageResultCallback(BuildImageCallbackListener listener) {
        this.listener = listener;
    }

    @Override
    public void onNext(BuildResponseItem item) {
        super.onNext(item);
        String stream = item.getStream();
        if (item.isErrorIndicated()) {
            listener.watchBuildStep(item.getErrorDetail().getMessage());
        } else if (stream != null && stream.startsWith(STEP_PREFIX)) {
            listener.watchBuildStep(stream);
        }
    }

}
