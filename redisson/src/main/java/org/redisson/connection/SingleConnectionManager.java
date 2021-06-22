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
package org.redisson.connection;

import org.redisson.config.*;

import java.util.UUID;

/**
 *
 * 单机链接 集成 主从链接管理配置
 * @author Nikita Koksharov
 *
 */
public class SingleConnectionManager extends MasterSlaveConnectionManager {

    /**
     * 将单机配置转换成主从配置
     *
     * @param cfg 单机服务器配置
     * @param config 服务器配置
     * @param id uuid
     */
    public SingleConnectionManager(SingleServerConfig cfg, Config config, UUID id) {

        super(create(cfg), config, id);
    }


    private static MasterSlaveServersConfig create(SingleServerConfig cfg) {
        MasterSlaveServersConfig newconfig = new MasterSlaveServersConfig();

        //ping 时间间隔
        newconfig.setPingConnectionInterval(cfg.getPingConnectionInterval());
        //
        newconfig.setSslEnableEndpointIdentification(cfg.isSslEnableEndpointIdentification());
        //
        newconfig.setSslProvider(cfg.getSslProvider());
        //
        newconfig.setSslTruststore(cfg.getSslTruststore());
        //
        newconfig.setSslTruststorePassword(cfg.getSslTruststorePassword());
        //
        newconfig.setSslKeystore(cfg.getSslKeystore());
        //
        newconfig.setSslKeystorePassword(cfg.getSslKeystorePassword());
        //
        newconfig.setSslProtocols(cfg.getSslProtocols());
        //
        newconfig.setRetryAttempts(cfg.getRetryAttempts());
        //重试间隔
        newconfig.setRetryInterval(cfg.getRetryInterval());
        //超时时间
        newconfig.setTimeout(cfg.getTimeout());
        //用户密码
        newconfig.setPassword(cfg.getPassword());
        //用户名
        newconfig.setUsername(cfg.getUsername());
        //数据库
        newconfig.setDatabase(cfg.getDatabase());
        //客户端名称
        newconfig.setClientName(cfg.getClientName());
        //主地址 ip:port
        newconfig.setMasterAddress(cfg.getAddress());
        //主连接池配置
        newconfig.setMasterConnectionPoolSize(cfg.getConnectionPoolSize());
        //
        newconfig.setSubscriptionsPerConnection(cfg.getSubscriptionsPerConnection());
        //订阅连接池时间
        newconfig.setSubscriptionConnectionPoolSize(cfg.getSubscriptionConnectionPoolSize());
        //链接超时时间
        newconfig.setConnectTimeout(cfg.getConnectTimeout());
        //空闲链接超时时间
        newconfig.setIdleConnectionTimeout(cfg.getIdleConnectionTimeout());
        //DNS 监控间隔
        newconfig.setDnsMonitoringInterval(cfg.getDnsMonitoringInterval());
        //主链接池最先空闲数量
        newconfig.setMasterConnectionMinimumIdleSize(cfg.getConnectionMinimumIdleSize());
        //订阅链池最小空闲数量
        newconfig.setSubscriptionConnectionMinimumIdleSize(cfg.getSubscriptionConnectionMinimumIdleSize());
        //读模式：主
        newconfig.setReadMode(ReadMode.MASTER);
        //订阅模式：主
        newconfig.setSubscriptionMode(SubscriptionMode.MASTER);
        //保持活跃时间
        newconfig.setKeepAlive(cfg.isKeepAlive());
        //
        newconfig.setTcpNoDelay(cfg.isTcpNoDelay());
        //name mapper
        newconfig.setNameMapper(cfg.getNameMapper());
        return newconfig;
    }

}
