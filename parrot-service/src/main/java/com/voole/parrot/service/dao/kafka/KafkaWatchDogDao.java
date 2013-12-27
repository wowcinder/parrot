
package com.voole.parrot.service.dao.kafka;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.kafka.KafkaWatchDog;
import org.springframework.stereotype.Repository;

@Repository
public class KafkaWatchDogDao
    extends EntityDao<KafkaWatchDog>
    implements IKafkaWatchDogDao
{


}
