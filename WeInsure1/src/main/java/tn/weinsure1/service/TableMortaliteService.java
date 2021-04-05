package tn.weinsure1.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.TableMortalité;
import tn.weinsure1.repository.TableMortalitéRepository;

@Service
public class TableMortaliteService implements ITableMortaliteService {
	@Autowired
	TableMortalitéRepository tr ;

	private static final Logger L= LogManager.getLogger(TableMortaliteService.class);
	@Override
	public TableMortalité Addtm(TableMortalité t) {
		TableMortalité tm = tr.save(t);
		return tm;
	}
}
