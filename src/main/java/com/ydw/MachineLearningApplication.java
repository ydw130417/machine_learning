package com.ydw;

import com.ydw.model.es_model.TimuDocument;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableJpaRepositories
public class MachineLearningApplication implements CommandLineRunner {
    @Autowired
     ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void run(String... args) throws Exception {
        elasticsearchTemplate.putMapping(TimuDocument.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(MachineLearningApplication.class, args);
    }


//    @Bean
//    public TransportClient transportClient() throws UnknownHostException {
//        return new PreBuiltXPackTransportClient(Settings.builder()
//                .put("cluster.name", "es-cluster")
//                .put("xpack.security.user", "jslx:mxPIM#5y6$")
//                .build())
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//    }
}
