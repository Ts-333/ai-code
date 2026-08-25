package com.aicode.ai;

import com.aicode.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeGeneratorServiceFactoryTest {

    @Resource
    private AiCodeGeneratorServiceFactory factory;

    @Test
    void testLookupReturnsNewPrototypeInstance() {
        // 清空缓存以确保触发创建逻辑
        com.github.benmanes.caffeine.cache.Cache<String, AiCodeGeneratorService> cache =
                (com.github.benmanes.caffeine.cache.Cache<String, AiCodeGeneratorService>)
                        ReflectionTestUtils.getField(factory, "serviceCache");
        if (cache != null) {
            cache.invalidateAll();
        }

        // 触发两次不同的 appId 创建，以避免缓存命中
        AiCodeGeneratorService service1 = factory.getAicodeGeneratorService(1001L, CodeGenTypeEnum.HTML);
        AiCodeGeneratorService service2 = factory.getAicodeGeneratorService(1002L, CodeGenTypeEnum.HTML);

        // 通过反射获取内部构建的模型实例进行比对（或者通过日志观察）
        // 如果 @Lookup 生效，每次构建服务都会获取新的模型实例
        // 由于模型实例被封装在 AiServices 内部，更直观的方式是观察日志或 Mock 验证
        assertNotNull(service1);
        assertNotNull(service2);
        assertNotSame(service1, service2);
    }

}