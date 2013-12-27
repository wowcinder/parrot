
package com.voole.parrot.service.service.kafka;

import com.voole.parrot.service.dao.kafka.IKafkaWatchDogDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.kafka.KafkaWatchDog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KafkaWatchDogServiceImpl
    extends EntityServiceImpl<KafkaWatchDog>
    implements KafkaWatchDogService
{

    @Autowired
    private IKafkaWatchDogDao KafkaWatchDogDao;

    public IKafkaWatchDogDao getEntityDao() {
        return KafkaWatchDogDao;
    }

}
