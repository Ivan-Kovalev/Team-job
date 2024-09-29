package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.service.CacheService;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    /** Класс по работе с кэшем */
    private final CacheManager cacheManager;

    /**
     * Метод очищения кэша
     */
    @Override
    public void clearCacheOfRecommendation() {
        cacheManager.getCache("getRecommendation").clear();
    }

}
