package com.example.demo.configs;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {


    @Override
    protected String getKeyspaceName() {
        return "tacocloud";
    }

//    @Bean
//    public CqlSession session(){
//        return CqlSession.builder()
//                .withKeyspace(getKeyspaceName())
//                .withLocalDatacenter("dataCenter")
//                .build();
//    }
}
