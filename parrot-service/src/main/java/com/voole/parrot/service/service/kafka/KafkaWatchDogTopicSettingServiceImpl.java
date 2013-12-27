
package com.voole.parrot.service.service.kafka;

import com.voole.parrot.service.dao.kafka.IKafkaWatchDogTopicSettingDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.kafka.KafkaWatchDogTopicSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KafkaWatchDogTopicSettingServiceImpl
    extends EntityServiceImpl<KafkaWatchDogTopicSetting>
    implements KafkaWatchDogTopicSettingService
{

    @Autowired
    private IKafkaWatchDogTopicSettingDao KafkaWatchDogTopicSettingDao;

    public IKafkaWatchDogTopicSettingDao getEntityDao() {
        return KafkaWatchDogTopicSettingDao;
    }

}
