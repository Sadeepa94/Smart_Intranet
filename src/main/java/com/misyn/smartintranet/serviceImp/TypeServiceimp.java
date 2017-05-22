package com.misyn.smartintranet.serviceImp;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misyn.smartintranet.dao.TypeDAO;
import com.misyn.smartintranet.entity.Type;
import com.misyn.smartintranet.entity.Type.TypeName;
import com.misyn.smartintranet.service.TypeService;

@Service
@Transactional
public class TypeServiceimp implements TypeService {
	
	@Autowired
	TypeDAO typeDAO;
	

	@Override
	public List<Type> getAllTypes() {
		return typeDAO.getAllTypes();
	}

	@Override
	public Type getType(int id) {
		return typeDAO.getType(id);
	}

	@Override
	public boolean updateType(Type type) {
		return typeDAO.updateType(type);
	}

	@Override
	public boolean saveType(Type type) {
		return typeDAO.saveType(type);
	}

	@Override
	public boolean deleteType(int id) {
		return typeDAO.deleteType(id);
	}

	@Override
	public List<Tuple> getAllTypesNameId() {
		return typeDAO.getAllTypesNameId();
	}

	@Override
	public Type getTypeByTypeName(TypeName typeName) {
		return typeDAO.getTypeByTypeName(typeName);
	}
	
	

}
