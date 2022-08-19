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

package com.github.dockerjava.core.command;

import com.github.dockerjava.api.command.HackBuildImageResultCallback;
import com.github.dockerjava.api.listener.BuildImageCallbackListener;

import java.io.File;
import java.io.InputStream;

/**
 * Listenable BuildImageCmdImpl
 *
 * @author Al-assad
 */
public class HackBuildImageCmd extends BuildImageCmdImpl {

    public HackBuildImageCmd(Exec exec) {
        super(exec);
    }

    public HackBuildImageCmd(Exec exec, File dockerFileOrFolder) {
        super(exec, dockerFileOrFolder);
    }

    public HackBuildImageCmd(Exec exec, InputStream tarInputStream) {
        super(exec, tarInputStream);
    }

    public HackBuildImageResultCallback start(BuildImageCallbackListener listener) {
        return exec(new HackBuildImageResultCallback(listener));
    }

}
