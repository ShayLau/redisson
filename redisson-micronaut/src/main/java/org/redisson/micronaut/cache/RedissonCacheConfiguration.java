/**
 * Copyright (c) 2013-2021 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.micronaut.cache;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.core.naming.Named;
import org.redisson.client.codec.Codec;

import java.time.Duration;

/**
 * Micronaut Cache settings.
 *
 * @author Nikita Koksharov
 *
 */
@EachProperty("redisson.caches")
public class RedissonCacheConfiguration implements Named {

    private final String name;

    private Codec codec;
    private Duration expireAfterWrite;
    private Duration expireAfterAccess;
    private int maxSize;

    public RedissonCacheConfiguration(@Parameter String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    public Codec getCodec() {
        return codec;
    }

    /**
     * Redis data codec applied to cache entries.
     * Default is MarshallingCodec codec
     *
     * @see org.redisson.client.codec.Codec
     * @see org.redisson.codec.MarshallingCodec
     *
     * @param codec - data codec
     * @return config
     */
    public void setCodec(Codec codec) {
        this.codec = codec;
    }

    public Duration getExpireAfterWrite() {
        return expireAfterWrite;
    }

    /**
     * Cache entry time to live duration applied after each write operation.
     *
     * @param expireAfterWrite - time to live duration
     */
    public void setExpireAfterWrite(Duration expireAfterWrite) {
        this.expireAfterWrite = expireAfterWrite;
    }

    public Duration getExpireAfterAccess() {
        return expireAfterAccess;
    }

    /**
     * Cache entry time to live duration applied after each read operation.
     *
     * @param expireAfterAccess - time to live duration
     */
    public void setExpireAfterAccess(Duration expireAfterAccess) {
        this.expireAfterAccess = expireAfterAccess;
    }

    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Max size of this cache. Superfluous elements are evicted using LRU algorithm.
     *
     * @param maxSize - max size
     *                  If <code>0</code> the cache is unbounded (default).
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

}
