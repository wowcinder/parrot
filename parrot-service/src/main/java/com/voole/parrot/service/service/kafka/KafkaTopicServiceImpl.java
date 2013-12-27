
package com.voole.parrot.service.service.kafka;

import com.voole.parrot.service.dao.kafka.IKafkaTopicDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.kafka.KafkaTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KafkaTopicServiceImpl
    extends EntityServiceImpl<KafkaTopic>
    implements KafkaTopicService
{

    @Autowired
    private IKafkaTopicDao KafkaTopicDao;

    public IKafkaTopicDao getEntityDao() {
        return KafkaTopicDao;
    }

}
