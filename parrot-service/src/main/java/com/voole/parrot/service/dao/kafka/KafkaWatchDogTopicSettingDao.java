
package com.voole.parrot.service.dao.kafka;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.kafka.KafkaWatchDogTopicSetting;
import org.springframework.stereotype.Repository;

@Repository
public class KafkaWatchDogTopicSettingDao
    extends EntityDao<KafkaWatchDogTopicSetting>
    implements IKafkaWatchDogTopicSettingDao
{


}
