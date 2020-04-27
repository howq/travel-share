package personal.zhou.travelshare.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import personal.zhou.travelshare.common.storage.LocalStorage;
import personal.zhou.travelshare.common.storage.BaseStorageService;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration {

    private final StorageProperties properties;

    public StorageAutoConfiguration(StorageProperties properties) {
        this.properties = properties;
    }

    @Bean
    public BaseStorageService baseStorageService() {
        BaseStorageService baseStorageService = new BaseStorageService();
        String active = this.properties.getActive();
        baseStorageService.setActive(active);
        baseStorageService.setStorage(localStorage());
        return baseStorageService;
    }

    @Bean
    public LocalStorage localStorage() {
        LocalStorage localStorage = new LocalStorage();
        StorageProperties.Local local = this.properties.getLocal();
        localStorage.setAddress(local.getAddress());
        localStorage.setStoragePath(local.getStoragePath());
        return localStorage;
    }

}
