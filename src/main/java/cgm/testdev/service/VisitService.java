package cgm.testdev.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgm.testdev.dao.IVisitDAO;
import cgm.testdev.dto.VisitDTO;
import cgm.testdev.entity.Visit;

@Service
public class VisitService implements IVisitService{
	
	private static final Logger logger = LogManager.getLogger(VisitService.class);

	
	@Autowired
	private IVisitDAO visitDAO;
	
	@Override
	public void createVisit(VisitDTO visitDTO) {
		logger.info("createVisit with visit : {} ", visitDTO);
		
		ModelMapper modelMapper = new ModelMapper();	
		Visit visit = modelMapper.map(visitDTO, Visit.class);
		int numberVisitCreated = this.visitDAO.createVisit(visit);
		logger.info("{} visit has been created", numberVisitCreated);
		
	}

	@Override
	public VisitDTO getVisit(int id) {
		logger.info("getVisit with id : {} ", id);
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(this.visitDAO.getVisit(id), VisitDTO.class);
	}
	
	@Override
	public VisitDTO updateVisit(VisitDTO visitDTO) {
		logger.info("updateVisit with visit : {} ", visitDTO);
		
		ModelMapper modelMapper = new ModelMapper();
		
		Visit visit = modelMapper.map(visitDTO, Visit.class);
		
		return modelMapper.map(this.visitDAO.updateVisit(visit), VisitDTO.class);
	}

}
