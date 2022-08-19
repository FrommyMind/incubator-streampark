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

package com.streamxhub.streamx.flink.connector.hbase.source;

import com.streamxhub.streamx.common.util.Utils;
import com.streamxhub.streamx.flink.connector.function.RunningFunction;
import com.streamxhub.streamx.flink.connector.hbase.function.HBaseQueryFunction;
import com.streamxhub.streamx.flink.connector.hbase.function.HBaseResultFunction;
import com.streamxhub.streamx.flink.connector.hbase.internal.HBaseSourceFunction;
import com.streamxhub.streamx.flink.core.scala.StreamingContext;

import org.apache.flink.streaming.api.datastream.DataStreamSource;

import java.util.Properties;

/**
 * @author benjobs
 */
public class HBaseJavaSource<T> {
    private final StreamingContext context;
    private final Properties property;

    public HBaseJavaSource(StreamingContext context, Properties property) {
        this.context = context;
        this.property = property;
    }

    public DataStreamSource<T> getDataStream(HBaseQueryFunction<T> queryFunction,
                                             HBaseResultFunction<T> resultFunction,
                                             RunningFunction runningFunc) {

        Utils.require(queryFunction != null, "queryFunction must not be null");
        Utils.require(resultFunction != null, "resultFunction must not be null");
        HBaseSourceFunction<T> sourceFunction = new HBaseSourceFunction<>(
            property,
            queryFunction,
            resultFunction,
            runningFunc,
            null);
        return context.getJavaEnv().addSource(sourceFunction);
    }
}
