package com.voole.parrot.service.service.hbasemeta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.hbasemeta.IHbaseTableVersionDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

@Service
@Transactional
public class HbaseTableVersionServiceImpl extends
		EntityServiceImpl<HbaseTableVersion> implements
		HbaseTableVersionService {

	@Autowired
	private IHbaseTableVersionDao HbaseTableVersionDao;

	public IHbaseTableVersionDao getEntityDao() {
		return HbaseTableVersionDao;
	}

	@Override
	public HbaseTableColumn createHbaseTableColumn(HbaseTableColumn column) {
		return HbaseTableVersionDao.createHbaseTableColumn(column);
	}

	@Override
	public HbaseTableVersion modifyHbaseTableVersion(HbaseTableVersion version) {
		return HbaseTableVersionDao.modifyHbaseTableVersion(version);
	}

	@Override
	public HbaseTableVersion duplicateHbaseTableVerion(
			HbaseTableVersion duplicate, HbaseTableVersion from) {
		return HbaseTableVersionDao.duplicateHbaseTableVerion(duplicate, from);
	}

	@Override
	public void changeHbaseTableColumnsPos(List<HbaseTableColumn> column,
			Integer pos) {
		HbaseTableVersionDao.changeHbaseTableColumnsPos(column, pos);

	}

}
