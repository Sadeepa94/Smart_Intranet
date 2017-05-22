package com.misyn.smartintranet.service;

import java.util.List;

import javax.persistence.Tuple;

import com.misyn.smartintranet.entity.Type;
import com.misyn.smartintranet.entity.Type.TypeName;

public interface TypeService {
	
	public List<Type>  getAllTypes();
	public Type getType(int id);
	public boolean updateType(Type type);
	public boolean saveType(Type type);
	public boolean deleteType(int id);
	public List<Tuple> getAllTypesNameId();
	public Type getTypeByTypeName(TypeName typeName);

}
