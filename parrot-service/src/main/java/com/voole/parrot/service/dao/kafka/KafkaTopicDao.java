
package com.voole.parrot.service.dao.kafka;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.kafka.KafkaTopic;
import org.springframework.stereotype.Repository;

@Repository
public class KafkaTopicDao
    extends EntityDao<KafkaTopic>
    implements IKafkaTopicDao
{


}
