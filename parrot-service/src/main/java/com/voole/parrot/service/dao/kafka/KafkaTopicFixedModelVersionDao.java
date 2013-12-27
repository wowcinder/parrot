
package com.voole.parrot.service.dao.kafka;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.kafka.KafkaTopicFixedModelVersion;
import org.springframework.stereotype.Repository;

@Repository
public class KafkaTopicFixedModelVersionDao
    extends EntityDao<KafkaTopicFixedModelVersion>
    implements IKafkaTopicFixedModelVersionDao
{


}
