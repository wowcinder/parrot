
package com.voole.parrot.service.service.kafka;

import com.voole.parrot.service.dao.kafka.IKafkaTopicFixedModelVersionDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.kafka.KafkaTopicFixedModelVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KafkaTopicFixedModelVersionServiceImpl
    extends EntityServiceImpl<KafkaTopicFixedModelVersion>
    implements KafkaTopicFixedModelVersionService
{

    @Autowired
    private IKafkaTopicFixedModelVersionDao KafkaTopicFixedModelVersionDao;

    public IKafkaTopicFixedModelVersionDao getEntityDao() {
        return KafkaTopicFixedModelVersionDao;
    }

}
