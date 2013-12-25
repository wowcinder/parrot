package com.voole.parrot.service.service.hbasemeta;

import com.voole.parrot.service.dao.hbasemeta.IHbaseTableDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HbaseTableServiceImpl extends EntityServiceImpl<HbaseTable>
		implements HbaseTableService {

	@Autowired
	private IHbaseTableDao HbaseTableDao;

	public IHbaseTableDao getEntityDao() {
		return HbaseTableDao;
	}

	@Override
	public HbaseTableVersion createHbaseTableVersion(HbaseTableVersion version) {
		return HbaseTableDao.createHbaseTableVersion(version);
	}

	@Override
	public HbaseTable modifyTable(HbaseTable table) {
		return HbaseTableDao.modifyTable(table);
	}

}
